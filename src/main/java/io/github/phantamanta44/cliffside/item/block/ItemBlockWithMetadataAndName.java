package io.github.phantamanta44.cliffside.item.block;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockWithMetadataAndName extends ItemBlockWithMetadata {
	
	public ItemBlockWithMetadataAndName(Block block) {
		super(block, block);
	}
	
	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("tile.", "tile." + GlobalConstants.MOD_PREF);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
	}
		
}

