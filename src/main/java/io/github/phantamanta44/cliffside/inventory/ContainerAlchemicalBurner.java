package io.github.phantamanta44.cliffside.inventory;

import io.github.phantamanta44.cliffside.tile.TileAlchemicalBurner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerAlchemicalBurner extends Container {

	protected TileAlchemicalBurner tile;
	
	public ContainerAlchemicalBurner(InventoryPlayer ipl, TileAlchemicalBurner te) {
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
		return (float)tile.getWork() / (float)tile.getWorkNeeded();
	}
	
	public float getBurnTimePercentage() {
		return tile.getBurnTimePercentage();
	}
	
	public float getEnergyPercentage() {
		return (float)tile.getStoredEnergy() / (float)tile.getMaximumEnergy();
	}
	
	public TileAlchemicalBurner getTile() {
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
				if (TileEntityFurnace.isItemFuel(stack)) {
					if (!this.mergeItemStack(stack, 0, 1, false))
						return null;
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
