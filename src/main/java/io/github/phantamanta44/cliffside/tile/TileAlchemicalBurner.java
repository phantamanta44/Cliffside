package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.TileConstants;
import io.github.phantamanta44.cliffside.tile.base.IActiveMachine;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.IDirectional;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenProvider;
import io.github.phantamanta44.cliffside.tile.base.IWrenchable;
import io.github.phantamanta44.cliffside.tile.base.TileBasicInventory;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;

public class TileAlchemicalBurner extends TileBasicInventory implements IActiveMachine, IDirectional, ILumenProvider, IWrenchable, IDebuggable {

	private static int LUMEN_BUFFER_SIZE = 64, LUMEN_PACKET_SIZE = 8;
	private int frontFace = -1;
	private int lumenBuffer = 0;
	private int totalBurnTime = 0, burnTime = 0, work = 0, workNeeded = 128;
	
	public TileAlchemicalBurner() {
		super(TileConstants.ALCHEM_BURNER_NAME, 1);
	}
	
	public TileAlchemicalBurner(int efficiency) {
		super(TileConstants.ALCHEM_BURNER_NAME, 1);
		workNeeded = efficiency;
	}

	@Override
	public void openInventory() {
		// NO-OP
	}

	@Override
	public void closeInventory() {
		// NO-OP
	}

	@Override
	public void onWrenchUse(int face, EntityPlayer player, ItemStack wrench) {
		rotateClockwise();
	}

	@Override
	public void onWrenchUseSneaking(int face, EntityPlayer player, ItemStack wrench) {
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air, 0, 3);
		float f = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f1 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		EntityItem ent = new EntityItem(worldObj, xCoord + f, yCoord + f1, zCoord + f2, new ItemStack(CSBlocks.machine, 1, BlockMachine.ALCHEM_BURNER));
		float f3 = 0.05F;
		ent.motionX = (double)((float)worldObj.rand.nextGaussian() * f3);
		ent.motionY = (double)((float)worldObj.rand.nextGaussian() * f3 + 0.2F);
		ent.motionZ = (double)((float)worldObj.rand.nextGaussian() * f3);
		worldObj.spawnEntityInWorld(ent);
	}
	
	@Override
	public void onDebug(EntityPlayer player) {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields) {
			try {
				player.addChatMessage(new ChatComponentText(f.getName() + ": " + f.get(this).toString()));
			} catch (Throwable th) { }
		}
	}

	@Override
	public int getStoredEnergy() {
		return lumenBuffer;
	}

	@Override
	public boolean canProvideEnergy(int amount, ILumenAcceptor destination) {
		return lumenBuffer >= amount;
	}

	@Override
	public int getFrontFace() {
		return frontFace;
	}
	
	@Override
	public void setFrontFace(int face) {
		frontFace = face;
	}

	@Override
	public void rotateClockwise() {
		switch (frontFace) {
		case 2:
			frontFace = 5;
			break;
		case 4:
			frontFace = 2;
			break;
		case 3:
			frontFace = 4;
			break;
		case 5:
			frontFace = 3;
			break;
		}
	}

	@Override
	public void rotateCounterclockwise() {
		switch (frontFace) {
		case 2:
			frontFace = 4;
			break;
		case 4:
			frontFace = 3;
			break;
		case 3:
			frontFace = 5;
			break;
		case 5:
			frontFace = 2;
			break;
		}
	}

	@Override
	public boolean isActive() {
		return burnTime > 0;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			boolean dirty = false;
			
			if (burnTime > 0) {
				burnTime--;
				work++;
			}
			if (work >= workNeeded) {
				work = 0;
				lumenBuffer++;
			}

			if (burnTime == 0 && slots[0] != null) {
				int fuelValue = TileEntityFurnace.getItemBurnTime(slots[0]);
				if (fuelValue > 0) {
					slots[0].stackSize--;
					if (slots[0].stackSize == 0)
						slots[0] = slots[0].getItem().getContainerItem(slots[0]);
					burnTime = totalBurnTime = fuelValue;
					dirty = true;
				}
			}
			
			lumenBuffer = Math.max(0, Math.min(lumenBuffer, LUMEN_BUFFER_SIZE));
			
			if (lumenBuffer >= LUMEN_PACKET_SIZE) {
				Set<ILumenAcceptor> acceptors = new HashSet<>();
				for (int i = 0; i < 4 && acceptors.isEmpty(); i++) {
					int xOrig = xCoord - i, yOrig = yCoord - i, zOrig = zCoord - i;
					int xTar = xCoord + i, yTar = yCoord + i, zTar = zCoord + i;
					for (int x = xOrig; x <= xTar; x++) {
						for (int y = yOrig; y <= yTar; y++) {
							for (int z = zOrig; z <= zTar; z++) {
								TileEntity te;
								if ((te = worldObj.getTileEntity(x, y, z)) != null) {
									if (te instanceof ILumenAcceptor && ((ILumenAcceptor)te).canAcceptEnergy(LUMEN_PACKET_SIZE, this))
										acceptors.add((ILumenAcceptor)te);
								}
							}
						}
					}
				}
				if (!acceptors.isEmpty()) {
					int split = (int)Math.floor((float)LUMEN_PACKET_SIZE / (float)acceptors.size());
					for (ILumenAcceptor acceptor : acceptors)
						acceptor.acceptEnergy(split, this);
					lumenBuffer -= LUMEN_PACKET_SIZE;
				}
			}
			if (dirty)
				this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		burnTime = nbt.getShort("BurnTime");
		work = nbt.getShort("Work");
		workNeeded = nbt.getShort("WorkNeeded");
		frontFace = nbt.getByte("FrontFace");
		totalBurnTime = nbt.getShort("TotalBurnTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short)lumenBuffer);
		nbt.setShort("BurnTime", (short)burnTime);
		nbt.setShort("Work", (short)work);
		nbt.setShort("WorkNeeded", (short)workNeeded);
		nbt.setByte("FrontFace", (byte)frontFace);
		nbt.setShort("TotalBurnTime", (short)totalBurnTime);
	}

	@Override
	public int getMaximumEnergy() {
		return LUMEN_BUFFER_SIZE;
	}

	@Override
	public void provideEnergy(int amount, ILumenAcceptor destination) {
		destination.acceptEnergy(amount, this);
	}
	
	public int getBurnTime() {
		return burnTime;
	}

	public float getBurnTimePercentage() {
		return (float)burnTime / (float)totalBurnTime;
	}
	
	public int getWork() {
		return work;
	}
	
	public int getWorkNeeded() {
		return workNeeded;
	}

}
