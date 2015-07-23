package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.TileConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import io.github.phantamanta44.cliffside.tile.base.IActiveMachine;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.IDirectional;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenAcceptor;
import io.github.phantamanta44.cliffside.tile.base.IWrenchable;
import io.github.phantamanta44.cliffside.tile.base.TileBasicInventory;

import java.lang.reflect.Field;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class TileLunarDistillery extends TileBasicInventory implements IDirectional, ILumenAcceptor, IWrenchable, IDebuggable, IActiveMachine {

	private static int LUMEN_BUFFER_SIZE = 64, MS_BUFFER_SIZE = 10000, WORK_PER_OPERATION = 1200;
	private int frontFace = -1;
	private int lumenBuffer = 0, msBuffer = 0;
	private int work = 0;
	
	public TileLunarDistillery() {
		super(TileConstants.LUNA_DISTILL_NAME, 1);
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
		player.addChatMessage(new ChatComponentText("canWork: " + canWork()));
		player.addChatMessage(new ChatComponentText("getWorkModifier: " + getWorkModifier()));
	}

	@Override
	public int getStoredEnergy() {
		return lumenBuffer;
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
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (isActive()) {
				int workAmt = getWorkModifier();
				work += workAmt;
				if (work > WORK_PER_OPERATION) {
					work = 0;
					lumenBuffer--;
					msBuffer += (workAmt * 3);
				}
			}
			if (slots[0] != null && slots[0].getItem().equals(Items.glass_bottle) && msBuffer >= 1000) {
				slots[0] = new ItemStack(CSItems.matResource, 1, ItemResource.BOTTLE_MS);
				msBuffer -= 1000;
			}
			msBuffer = Math.max(0, Math.min(msBuffer, MS_BUFFER_SIZE));
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		work = nbt.getShort("Work");
		frontFace = nbt.getByte("FrontFace");
		msBuffer = nbt.getShort("MoonshineBuffer");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short)lumenBuffer);
		nbt.setShort("Work", (short)work);
		nbt.setByte("FrontFace", (byte)frontFace);
		nbt.setShort("MoonshineBuffer", (short)msBuffer);
	}

	@Override
	public int getMaximumEnergy() {
		return LUMEN_BUFFER_SIZE;
	}
	
	public int getWorkModifier() {
		int moonPhaseMod = Math.abs(worldObj.getMoonPhase() - 4);
		return (int)(moonPhaseMod * Math.max(Math.sqrt(yCoord) / 4, 0.25));
	}
	
	public int getWork() {
		return work;
	}
	
	public float getWorkPercentage() {
		return (float)work / (float)WORK_PER_OPERATION;
	}

	@Override
	public void acceptEnergy(int amount, ILumenProvider source) {
		lumenBuffer = Math.min(lumenBuffer + amount, LUMEN_BUFFER_SIZE);
	}

	@Override
	public boolean canAcceptEnergy(int amount, ILumenProvider source) {
		return lumenBuffer < LUMEN_BUFFER_SIZE;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	public boolean canWork() {
		int time = (int)(worldObj.getWorldTime() % 24000);
		return (time > 13000 && time < 22650) && worldObj.getWorldInfo().getVanillaDimension() == 0 && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord);
	}
	
	public int getMoonshineBuffer() {
		return msBuffer;
	}
	
	public float getMoonshineBufferPercentage() {
		return (float)msBuffer / (float)MS_BUFFER_SIZE;
	}
	
	@Override
	public boolean isActive() {
		return canWork() && lumenBuffer > 0 && msBuffer < MS_BUFFER_SIZE;
	}

}
