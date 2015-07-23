package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.IDirectional;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenProvider;
import io.github.phantamanta44.cliffside.tile.base.IWrenchable;

import java.lang.reflect.Field;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.ChunkCoordIntPair;

public class TileLeylineAggregator extends TileEntity implements IDirectional, ILumenProvider, IWrenchable, IDebuggable {

	private static int LUMEN_BUFFER_SIZE = 16, LUMEN_PACKET_SIZE = 4;
	private int frontFace = -1;
	private int lumenBuffer = 0;
	private int work = 0;
	
	public TileLeylineAggregator() {
		super();
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
		EntityItem ent = new EntityItem(worldObj, xCoord + f, yCoord + f1, zCoord + f2, new ItemStack(CSBlocks.machine, 1, BlockMachine.LEYLINE_AGGRO));
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
		player.addChatMessage(new ChatComponentText("Work needed: " + getWorkNeeded()));
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
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			work++;
			int workNeeded = getWorkNeeded();
			int dim = worldObj.getWorldInfo().getVanillaDimension();
			if (dim == -1 || dim == 1)
				workNeeded *= 0.75;
			if (work >= workNeeded) {
				work = 0;
				lumenBuffer++;
			}
			lumenBuffer = Math.max(0, Math.min(lumenBuffer, LUMEN_BUFFER_SIZE));
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		work = nbt.getShort("Work");
		frontFace = nbt.getByte("FrontFace");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short)lumenBuffer);
		nbt.setShort("Work", (short)work);
		nbt.setByte("FrontFace", (byte)frontFace);
	}

	@Override
	public int getMaximumEnergy() {
		return LUMEN_BUFFER_SIZE;
	}

	@Override
	public void provideEnergy(int amount, ILumenAcceptor destination) {
		destination.acceptEnergy(amount, this);
	}
	
	public int getWork() {
		return work;
	}
	
	public int getWorkNeeded() {
		ChunkCoordIntPair chunk = worldObj.getChunkFromBlockCoords(xCoord, zCoord).getChunkCoordIntPair();
		int cX = chunk.getCenterXPos(), cZ = chunk.getCenterZPosition();
		double dist = Math.hypot(xCoord - cX, zCoord - cZ);
		return (int)(1500 / Math.sqrt(dist + 1));
	}

}
