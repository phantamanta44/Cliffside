package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModSword extends ItemSword {

	protected EnumRarity rarity = EnumRarity.common;
	protected String tooltip;
	
	public ItemModSword(ToolMaterial material) {
		super(material);
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
	
	public ItemModSword setRarity(EnumRarity level) {
		rarity = level;
		return this;
	}
	
	public ItemModSword setTooltip(String key) {
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
			info.add(String.format("Durability: %s%d%%", color, (int)(damagePercent * 100)));
		}
		if (tooltip != null && !tooltip.isEmpty())
			info.add(StatCollector.translateToLocal(tooltip));
	}
	
	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}
	
}
