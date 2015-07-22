package io.github.phantamanta44.cliffside.item.resource;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.item.ItemModSubs;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemDust extends ItemModSubs {

	public static final int ALCHEM_GS = 0, BINDING = 1, ALCHEM = 2, CATAL = 3,
			MITHRIL = 4, NINTENDIUM = 5, SILVER = 6, QS = 7, IRON = 8, GOLD = 9;
	
	public ItemDust() {
		super(10);
		setUnlocalizedName(ItemConstants.DUST_NAME);
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
	
}
