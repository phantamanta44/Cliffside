package io.github.phantamanta44.cliffside.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

public abstract class TileBasicInventory extends TileMod implements IInventory {

	protected String invName;
	protected ItemStack[] slots;
	
	public TileBasicInventory(String name, int size) {
		super();
		invName = name;
		slots = new ItemStack[size];
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		try {
			return slots[slot];
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		try {
			ItemStack stack = slots[slot];
			stack.stackSize -= amount;
			ItemStack newStack = new ItemStack(stack.getItem(), amount, stack.getItemDamage());
			if (stack.hasTagCompound())
				newStack.setTagCompound(stack.getTagCompound());
			return newStack;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		try {
			return slots[slot];
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack contents) {
		try {
			slots[slot] = contents;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String getInventoryName() {
		return StatCollector.translateToLocal(invName);
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList itemListTag = nbt.getTagList("Items", 10);
		for (int i = 0; i < itemListTag.tagCount(); i++) {	
			NBTTagCompound itemTag = itemListTag.getCompoundTagAt(i);
			short slot = itemTag.getShort("Slot");
			if (!itemTag.hasKey("NULLITEM")) {
				ItemStack stack = ItemStack.loadItemStackFromNBT(itemTag);
				slots[slot] = stack;
			}
			else
				slots[slot] = null;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList itemListTag = new NBTTagList();
		for (short i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				NBTTagCompound itemTag = new NBTTagCompound();
				slots[i].writeToNBT(itemTag);
				itemTag.setShort("Slot", i);
				itemListTag.appendTag(itemTag);
			}
			else {
				NBTTagCompound emptyTag = new NBTTagCompound();
				emptyTag.setByte("NULLITEM", (byte)0);
				emptyTag.setShort("Slot", i);
				itemListTag.appendTag(emptyTag);
			}
		}
		nbt.setTag("Items", itemListTag);
		super.writeToNBT(nbt);
	}
	
}
