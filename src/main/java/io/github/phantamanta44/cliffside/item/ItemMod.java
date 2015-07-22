package io.github.phantamanta44.cliffside.item;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMod extends Item {

	public ItemMod() {
		super();
		setCreativeTab(ModCliffside.tabCS);
	}

	@Override
	public Item setUnlocalizedName(String name) {
		GameRegistry.registerItem(this, name);
		return super.setUnlocalizedName(name);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemstack) {
		return super.getUnlocalizedNameInefficiently(itemstack).replaceAll("item\\.", "item." + GlobalConstants.MOD_PREF);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		itemIcon = IconHelper.forItem(registry, this);
	}

}
