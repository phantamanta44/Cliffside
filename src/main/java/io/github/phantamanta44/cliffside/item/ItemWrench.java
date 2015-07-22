package io.github.phantamanta44.cliffside.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;

public class ItemWrench extends ItemModSubs {
	
	public static final int WRENCH = 0, DEBUG = 1, READER = 2;
	
	public ItemWrench() {
		super(3);
		maxStackSize = 1;
		setUnlocalizedName(ItemConstants.WRENCH_NAME);
	}
	
}
