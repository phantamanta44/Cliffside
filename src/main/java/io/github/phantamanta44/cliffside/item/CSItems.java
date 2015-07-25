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
import io.github.phantamanta44.cliffside.item.tool.ToolAlchemical.ItemAlchemicalAxe;
import io.github.phantamanta44.cliffside.item.tool.ToolAlchemical.ItemAlchemicalHoe;
import io.github.phantamanta44.cliffside.item.tool.ToolAlchemical.ItemAlchemicalPickaxe;
import io.github.phantamanta44.cliffside.item.tool.ToolAlchemical.ItemAlchemicalSpade;
import io.github.phantamanta44.cliffside.item.tool.ToolAlchemical.ItemAlchemicalSword;
import io.github.phantamanta44.cliffside.item.tool.ToolCatalytical.ItemCatalyticalAxe;
import io.github.phantamanta44.cliffside.item.tool.ToolCatalytical.ItemCatalyticalHoe;
import io.github.phantamanta44.cliffside.item.tool.ToolCatalytical.ItemCatalyticalPickaxe;
import io.github.phantamanta44.cliffside.item.tool.ToolCatalytical.ItemCatalyticalSpade;
import io.github.phantamanta44.cliffside.item.tool.ToolCatalytical.ItemCatalyticalSword;
import io.github.phantamanta44.cliffside.item.tool.ToolMithril.ItemMithrilAxe;
import io.github.phantamanta44.cliffside.item.tool.ToolMithril.ItemMithrilHoe;
import io.github.phantamanta44.cliffside.item.tool.ToolMithril.ItemMithrilPickaxe;
import io.github.phantamanta44.cliffside.item.tool.ToolMithril.ItemMithrilSpade;
import io.github.phantamanta44.cliffside.item.tool.ToolMithril.ItemMithrilSword;
import io.github.phantamanta44.cliffside.item.tool.ToolNintendium.ItemNintendiumAxe;
import io.github.phantamanta44.cliffside.item.tool.ToolNintendium.ItemNintendiumHoe;
import io.github.phantamanta44.cliffside.item.tool.ToolNintendium.ItemNintendiumPickaxe;
import io.github.phantamanta44.cliffside.item.tool.ToolNintendium.ItemNintendiumSpade;
import io.github.phantamanta44.cliffside.item.tool.ToolNintendium.ItemNintendiumSword;
import net.minecraft.item.Item;

public class CSItems {

	public static Item matDust;
	public static Item matIngot;
	public static Item matNugget;
	public static Item matResource;
	public static Item wrench;
	public static Item zfgBoots;
	
	public static void init() {
		matDust = new ItemDust();
		matIngot = new ItemIngot();
		matNugget = new ItemNugget();
		matResource = new ItemResource();
		wrench = new ItemWrench();
		zfgBoots = new ItemZFGBoots();
		
		addTools();
	}
	
	public static Item alchemHelmet, alchemChestplate, alchemLeggings, alchemBoots;
	public static Item alchemAxe, alchemPickaxe, alchemSpade, alchemSword, alchemHoe;
	public static Item catalHelmet, catalChestplate, catalLeggings, catalBoots;
	public static Item catalAxe, catalPickaxe, catalSpade, catalSword, catalHoe;
	public static Item mithrilHelmet, mithrilChestplate, mithrilLeggings, mithrilBoots;
	public static Item mithrilAxe, mithrilPickaxe, mithrilSpade, mithrilSword, mithrilHoe;
	public static Item nintHelmet, nintChestplate, nintLeggings, nintBoots;
	public static Item nintAxe, nintPickaxe, nintSpade, nintSword, nintHoe;
	
	public static void addTools() {
		alchemHelmet = new ItemAlchemicalHelmet();
		alchemChestplate = new ItemAlchemicalChestplate();
		alchemLeggings = new ItemAlchemicalLeggings();
		alchemBoots = new ItemAlchemicalBoots();
		alchemAxe = new ItemAlchemicalAxe();
		alchemPickaxe = new ItemAlchemicalPickaxe();
		alchemSpade = new ItemAlchemicalSpade();
		alchemSword = new ItemAlchemicalSword();
		alchemHoe = new ItemAlchemicalHoe();
		catalHelmet = new ItemCatalyticalHelmet();
		catalChestplate = new ItemCatalyticalChestplate();
		catalLeggings = new ItemCatalyticalLeggings();
		catalBoots = new ItemCatalyticalBoots();
		catalAxe = new ItemCatalyticalAxe();
		catalPickaxe = new ItemCatalyticalPickaxe();
		catalSpade = new ItemCatalyticalSpade();
		catalSword = new ItemCatalyticalSword();
		catalHoe = new ItemCatalyticalHoe();
		mithrilHelmet = new ItemMithrilHelmet();
		mithrilChestplate = new ItemMithrilChestplate();
		mithrilLeggings = new ItemMithrilLeggings();
		mithrilBoots = new ItemMithrilBoots();
		mithrilAxe = new ItemMithrilAxe();
		mithrilPickaxe = new ItemMithrilPickaxe();
		mithrilSpade = new ItemMithrilSpade();
		mithrilSword = new ItemMithrilSword();
		mithrilHoe = new ItemMithrilHoe();
		nintHelmet = new ItemNintendiumHelmet();
		nintChestplate = new ItemNintendiumChestplate();
		nintLeggings = new ItemNintendiumLeggings();
		nintBoots = new ItemNintendiumBoots();
		nintAxe = new ItemNintendiumAxe();
		nintPickaxe = new ItemNintendiumPickaxe();
		nintSpade = new ItemNintendiumSpade();
		nintSword = new ItemNintendiumSword();
		nintHoe = new ItemNintendiumHoe();
	}
	
}
