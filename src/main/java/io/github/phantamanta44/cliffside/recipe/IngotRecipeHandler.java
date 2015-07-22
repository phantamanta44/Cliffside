package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.block.BlockOre;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.item.resource.ItemNugget;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class IngotRecipeHandler {

	protected static void registerRecipes() {
		addIngot(new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM), "ingotAlchemical",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.ALCHEM), "nuggetAlchemical",
				new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM), 2000);
		addIngot(new ItemStack(CSItems.matIngot, 1, ItemIngot.CATAL), "ingotCatalyst",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.CATAL), "nuggetCatalyst",
				new ItemStack(CSItems.matDust, 1, ItemDust.CATAL), 2000);
		addIngot(new ItemStack(CSItems.matIngot, 1, ItemIngot.MITHRIL), "ingotMithril",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.MITHRIL), "nuggetMithril",
				new ItemStack(CSItems.matDust, 1, ItemDust.MITHRIL), 2000,
				new ItemStack(CSBlocks.ore, 1, BlockOre.MITHRIL), 18000);
		addIngot(new ItemStack(CSItems.matIngot, 1, ItemIngot.NINTENDIUM), "ingotNintendium",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.NINTENDIUM), "nuggetNintendium",
				new ItemStack(CSItems.matDust, 1, ItemDust.NINTENDIUM), 2000);
		addIngot(new ItemStack(CSItems.matIngot, 1, ItemIngot.QS), "ingotQuicksilver",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.QS), "nuggetQuicksilver",
				new ItemStack(CSItems.matDust, 1, ItemDust.QS), 2000);
		addIngot(new ItemStack(CSItems.matIngot, 1, ItemIngot.SILVER), "ingotSilver",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.SILVER), "nuggetSilver",
				new ItemStack(CSItems.matDust, 1, ItemDust.SILVER), 2000,
				new ItemStack(CSBlocks.ore, 1, BlockOre.SILVER), 18000);
		addIngot(new ItemStack(Items.iron_ingot), "ingotIron",
				new ItemStack(CSItems.matNugget, 1, ItemNugget.IRON), "nuggetIron",
				new ItemStack(CSItems.matDust, 1, ItemDust.IRON), 2000);
		addSmeltOrigins(new ItemStack(Items.gold_ingot), "ingotGold",
				new ItemStack(CSItems.matDust, 1, ItemDust.GOLD), 2000);
	}
	
	private static void addIngot(ItemStack ingot, String oreDictIngot, ItemStack nugget, String oreDictNugget, Object... smeltOrigins) {
		addNuggetRecipe(ingot, oreDictIngot, nugget, oreDictNugget);
		addSmeltOrigins(ingot, oreDictIngot, smeltOrigins);
	}
	
	private static void addSmeltOrigins(ItemStack ingot, String oreDictIngot, Object... smeltOrigins) {
		for (int i = 0; i < smeltOrigins.length; i += 2)
			addSmelting(ingot, (ItemStack)smeltOrigins[i], (int)smeltOrigins[i + 1]);
	}
	
	private static void addNuggetRecipe(ItemStack output, ItemStack input) {
		addOreDictRecipe(output, "ggg", "ggg", "ggg", 'g', input);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 9, input.getItemDamage()), output);
	}
	
	private static void addNuggetRecipe(ItemStack output, ItemStack input, String oreDictIn) {
		addOreDictRecipe(output, "ggg", "ggg", "ggg", 'g', oreDictIn);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 9, input.getItemDamage()), output);
	}
	
	private static void addNuggetRecipe(ItemStack output, String oreDictOut, ItemStack input, String oreDictIn) {
		addOreDictRecipe(output, "ggg", "ggg", "ggg", 'g', oreDictIn);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 9, input.getItemDamage()), oreDictOut);
	}
	
	private static void addSmelting(ItemStack output, ItemStack input, int xp) {
		MasterRecipeManager.addSmelting(output, input, xp);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		MasterRecipeManager.addOreDictRecipe(output, recipe);
	}
	
	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		MasterRecipeManager.addShapelessOreDictRecipe(output, recipe);
	}
	
}
