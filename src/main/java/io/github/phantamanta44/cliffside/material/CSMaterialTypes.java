package io.github.phantamanta44.cliffside.material;

import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class CSMaterialTypes {
	
	public static ArmorMaterial armorAlchemical = EnumHelper.addArmorMaterial("ALCHEMICAL", 18, new int[] {2, 6, 5, 3}, 25);
	public static ArmorMaterial armorCatalytical = EnumHelper.addArmorMaterial("CATALYTICAL", 19, new int[] {2, 6, 5, 3}, 26);
	public static ArmorMaterial armorMithril = EnumHelper.addArmorMaterial("MITHRIL", 14, new int[] {2, 5, 4, 1}, 34);
	public static ArmorMaterial armorNintendium = EnumHelper.addArmorMaterial("NINTENDIUM", 128, new int[] {2, 7, 5, 3}, 16);
	public static ArmorMaterial armorNintendiumEmpowered = EnumHelper.addArmorMaterial("NINTEMPOWERED", 128, new int[] {3, 8, 6, 3}, 42);
	
	public static ToolMaterial toolsAlchemical = EnumHelper.addToolMaterial("ALCHEMICAL", 5, 649, 8.0F, 2.5F, 25).setRepairItem(new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM));
	public static ToolMaterial toolsCatalytical = EnumHelper.addToolMaterial("CATALYTICAL", 6, 849, 12.0F, 3.0F, 26).setRepairItem(new ItemStack(CSItems.matIngot, 1, ItemIngot.CATAL));
	public static ToolMaterial toolsMithril = EnumHelper.addToolMaterial("MITHRIL", 4, 419, 7.0F, 2.0F, 34).setRepairItem(new ItemStack(CSItems.matIngot, 1, ItemIngot.MITHRIL));
	public static ToolMaterial toolsNintendium = EnumHelper.addToolMaterial("NINTENDIUM", 8, 9000, 14.0F, 5F, 16);
	public static ToolMaterial toolsNintendiumEmpowered = EnumHelper.addToolMaterial("NINTEMPOWERED", 8, 9000, 20.0F, 11F, 42);
	
}
