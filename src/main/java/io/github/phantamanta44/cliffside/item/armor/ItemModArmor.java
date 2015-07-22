package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModArmor extends ItemArmor {
	
	protected String armorSetName;

	public ItemModArmor(int type) {
		super(CSMaterialTypes.armorAlchemical, 0, type);
		setCreativeTab(ModCliffside.tabCS);
	}
	
	public ItemModArmor(int type, ArmorMaterial material) {
		super(material, 0, type);
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

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return GlobalConstants.TEXPATH_ARMOUR + armorSetName + (this.armorType == 2 ? "_2.png" : "_1.png");
	}

}
