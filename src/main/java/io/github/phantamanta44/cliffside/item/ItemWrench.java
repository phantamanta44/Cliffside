package io.github.phantamanta44.cliffside.item;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemWrench extends ItemModSubs {
	
	public static final int WRENCH = 0, DEBUG = 1, READER = 2;
	
	public ItemWrench() {
		super(3);
		maxStackSize = 1;
		setUnlocalizedName(ItemConstants.WRENCH_NAME);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		switch (stack.getItemDamage()) {
		case WRENCH:
			info.add(StatCollector.translateToLocal(LangConstants.WRENCH_DESC));
			break;
		case READER:
			info.add(StatCollector.translateToLocal(LangConstants.READER_DESC));
			break;
		}
	}
	
}
