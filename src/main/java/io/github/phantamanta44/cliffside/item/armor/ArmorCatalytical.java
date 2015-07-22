package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.item.ItemStack;

public class ArmorCatalytical extends ItemModArmor {
	
	public ArmorCatalytical(int type) {
		super(type, CSMaterialTypes.armorCatalytical);
		armorSetName = "catalytical";
	}
	
	@Override
	public boolean getIsRepairable(ItemStack a, ItemStack b) {
		if (b.getItem().equals(CSItems.matIngot) && b.getItemDamage() == ItemIngot.CATAL)
			return true;
		return super.getIsRepairable(a, b);
	}
	
	public static class ItemCatalyticalHelmet extends ArmorCatalytical {
		
		public ItemCatalyticalHelmet() {
			super(GlobalConstants.ARMOR_HELM);
			setUnlocalizedName(ItemConstants.CATAL_HELM_NAME);
		}
		
	}
	
	public static class ItemCatalyticalChestplate extends ArmorCatalytical {
		
		public ItemCatalyticalChestplate() {
			super(GlobalConstants.ARMOR_CHEST);
			setUnlocalizedName(ItemConstants.CATAL_CHESTPLATE_NAME);
		}
		
	}
	
	public static class ItemCatalyticalLeggings extends ArmorCatalytical {
		
		public ItemCatalyticalLeggings() {
			super(GlobalConstants.ARMOR_LEGS);
			setUnlocalizedName(ItemConstants.CATAL_LEGS_NAME);
		}
		
	}
	
	public static class ItemCatalyticalBoots extends ArmorCatalytical {
		
		public ItemCatalyticalBoots() {
			super(GlobalConstants.ARMOR_BOOTS);
			setUnlocalizedName(ItemConstants.CATAL_BOOTS_NAME);
		}
		
	}
	
}
