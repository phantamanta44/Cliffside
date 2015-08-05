package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IWarpingGear;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IRunicArmor"), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IWarpingGear")})
public class ArmorNintendium extends ItemModArmor implements ISpecialArmor, IRunicArmor, IWarpingGear {
	
	public ArmorNintendium(int type) {
		super(type, CSMaterialTypes.armorNintendium);
		armorSetName = "nintendium";
		setTooltip(LangConstants.UNBREAKABLE_DESC);
		setRarity(EnumRarity.epic);
	}
	
	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		// NO-OP
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

	@Override
	@Optional.Method(modid="Thaumcraft")
	public int getWarp(ItemStack itemstack, EntityPlayer player) {
		return 1;
	}

	@Override
	@Optional.Method(modid="Thaumcraft")
	public int getRunicCharge(ItemStack itemstack) {
		return 0;
	}
	
}
