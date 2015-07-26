package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModArmor extends ItemArmor {
	
	protected String armorSetName, tooltip;
	protected EnumRarity rarity = EnumRarity.common;

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
	
	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}
	
	public ItemModArmor setRarity(EnumRarity level) {
		rarity = level;
		return this;
	}
	
	public ItemModArmor setTooltip(String key) {
		tooltip = key;
		return this;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		if (stack.getItem().isDamageable()) {
			float damagePercent = (float)(stack.getMaxDamage() - stack.getItemDamage()) / (float)stack.getMaxDamage();
			String color = (damagePercent == 1F ? EnumChatFormatting.AQUA : (damagePercent > 0.7F ? EnumChatFormatting.GREEN : (damagePercent > 0.5F ? EnumChatFormatting.YELLOW : (damagePercent > 0.2F ? EnumChatFormatting.GOLD : EnumChatFormatting.DARK_RED)))).toString();
			info.add(String.format("Durability: %s%d%%", color, (int)Math.max(damagePercent * 100, 1)));
		}
		if (tooltip != null && !tooltip.isEmpty())
			info.add(StatCollector.translateToLocal(tooltip));
	}

}
