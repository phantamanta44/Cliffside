package io.github.phantamanta44.cliffside.item.armor;

import java.util.List;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ArmorNintendium extends ItemModArmor implements ISpecialArmor {
	
	public ArmorNintendium(int type) {
		super(type, CSMaterialTypes.armorNintendium);
		armorSetName = "nintendium";
	}
	
	@Override
	public boolean getIsRepairable(ItemStack a, ItemStack b) {
		if (b.getItem().equals(CSItems.matIngot) && b.getItemDamage() == ItemIngot.NINTENDIUM)
			return true;
		return super.getIsRepairable(a, b);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		info.add(StatCollector.translateToLocal(LangConstants.UNBREAKABLE_DESC));
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		return;
	}
	
	public static class ItemNintendiumHelmet extends ArmorNintendium {
		
		public ItemNintendiumHelmet() {
			super(GlobalConstants.ARMOR_HELM);
			setUnlocalizedName(ItemConstants.NINT_HELM_NAME);
		}
		
	}
	
	public static class ItemNintendiumChestplate extends ArmorNintendium {
		
		public ItemNintendiumChestplate() {
			super(GlobalConstants.ARMOR_CHEST);
			setUnlocalizedName(ItemConstants.NINT_CHESTPLATE_NAME);
		}
		
	}
	
	public static class ItemNintendiumLeggings extends ArmorNintendium {
		
		public ItemNintendiumLeggings() {
			super(GlobalConstants.ARMOR_LEGS);
			setUnlocalizedName(ItemConstants.NINT_LEGS_NAME);
		}
		
	}
	
	public static class ItemNintendiumBoots extends ArmorNintendium {
		
		public ItemNintendiumBoots() {
			super(GlobalConstants.ARMOR_BOOTS);
			setUnlocalizedName(ItemConstants.NINT_BOOTS_NAME);
		}
		
	}
	
}
