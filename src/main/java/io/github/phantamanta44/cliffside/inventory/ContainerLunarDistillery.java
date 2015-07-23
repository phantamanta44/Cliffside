package io.github.phantamanta44.cliffside.inventory;

import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import io.github.phantamanta44.cliffside.tile.TileLunarDistillery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLunarDistillery extends Container {

	protected TileLunarDistillery tile;
	
	public ContainerLunarDistillery(InventoryPlayer ipl, TileLunarDistillery te) {
		tile = te;
		addSlotToContainer(new Slot(te, 0, 80, 43));
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
	
	public float getProgressPercentage() {
		return tile.getWorkPercentage();
	}
	
	public float getEnergyPercentage() {
		return (float)tile.getStoredEnergy() / (float)tile.getMaximumEnergy();
	}
	
	public TileLunarDistillery getTile() {
		return tile;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack result = null;
		Slot slot = getSlot(slotIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			result = stack.copy();
			if (slotIndex != 0) {
				if (stack.getItem().equals(Items.glass_bottle) || (stack.getItem().equals(CSItems.matResource) && stack.getItemDamage() == ItemResource.BOTTLE_MS)) {
					if (tile.getStackInSlot(0) != null)
						return null;
					ItemStack clonedStack = stack.copy();
					clonedStack.stackSize = 1;
					if (!this.mergeItemStack(clonedStack, 0, 1, false))
						return null;
					else {
						stack.stackSize--;
						result.stackSize--;
					}
				}
				else if (slotIndex >= 1 && slotIndex < 28) {
					if (!this.mergeItemStack(stack, 28, 37, false))
						return null;
				}
				else if (slotIndex >= 28 && slotIndex < 37) {
					if (!this.mergeItemStack(stack, 1, 28, false))
						return null;
				}
			}
			else if (!this.mergeItemStack(stack, 1, 37, false))
				return null;
			if (stack.stackSize <= 0)
				slot.putStack((ItemStack)null);
			else
				slot.onSlotChanged();
			if (stack.stackSize == result.stackSize)
				return null;
			slot.onPickupFromSlot(player, stack);
		}
		return result;
	}
	
}
