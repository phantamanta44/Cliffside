package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.TileConstants;
import io.github.phantamanta44.cliffside.recipe.machine.DisintegratorRecipeHandler;
import io.github.phantamanta44.cliffside.recipe.machine.DisintegratorRecipeHandler.RecipeDisintegrator;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class TileDisintegrator extends TileBasicInventory implements IActiveMachine, IDebuggable, IDirectional, ILumenAcceptor, IWrenchable {

	public TileDisintegrator() {
		super(TileConstants.DISINTEGRATOR_NAME, 2);
	}

	private static final int LUMEN_BUFFER_SIZE = 64, TICKS_PER_WORK = 15;
	private int lumenBuffer = 0, work = 0, subWork = 0, workNeeded = 0;
	private int frontFace = -1;
	private RecipeDisintegrator currentRecipe;
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
						BlockMachine.DISINTEGRATOR));
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
		if (slots[1] != null) {
			if (!slots[1].isItemEqual(currentRecipe.getOutput()))
				return false;
			if (slots[1].stackSize + currentRecipe.getOutput().stackSize > 64)
				return false;
		}
		if (slots[0].stackSize < currentRecipe.getInput().stackSize)
			return false;
		return true;
	}

	public void doCrafting() {
		if (slots[1] == null)
			slots[1] = currentRecipe.getOutput().copy();
		else if (slots[1].isItemEqual(currentRecipe.getOutput()))
			slots[1].stackSize += currentRecipe.getOutput().stackSize;
		slots[0].stackSize -= currentRecipe.getInput().stackSize;
		if (slots[0].stackSize <= 0)
			slots[0] = null;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			boolean dirty = false;
			if (currentRecipe == null) {
				if (slots[0] != null) {
					currentRecipe = DisintegratorRecipeHandler.recipeList.getRecipeFor(slots[0]);
					if (currentRecipe != null)
						workNeeded = currentRecipe.getWorkNeeded();
				}
				isActive = false;
				work = 0;
				subWork = 0;
			}
			else {
				if (slots[0] == null || !currentRecipe.getInput().isItemEqual(slots[0])) {
					work = 0;
					subWork = 0;
					currentRecipe = null;
					isActive = false;
				}
				else if (!canDoCrafting()) {
					work = 0;
					subWork = 0;
					isActive = false;
				}
				else {
					if (lumenBuffer > 0) {
						subWork++;
						if (subWork > TICKS_PER_WORK) {
							subWork = 0;
							lumenBuffer--;
							work++;
							isActive = true;
						}
					}
					else {
						subWork = 0;
						isActive = false;
					}
					if (work >= currentRecipe.getWorkNeeded()) {
						doCrafting();
						dirty = true;
						work = 0;
					}
				}
				if (dirty)
					this.markDirty();
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		work = nbt.getShort("Work");
		workNeeded = nbt.getShort("WorkNeeded");
		subWork = nbt.getShort("SubWork");
		frontFace = nbt.getByte("FrontFace");
		isActive = nbt.getBoolean("IsActive");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short) lumenBuffer);
		nbt.setShort("Work", (short) work);
		nbt.setShort("WorkNeeded", (short) workNeeded);
		nbt.setShort("SubWork", (short) subWork);
		nbt.setByte("FrontFace", (byte) frontFace);
		nbt.setBoolean("IsActive", isActive);
	}

	public float getProgress() {
		if (isActive || subWork > 0 || work > 0) {
			float totalSubWork = TICKS_PER_WORK * workNeeded;
			float currentSubWork = TICKS_PER_WORK * work + subWork;
			return currentSubWork / totalSubWork;
		}
		return 0F;
	}

}
