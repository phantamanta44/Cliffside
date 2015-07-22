package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.TileConstants;
import io.github.phantamanta44.cliffside.recipe.machine.DisintegratorRecipeHandler;
import io.github.phantamanta44.cliffside.tile.base.IActiveMachine;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.IDirectional;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenAcceptor;
import io.github.phantamanta44.cliffside.tile.base.IWrenchable;
import io.github.phantamanta44.cliffside.tile.base.TileBasicInventory;

import java.lang.reflect.Field;
import java.util.Map.Entry;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class TileSmelter extends TileBasicInventory implements IActiveMachine, IDebuggable, IDirectional, ILumenAcceptor, IWrenchable {

	public TileSmelter() {
		super(TileConstants.DISINTEGRATOR_NAME, 2);
	}

	private static final int LUMEN_BUFFER_SIZE = 64, WORK_PER_OPERATION = 20;
	private int lumenBuffer = 0, work = 0;
	private int frontFace = -1;
	private boolean isActive;

	@Override
	public int getStoredEnergy() {
		return lumenBuffer;
	}

	@Override
	public int getMaximumEnergy() {
		return LUMEN_BUFFER_SIZE;
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
	public void onWrenchUse(int face, EntityPlayer player, ItemStack wrench) {
		rotateClockwise();
	}

	@Override
	public void onWrenchUseSneaking(int face, EntityPlayer player, ItemStack wrench) {
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air, 0, 3);
		float f = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f1 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		EntityItem ent = new EntityItem(worldObj, xCoord + f, yCoord + f1,
				zCoord + f2, new ItemStack(CSBlocks.machine, 1,
						BlockMachine.SMELTER));
		float f3 = 0.05F;
		ent.motionX = (double) ((float) worldObj.rand.nextGaussian() * f3);
		ent.motionY = (double) ((float) worldObj.rand.nextGaussian() * f3 + 0.2F);
		ent.motionZ = (double) ((float) worldObj.rand.nextGaussian() * f3);
		worldObj.spawnEntityInWorld(ent);
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
	public void onDebug(EntityPlayer player) {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields) {
			try {
				player.addChatMessage(new ChatComponentText(f.getName() + ": "
						+ f.get(this).toString()));
			} catch (Throwable th) {
			}
		}
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	public boolean canDoCrafting() {
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(slots[0]);
		if (result == null)
			return false;
		if (slots[1] != null) {
			if (!slots[1].isItemEqual(result))
				return false;
			if (slots[1].stackSize + result.stackSize > 64)
				return false;
		}
		return true;
	}

	public void doCrafting() {
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(slots[0]);
		if (slots[1] == null)
			slots[1] = result.copy();
		else if (slots[1].isItemEqual(result))
			slots[1].stackSize += result.stackSize;
		slots[0].stackSize--;
		if (slots[0].stackSize <= 0)
			slots[0] = null;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			boolean dirty = false;
			if (slots[0] != null) {
				if (canDoCrafting()) {
					if (lumenBuffer > 0) {
						isActive = true;
						work++;
					}
					else {
						isActive = false;
						work = 0;
					}
					if (work > WORK_PER_OPERATION) {
						work = 0;
						lumenBuffer--;
						doCrafting();
					}
				}
				else {
					isActive = false;
					work = 0;
				}
			}
			else {
				isActive = false;
				work = 0;
			}
			if (dirty)
				this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		work = nbt.getShort("Work");
		frontFace = nbt.getByte("FrontFace");
		isActive = nbt.getBoolean("IsActive");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short) lumenBuffer);
		nbt.setShort("Work", (short) work);
		nbt.setByte("FrontFace", (byte) frontFace);
		nbt.setBoolean("IsActive", isActive);
	}

	public float getProgress() {
		return (float)work / (float)WORK_PER_OPERATION;
	}

}
