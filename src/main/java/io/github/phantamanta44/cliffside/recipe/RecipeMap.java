package io.github.phantamanta44.cliffside.recipe;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class RecipeMap<V> extends HashMap<ItemStack, V> {

	public boolean containsRecipeFor(ItemStack stack) {
		for (Entry<ItemStack, V> entry : entrySet()) {
			if (stack.isItemEqual(entry.getKey()))
				return true;
		}
		return false;
	}
	
	public V getRecipeFor(ItemStack stack) {
		for (Entry<ItemStack, V> entry : entrySet()) {
			if (stack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}
	
}
