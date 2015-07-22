package io.github.phantamanta44.cliffside.inventory;

import io.github.phantamanta44.cliffside.tile.TileSmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerSmelter extends Container {

	protected TileSmelter tile;
	
	public ContainerSmelter(InventoryPlayer ipl, TileSmelter te) {
		tile = te;
		addSlotToContainer(new Slot(te, 0, 56, 35));
		addSlotToContainer(new Slot(te, 1, 116, 35));
		addPlayerInventory(ipl);
	}
	
	protected void addPlayerInventory(InventoryPlayer ipl) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j)
				this.addSlotToContainer(new Slot(ipl, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		}

		for (int i = 0; i < 9; ++i)
			this.addSlotToContainer(new Slot(ipl, i, 8 + i * 18, 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.isUseableByPlayer(player);
	}
	
	public float getProgress() {
		return tile.getProgress();
	}
	
	public float getEnergy() {
		return (float)tile.getStoredEnergy() / (float)tile.getMaximumEnergy();
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack result = null;
		Slot slot = getSlot(slotIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			result = stack.copy();
			if (slotIndex == 1) {
				if (!mergeItemStack(stack, 2, 38, true))
					return null;
				slot.onSlotChange(stack, result);
			}
			else if (slotIndex != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(stack) != null) {
					if (!this.mergeItemStack(stack, 0, 1, false))
						return null;
				}
				else if (slotIndex >= 2 && slotIndex < 29) {
					if (!this.mergeItemStack(stack, 29, 38, false))
						return null;
				}
				else if (slotIndex >= 29 && slotIndex < 38) {
					if (!this.mergeItemStack(stack, 2, 29, false))
						return null;
				}
			}
			else if (!this.mergeItemStack(stack, 2, 38, false))
				return null;
			if (stack.stackSize <= 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
			if (stack.stackSize == result.stackSize)
				return null;
			slot.onPickupFromSlot(player, stack);
		}
		return result;
	}

	public TileSmelter getTile() {
		return tile;
	}
	
}
