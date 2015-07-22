package io.github.phantamanta44.cliffside;

import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabCliffside extends CreativeTabs {

	public CreativeTabCliffside() {
		super(LangConstants.CREATIVE_TAB_NAME);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM_GS);
	}
	
	@Override
	public Item getTabIconItem() {
		return CSItems.matDust;
	}
	
	public static class NodeTab extends CreativeTabs {
		
		public NodeTab() {
			super(LangConstants.CREATIVE_NODE_TAB_NAME);
		}
		
		@Override
		public ItemStack getIconItemStack() {
			return new ItemStack(CSBlocks.gsNode);
		}
		
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(CSBlocks.gsNode);
		}
		
	}
	
}
