package io.github.phantamanta44.cliffside.item.resource;

import java.util.List;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.item.ItemModSubs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemIngot extends ItemModSubs {

	public static final int ALCHEM = 0, CATAL = 1, MITHRIL = 2, NINTENDIUM = 3, SILVER = 4,
			QS = 5;
	
	public ItemIngot() {
		super(6);
		setUnlocalizedName(ItemConstants.INGOT_NAME);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case NINTENDIUM:
			return EnumRarity.epic;
		case QS:
			return EnumRarity.uncommon;
		default:
			return EnumRarity.common;
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		switch (stack.getItemDamage()) {
		case NINTENDIUM:
			info.add(StatCollector.translateToLocal(LangConstants.NINTENDIUM_DESC));
			break;
		}
	}

}
