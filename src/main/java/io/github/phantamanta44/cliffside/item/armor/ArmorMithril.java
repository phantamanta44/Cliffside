package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IRunicArmor"), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IVisDiscountGear")})
public class ArmorMithril extends ItemModArmor implements IRunicArmor, IVisDiscountGear {
	
	public ArmorMithril(int type) {
		super(type, CSMaterialTypes.armorMithril);
		armorSetName = "mithril";
		setTooltip(LangConstants.MAGICAL_DESC);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack a, ItemStack b) {
		if (b.getItem().equals(CSItems.matIngot) && b.getItemDamage() == ItemIngot.MITHRIL)
			return true;
		return super.getIsRepairable(a, b);
	}
	
	public static class ItemMithrilHelmet extends ArmorMithril {
		
		public ItemMithrilHelmet() {
			super(GlobalConstants.ARMOR_HELM);
			setUnlocalizedName(ItemConstants.MITHRIL_HELM_NAME);
		}
		
	}
	
	public static class ItemMithrilChestplate extends ArmorMithril {
		
		public ItemMithrilChestplate() {
			super(GlobalConstants.ARMOR_CHEST);
			setUnlocalizedName(ItemConstants.MITHRIL_CHESTPLATE_NAME);
		}
		
	}
	
	public static class ItemMithrilLeggings extends ArmorMithril {
		
		public ItemMithrilLeggings() {
			super(GlobalConstants.ARMOR_LEGS);
			setUnlocalizedName(ItemConstants.MITHRIL_LEGS_NAME);
		}
		
	}
	
	public static class ItemMithrilBoots extends ArmorMithril {
		
		public ItemMithrilBoots() {
			super(GlobalConstants.ARMOR_BOOTS);
			setUnlocalizedName(ItemConstants.MITHRIL_BOOTS_NAME);
		}
		
	}

	@Override
	@Optional.Method(modid="Thaumcraft")
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		if (stack.getItem().equals(CSItems.mithrilChestplate))
			return 4;
		if (stack.getItem().equals(CSItems.mithrilLeggings))
			return 3;
		if (stack.getItem().equals(CSItems.mithrilBoots))
			return 2;
		return 2;
	}

	@Override
	@Optional.Method(modid="Thaumcraft")
	public int getRunicCharge(ItemStack itemstack) {
		return 0;
	}
	
}
