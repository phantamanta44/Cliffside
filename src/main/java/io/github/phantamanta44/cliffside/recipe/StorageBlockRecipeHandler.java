package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.BlockCompressed;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class StorageBlockRecipeHandler {
	
	protected static void registerRecipes() {
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_GS), new ItemStack(Blocks.glowstone, 1), "glowstone");
		addSmallStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_ALCHEM_GS), new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS));
		addSmallStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_RS), new ItemStack(Blocks.redstone_block, 1), "blockRedstone");
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_ALCHEM), "blockAlchemical", new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM), "ingotAlchemical");
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_CATAL), "blockCatalyst", new ItemStack(CSItems.matIngot, 1, ItemIngot.CATAL), "ingotCatalyst");
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_MITHRIL), "blockMithril", new ItemStack(CSItems.matIngot, 1, ItemIngot.MITHRIL), "ingotMithril");
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_NINT), "blockNintendium", new ItemStack(CSItems.matIngot, 1, ItemIngot.NINTENDIUM), "ingotNintendium");
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_QS), "blockQuicksilver", new ItemStack(CSItems.matIngot, 1, ItemIngot.QS), "ingotQuicksilver");
		addLargeStorageRecipe(new ItemStack(CSBlocks.compBlk, 1, BlockCompressed.COMP_SILVER), "blockSilver", new ItemStack(CSItems.matIngot, 1, ItemIngot.SILVER), "ingotSilver");
	}

	private static void addSmallStorageRecipe(ItemStack output, ItemStack input) {
		addOreDictRecipe(output, "rr", "rr", 'r', input);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 4, input.getItemDamage()), output);
	}
	
	private static void addLargeStorageRecipe(ItemStack output, ItemStack input) {
		addOreDictRecipe(output, "ggg", "ggg", "ggg", 'g', input);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 9, input.getItemDamage()), output);
	}
	
	private static void addSmallStorageRecipe(ItemStack output, ItemStack input, String oreDictIn) {
		addOreDictRecipe(output, "rr", "rr", 'r', oreDictIn);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 4, input.getItemDamage()), output);
	}
	
	private static void addLargeStorageRecipe(ItemStack output, ItemStack input, String oreDictIn) {
		addOreDictRecipe(output, "ggg", "ggg", "ggg", 'g', oreDictIn);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 9, input.getItemDamage()), output);
	}
	
	private static void addSmallStorageRecipe(ItemStack output, String oreDictOut, ItemStack input, String oreDictIn) {
		addOreDictRecipe(output, "rr", "rr", 'r', oreDictIn);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 4, input.getItemDamage()), oreDictOut);
	}
	
	private static void addLargeStorageRecipe(ItemStack output, String oreDictOut, ItemStack input, String oreDictIn) {
		addOreDictRecipe(output, "ggg", "ggg", "ggg", 'g', oreDictIn);
		addShapelessOreDictRecipe(new ItemStack(input.getItem(), 9, input.getItemDamage()), oreDictOut);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		MasterRecipeManager.addOreDictRecipe(output, recipe);
	}
	
	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		MasterRecipeManager.addShapelessOreDictRecipe(output, recipe);
	}
	
}
