package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.block.BlockCompressed;
import io.github.phantamanta44.cliffside.block.BlockOre;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.item.resource.ItemNugget;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

	protected static void registerOres() {
		OreDictionary.registerOre("blockAlchemical", new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_ALCHEM));
		OreDictionary.registerOre("blockCatalyst", new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_CATAL));
		OreDictionary.registerOre("blockMithril", new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_MITHRIL));
		OreDictionary.registerOre("blockNintendium", new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_NINT));
		OreDictionary.registerOre("blockQuicksilver", new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_QS));
		OreDictionary.registerOre("blockSilver", new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_SILVER));
		OreDictionary.registerOre("ingotAlchemical", new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM));
		OreDictionary.registerOre("ingotCatalyst", new ItemStack(CSItems.matIngot, 1, ItemIngot.CATAL));
		OreDictionary.registerOre("ingotMithril", new ItemStack(CSItems.matIngot, 1, ItemIngot.MITHRIL));
		OreDictionary.registerOre("ingotNintendium", new ItemStack(CSItems.matIngot, 1, ItemIngot.NINTENDIUM));
		OreDictionary.registerOre("ingotQuicksilver", new ItemStack(CSItems.matIngot, 1, ItemIngot.QS));
		OreDictionary.registerOre("ingotSilver", new ItemStack(CSItems.matIngot, 1, ItemIngot.SILVER));
		OreDictionary.registerOre("nuggetAlchemical", new ItemStack(CSItems.matNugget, 1, ItemNugget.ALCHEM));
		OreDictionary.registerOre("nuggetCatalyst", new ItemStack(CSItems.matNugget, 1, ItemNugget.CATAL));
		OreDictionary.registerOre("nuggetMithril", new ItemStack(CSItems.matNugget, 1, ItemNugget.MITHRIL));
		OreDictionary.registerOre("nuggetNintendium", new ItemStack(CSItems.matNugget, 1, ItemNugget.NINTENDIUM));
		OreDictionary.registerOre("nuggetQuicksilver", new ItemStack(CSItems.matNugget, 1, ItemNugget.QS));
		OreDictionary.registerOre("nuggetSilver", new ItemStack(CSItems.matNugget, 1, ItemNugget.SILVER));
		OreDictionary.registerOre("dustAlchemical", new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM));
		OreDictionary.registerOre("dustCatalyst", new ItemStack(CSItems.matDust, 1, ItemDust.CATAL));
		OreDictionary.registerOre("dustMithril", new ItemStack(CSItems.matDust, 1, ItemDust.MITHRIL));
		OreDictionary.registerOre("dustNintendium", new ItemStack(CSItems.matDust, 1, ItemDust.NINTENDIUM));
		OreDictionary.registerOre("dustQuicksilver", new ItemStack(CSItems.matDust, 1, ItemDust.QS));
		OreDictionary.registerOre("dustSilver", new ItemStack(CSItems.matDust, 1, ItemDust.SILVER));
		OreDictionary.registerOre("oreSilver", new ItemStack(CSBlocks.ore, 1, BlockOre.SILVER));
		OreDictionary.registerOre("oreMithril", new ItemStack(CSBlocks.ore, 1, BlockOre.MITHRIL));
		OreDictionary.registerOre("gearAlchemical", new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_ALCHEM));
		OreDictionary.registerOre("gearCatalyst", new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_CATAL));
		OreDictionary.registerOre("gearMithril", new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_MITHRIL));
		OreDictionary.registerOre("gearQuicksilver", new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_QS));
		OreDictionary.registerOre("gearMeat", new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_MEAT));
	}
	
}
