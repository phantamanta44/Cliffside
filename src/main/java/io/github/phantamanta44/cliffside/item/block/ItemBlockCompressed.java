package io.github.phantamanta44.cliffside.item.block;

import io.github.phantamanta44.cliffside.block.BlockCompressed;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockCompressed extends ItemBlockWithMetadataAndName {

	public ItemBlockCompressed(Block block) {
		super(block);
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case BlockCompressed.COMP_NINT:
			return EnumRarity.epic;
		case BlockCompressed.COMP_QS:
			return EnumRarity.uncommon;
		default:
			return EnumRarity.common;
		}
	}

}
