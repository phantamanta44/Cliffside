package io.github.phantamanta44.cliffside.item;

import io.github.phantamanta44.cliffside.item.armor.ArmorAlchemical.ItemAlchemicalBoots;
import io.github.phantamanta44.cliffside.item.armor.ArmorAlchemical.ItemAlchemicalChestplate;
import io.github.phantamanta44.cliffside.item.armor.ArmorAlchemical.ItemAlchemicalHelmet;
import io.github.phantamanta44.cliffside.item.armor.ArmorAlchemical.ItemAlchemicalLeggings;
import io.github.phantamanta44.cliffside.item.armor.ArmorCatalytical.ItemCatalyticalBoots;
import io.github.phantamanta44.cliffside.item.armor.ArmorCatalytical.ItemCatalyticalChestplate;
import io.github.phantamanta44.cliffside.item.armor.ArmorCatalytical.ItemCatalyticalHelmet;
import io.github.phantamanta44.cliffside.item.armor.ArmorCatalytical.ItemCatalyticalLeggings;
import io.github.phantamanta44.cliffside.item.armor.ArmorMithril.ItemMithrilBoots;
import io.github.phantamanta44.cliffside.item.armor.ArmorMithril.ItemMithrilChestplate;
import io.github.phantamanta44.cliffside.item.armor.ArmorMithril.ItemMithrilHelmet;
import io.github.phantamanta44.cliffside.item.armor.ArmorMithril.ItemMithrilLeggings;
import io.github.phantamanta44.cliffside.item.armor.ArmorNintendium.ItemNintendiumBoots;
import io.github.phantamanta44.cliffside.item.armor.ArmorNintendium.ItemNintendiumChestplate;
import io.github.phantamanta44.cliffside.item.armor.ArmorNintendium.ItemNintendiumHelmet;
import io.github.phantamanta44.cliffside.item.armor.ArmorNintendium.ItemNintendiumLeggings;
import io.github.phantamanta44.cliffside.item.armor.ItemZFGBoots;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.item.resource.ItemNugget;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import net.minecraft.item.Item;

public class CSItems {

	public static Item matDust;
	public static Item matIngot;
	public static Item matNugget;
	public static Item matResource;
	public static Item wrench;
	public static Item zfgBoots;
	public static Item alchemHelmet;
	public static Item alchemChestplate;
	public static Item alchemLeggings;
	public static Item alchemBoots;
	public static Item catalHelmet;
	public static Item catalChestplate;
	public static Item catalLeggings;
	public static Item catalBoots;
	public static Item mithrilHelmet;
	public static Item mithrilChestplate;
	public static Item mithrilLeggings;
	public static Item mithrilBoots;
	public static Item nintHelmet;
	public static Item nintChestplate;
	public static Item nintLeggings;
	public static Item nintBoots;
	
	public static void init() {
		matDust = new ItemDust();
		matIngot = new ItemIngot();
		matNugget = new ItemNugget();
		matResource = new ItemResource();
		wrench = new ItemWrench();
		zfgBoots = new ItemZFGBoots();
		alchemHelmet = new ItemAlchemicalHelmet();
		alchemChestplate = new ItemAlchemicalChestplate();
		alchemLeggings = new ItemAlchemicalLeggings();
		alchemBoots = new ItemAlchemicalBoots();
		catalHelmet = new ItemCatalyticalHelmet();
		catalChestplate = new ItemCatalyticalChestplate();
		catalLeggings = new ItemCatalyticalLeggings();
		catalBoots = new ItemCatalyticalBoots();
		mithrilHelmet = new ItemMithrilHelmet();
		mithrilChestplate = new ItemMithrilChestplate();
		mithrilLeggings = new ItemMithrilLeggings();
		mithrilBoots = new ItemMithrilBoots();
		nintHelmet = new ItemNintendiumHelmet();
		nintChestplate = new ItemNintendiumChestplate();
		nintLeggings = new ItemNintendiumLeggings();
		nintBoots = new ItemNintendiumBoots();
	}
	
}
