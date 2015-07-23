package io.github.phantamanta44.cliffside.item.block;

import io.github.phantamanta44.cliffside.block.BlockTransparent;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockTransparent extends ItemBlockWithMetadataAndName {

	public ItemBlockTransparent(Block block) {
		super(block);
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case BlockTransparent.QS_GLASS:
			return EnumRarity.uncommon;
		default:
			return EnumRarity.common;
		}
	}

}
