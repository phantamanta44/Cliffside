package io.github.phantamanta44.cliffside.item.block;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockAlchemical extends ItemBlockWithMetadataAndName {

	public ItemBlockAlchemical(Block block) {
		super(block);
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case BlockAlchemical.NINT_MIX:
			return EnumRarity.rare;
		default:
			return EnumRarity.common;
		}
	}

}
