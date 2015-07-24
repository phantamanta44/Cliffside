package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.recipe.machine.EnergizerRecipeHandler;
import io.github.phantamanta44.cliffside.recipe.machine.EnergizerRecipeHandler.RecipeEnergizer;
import io.github.phantamanta44.cliffside.tile.base.IActiveMachine;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.IDirectional;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenAcceptor;
import io.github.phantamanta44.cliffside.tile.base.IWrenchable;
import io.github.phantamanta44.cliffside.tile.base.TileMod;
import io.github.phantamanta44.cliffside.util.BlockWithMeta;

import java.lang.reflect.Field;
import java.util.Map.Entry;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class TileGlowstoneEnergizer extends TileMod implements ILumenAcceptor, IWrenchable, IDirectional, IActiveMachine, IDebuggable {

	private static final int LUMEN_BUFFER_SIZE = 64;
	private int lumenBuffer = 0, work = 0;
	private int frontFace = -1;
	private int[] currentBlock = new int[3];
	private RecipeEnergizer currentRecipe;
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
	public boolean isActive() {
		return isActive;
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
	public void onWrenchUse(int face, EntityPlayer player, ItemStack wrench) {
		rotateClockwise();
	}

	@Override
	public void onWrenchUseSneaking(int face, EntityPlayer player, ItemStack wrench) {
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air, 0, 3);
		float f = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f1 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
		EntityItem ent = new EntityItem(worldObj, xCoord + f, yCoord + f1, zCoord + f2, new ItemStack(CSBlocks.machine, 1, BlockMachine.ENERGIZER));
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
	public void acceptEnergy(int amount, ILumenProvider source) {
		lumenBuffer = Math.min(lumenBuffer + amount, LUMEN_BUFFER_SIZE);
	}

	@Override
	public boolean canAcceptEnergy(int amount, ILumenProvider source) {
		return lumenBuffer < LUMEN_BUFFER_SIZE;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (currentRecipe != null) {
				if (!worldObj.getBlock(currentBlock[0], currentBlock[1], currentBlock[2]).equals(currentRecipe.getInput().getBlock()) || worldObj.getBlockMetadata(currentBlock[0], currentBlock[1], currentBlock[2]) != currentRecipe.getInput().getMeta()) {
					work = 0;
					currentRecipe = null;
					isActive = false;
				}
				else {
					
					if (lumenBuffer > 0) {
						lumenBuffer--;
						work++;
					}
					if (work >= currentRecipe.getWorkNeeded()) {
						work = 0;
						worldObj.setBlock(currentBlock[0], currentBlock[1], currentBlock[2], currentRecipe.getOutput().getBlock(), currentRecipe.getOutput().getMeta(), 3);
						for (int i = 0; i < 32; i++)
							worldObj.spawnParticle("smoke", worldObj.rand.nextFloat() + currentBlock[0], worldObj.rand.nextFloat() + currentBlock[1], worldObj.rand.nextFloat() + currentBlock[2], 0.0, 0.0, 0.0);
						currentRecipe = null;
						isActive = false;
					}
				}
			}
			else {
				int xOrig = xCoord - 3, yOrig = yCoord - 3, zOrig = zCoord - 3;
				int xTar = xCoord + 3, yTar = yCoord + 3, zTar = zCoord + 3;
				for (int x = xOrig; x <= xTar; x++) {
					for (int y = yOrig; y <= yTar; y++) {
						for (int z = zOrig; z <= zTar; z++) {
							BlockWithMeta bwm = new BlockWithMeta(worldObj, x, y, z);
							for (Entry<BlockWithMeta, RecipeEnergizer> recipe : EnergizerRecipeHandler.recipeList.entrySet()) {
								if (recipe.getKey().equals(bwm)) {
									currentRecipe = recipe.getValue();
									currentBlock = new int[] {x, y, z};
									isActive = true;
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		work = nbt.getShort("Work");
		frontFace = nbt.getByte("FrontFace");
		currentBlock = nbt.getIntArray("CurrentBlock");
		isActive = nbt.getBoolean("IsActive");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short)lumenBuffer);
		nbt.setShort("Work", (short)work);
		nbt.setByte("FrontFace", (byte)frontFace);
		nbt.setIntArray("CurrentBlock", currentBlock);
		nbt.setBoolean("IsActive", isActive);
	}

}
