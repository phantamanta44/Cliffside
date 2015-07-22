package io.github.phantamanta44.cliffside.item.block;

import java.util.List;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockCreativeLumenSource extends ItemBlockWithMetadataAndName {

	public ItemBlockCreativeLumenSource(Block block) {
		super(block);
	}
	
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		info.add(StatCollector.translateToLocal(LangConstants.CREATIVE_DESC));
	}

}
