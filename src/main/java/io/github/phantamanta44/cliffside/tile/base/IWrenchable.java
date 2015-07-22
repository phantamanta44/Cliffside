package io.github.phantamanta44.cliffside.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWrenchable {

	public void onWrenchUse(int face, EntityPlayer player, ItemStack wrench);
	
	public void onWrenchUseSneaking(int face, EntityPlayer player, ItemStack wrench);
	
}
