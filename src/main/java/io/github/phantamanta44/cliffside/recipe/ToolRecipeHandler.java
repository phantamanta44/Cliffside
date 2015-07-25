package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ToolRecipeHandler {

	protected static void registerRecipes() {
		addArmorRecipes("ingotAlchemical", new ItemStack(CSItems.alchemHelmet), new ItemStack(CSItems.alchemChestplate),
				new ItemStack(CSItems.alchemLeggings), new ItemStack(CSItems.alchemBoots));
		addToolRecipes("ingotAlchemical", new ItemStack(Items.stick), new ItemStack(CSItems.alchemPickaxe),
				new ItemStack(CSItems.alchemAxe), new ItemStack(CSItems.alchemSword),
				new ItemStack(CSItems.alchemSpade), new ItemStack(CSItems.alchemHoe));
		
		addArmorRecipes("ingotCatalyst", new ItemStack(CSItems.catalHelmet), new ItemStack(CSItems.catalChestplate),
				new ItemStack(CSItems.catalLeggings), new ItemStack(CSItems.catalBoots));
		addToolRecipes("ingotCatalyst", new ItemStack(Items.stick), new ItemStack(CSItems.catalPickaxe),
				new ItemStack(CSItems.catalAxe), new ItemStack(CSItems.catalSword),
				new ItemStack(CSItems.catalSpade), new ItemStack(CSItems.catalHoe));
		
		addArmorRecipes("ingotMithril", new ItemStack(CSItems.mithrilHelmet), new ItemStack(CSItems.mithrilChestplate),
				new ItemStack(CSItems.mithrilLeggings), new ItemStack(CSItems.mithrilBoots));
		addToolRecipes("ingotMithril", new ItemStack(CSItems.matResource, 1, ItemResource.STICK_COND), new ItemStack(CSItems.mithrilPickaxe),
				new ItemStack(CSItems.mithrilAxe), new ItemStack(CSItems.mithrilSword),
				new ItemStack(CSItems.mithrilSpade), new ItemStack(CSItems.mithrilHoe));
		
		addArmorRecipes("ingotNintendium", new ItemStack(CSItems.nintHelmet), new ItemStack(CSItems.nintChestplate),
				new ItemStack(CSItems.nintLeggings), new ItemStack(CSItems.nintBoots));
		addToolRecipes("ingotNintendium", new ItemStack(CSItems.matResource, 1, ItemResource.STICK_OBS), new ItemStack(CSItems.nintPickaxe),
				new ItemStack(CSItems.nintAxe), new ItemStack(CSItems.nintSword),
				new ItemStack(CSItems.nintSpade), new ItemStack(CSItems.nintHoe));
	}
	
	private static void addArmorRecipes(Object material, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots) {
		addOreDictRecipe(helm, "mmm", "m m", 'm', material);
		addOreDictRecipe(chest, "m m", "mmm", "mmm", 'm', material);
		addOreDictRecipe(legs, "mmm", "m m", "m m", 'm', material);
		addOreDictRecipe(boots, "m m", "m m", 'm', material);
	}
	
	private static void addToolRecipes(Object material, Object rod, ItemStack pick, ItemStack axe, ItemStack sword, ItemStack shovel, ItemStack hoe) {
		addToolRecipes(material, rod, pick, axe, sword, shovel);
		addOreDictRecipe(hoe, "mm", " r", " r", 'm', material, 'r', rod);
		addOreDictRecipe(hoe, "mm", "r ", "r ", 'm', material, 'r', rod);
	}
	
	private static void addToolRecipes(Object material, Object rod, ItemStack pick, ItemStack axe, ItemStack sword, ItemStack shovel) {
		addOreDictRecipe(pick, "mmm", " r ", " r ", 'm', material, 'r', rod);
		addOreDictRecipe(axe, "mm", "mr", " r", 'm', material, 'r', rod);
		addOreDictRecipe(axe, "mm", "rm", "r ", 'm', material, 'r', rod);
		addOreDictRecipe(shovel, "m", "r", "r", 'm', material, 'r', rod);
		addOreDictRecipe(sword, "m", "m", "r", 'm', material, 'r', rod);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}
	
}
