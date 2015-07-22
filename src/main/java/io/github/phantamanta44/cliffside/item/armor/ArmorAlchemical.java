package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.item.ItemStack;

public class ArmorAlchemical extends ItemModArmor {
	
	public ArmorAlchemical(int type) {
		super(type, CSMaterialTypes.armorAlchemical);
		armorSetName = "alchemical";
	}
	
	@Override
	public boolean getIsRepairable(ItemStack a, ItemStack b) {
		if (b.getItem().equals(CSItems.matIngot) && b.getItemDamage() == ItemIngot.ALCHEM)
			return true;
		return super.getIsRepairable(a, b);
	}
	
	public static class ItemAlchemicalHelmet extends ArmorAlchemical {
		
		public ItemAlchemicalHelmet() {
			super(GlobalConstants.ARMOR_HELM);
			setUnlocalizedName(ItemConstants.ALCHEM_HELM_NAME);
		}
		
	}
	
	public static class ItemAlchemicalChestplate extends ArmorAlchemical {
		
		public ItemAlchemicalChestplate() {
			super(GlobalConstants.ARMOR_CHEST);
			setUnlocalizedName(ItemConstants.ALCHEM_CHESTPLATE_NAME);
		}
		
	}
	
	public static class ItemAlchemicalLeggings extends ArmorAlchemical {
		
		public ItemAlchemicalLeggings() {
			super(GlobalConstants.ARMOR_LEGS);
			setUnlocalizedName(ItemConstants.ALCHEM_LEGS_NAME);
		}
		
	}
	
	public static class ItemAlchemicalBoots extends ArmorAlchemical {
		
		public ItemAlchemicalBoots() {
			super(GlobalConstants.ARMOR_BOOTS);
			setUnlocalizedName(ItemConstants.ALCHEM_BOOTS_NAME);
		}
		
	}
	
}
