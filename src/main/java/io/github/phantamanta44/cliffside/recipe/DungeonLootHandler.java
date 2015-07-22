package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonLootHandler {
	
	protected static void registerDungeonLoot() {
		addToAll(new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM), 1, 4, 20);
		addToAll(new ItemStack(CSItems.matIngot, 1, ItemIngot.MITHRIL), 1, 4, 32);
		addToAll(new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM_GS), 1, 5, 24);
		addToHostile(new ItemStack(CSItems.matIngot, 1, ItemIngot.NINTENDIUM), 1, 2, 1);
		addToHostile(new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS), 1, 3, 16);
	}
	
	private static void addToAll(ItemStack item, int min, int max, int rarity) {
		addToHostile(item, min, max, rarity);
		addWeightedItem(ChestGenHooks.VILLAGE_BLACKSMITH, item, min, max, rarity);
	}
	
	private static void addToHostile(ItemStack item, int min, int max, int rarity) {
		addWeightedItem(ChestGenHooks.DUNGEON_CHEST, item, min, max, rarity);
		addWeightedItem(ChestGenHooks.MINESHAFT_CORRIDOR, item, min, max, rarity);
		addWeightedItem(ChestGenHooks.PYRAMID_DESERT_CHEST, item, min, max, rarity);
		addWeightedItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, item, min, max, rarity);
		addWeightedItem(ChestGenHooks.STRONGHOLD_CORRIDOR, item, min, max, rarity);
		addWeightedItem(ChestGenHooks.STRONGHOLD_CROSSING, item, min, max, rarity);
		addWeightedItem(ChestGenHooks.STRONGHOLD_LIBRARY, item, min, max, rarity);
	}
	
	private static void addWeightedItem(String category, ItemStack item, int min, int max, int rarity) {
		ChestGenHooks.addItem(category, new WeightedRandomChestContent(item, min, max, rarity));
	}
	
}
