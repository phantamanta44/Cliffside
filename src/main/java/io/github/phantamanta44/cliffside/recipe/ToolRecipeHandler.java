package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.item.CSItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ToolRecipeHandler {

	protected static void registerRecipes() {
		addArmorRecipes("ingotAlchemical", new ItemStack(CSItems.alchemHelmet), new ItemStack(CSItems.alchemChestplate),
				new ItemStack(CSItems.alchemLeggings), new ItemStack(CSItems.alchemBoots));
		addArmorRecipes("ingotCatalyst", new ItemStack(CSItems.catalHelmet), new ItemStack(CSItems.catalChestplate),
				new ItemStack(CSItems.catalLeggings), new ItemStack(CSItems.catalBoots));
		addArmorRecipes("ingotMithril", new ItemStack(CSItems.mithrilHelmet), new ItemStack(CSItems.mithrilChestplate),
				new ItemStack(CSItems.mithrilLeggings), new ItemStack(CSItems.mithrilBoots));
		addArmorRecipes("ingotNintendium", new ItemStack(CSItems.nintHelmet), new ItemStack(CSItems.nintChestplate),
				new ItemStack(CSItems.nintLeggings), new ItemStack(CSItems.nintBoots));
	}
	
	private static void addArmorRecipes(Object material, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots) {
		addOreDictRecipe(helm, "mmm", "m m", 'm', material);
		addOreDictRecipe(chest, "m m", "mmm", "mmm", 'm', material);
		addOreDictRecipe(legs, "mmm", "m m", "m m", 'm', material);
		addOreDictRecipe(boots, "m m", "m m", 'm', material);
	}
	
	private static void addToolRecipes(Object material, Object rod, ItemStack pick, ItemStack axe, ItemStack sword, ItemStack shovel, ItemStack hoe) {
		addToolRecipes(material, rod, pick, axe, sword, shovel);
		addOreDictRecipe(hoe, "mm", " r", " r", 'm', 'm', material, 'r', rod);
		addOreDictRecipe(hoe, "mm", "r", "r", 'm', material, 'r', rod);
	}
	
	private static void addToolRecipes(Object material, Object rod, ItemStack pick, ItemStack axe, ItemStack sword, ItemStack shovel) {
		addOreDictRecipe(pick, "mmm", " r ", " r ", 'm', material, 'r', rod);
		addOreDictRecipe(axe, "mm", "mr", " r", 'm', material, 'r', rod);
		addOreDictRecipe(axe, "mm", "rm", "r", 'm', material, 'r', rod);
		addOreDictRecipe(shovel, "m", "r", "r", 'm', material, 'r', rod);
		addOreDictRecipe(sword, "m", "m", "r", 'm', material, 'r', rod);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}
	
}
