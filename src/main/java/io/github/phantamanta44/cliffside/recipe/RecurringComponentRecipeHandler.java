package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import net.minecraft.item.ItemStack;

public class RecurringComponentRecipeHandler {

	protected static void registerRecipes() {
		addGearRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_ALCHEM), "ingotAlchemical");
		addGearRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_CATAL), "ingotCatalyst");
		addGearRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_MITHRIL), "ingotMithril");
		addGearRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_QS), "ingotQuicksilver");
		addGearRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.GEAR_MEAT), new ItemStack(CSItems.matResource, 1, ItemResource.INGOT_MEAT));
	}
	
	private static void addGearRecipe(ItemStack gear, Object mat) {
		addOreDictRecipe(gear, " m ", "mim", " m ", 'i', "ingotIron", 'm', mat);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		MasterRecipeManager.addOreDictRecipe(output, recipe);
	}
	
	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		MasterRecipeManager.addShapelessOreDictRecipe(output, recipe);
	}
	
}
