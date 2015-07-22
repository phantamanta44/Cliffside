package io.github.phantamanta44.cliffside.recipe.machine;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.BlockOre;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import io.github.phantamanta44.cliffside.recipe.RecipeMap;
import io.github.phantamanta44.cliffside.util.OreDictEntry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class DisintegratorRecipeHandler {
	
	public static RecipeMap<RecipeDisintegrator> recipeList = new RecipeMap<>();
	
	public static void registerRecipes() {
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM), new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM), 1);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.CATAL), new ItemStack(CSItems.matIngot, 1, ItemIngot.CATAL), 1);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.MITHRIL), new ItemStack(CSItems.matIngot, 1, ItemIngot.MITHRIL), 1);
		addRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.MITHRIL), new ItemStack(CSBlocks.ore, 1, BlockOre.MITHRIL), 2);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.NINTENDIUM), new ItemStack(CSItems.matIngot, 1, ItemIngot.NINTENDIUM), 1);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.QS), new ItemStack(CSItems.matIngot, 1, ItemIngot.QS), 1);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.SILVER), new ItemStack(CSItems.matIngot, 1, ItemIngot.SILVER), 1);
		addRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.SILVER), new ItemStack(CSBlocks.ore, 1, BlockOre.SILVER), 2);
		addRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.IRON), new ItemStack(Blocks.iron_ore), 2);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.IRON), new ItemStack(Items.iron_ingot), 1);
		addRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.GOLD), new ItemStack(Blocks.gold_ore), 2);
		addRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.GOLD), new ItemStack(Items.gold_ingot), 1);
		addRecipe(new ItemStack(CSItems.matDust, 4, ItemDust.ALCHEM_GS), new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS), 2);
		addRecipe(new ItemStack(CSItems.matDust, 4, ItemDust.CATAL), new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.CATAL), 2);
		addRecipe(new ItemStack(Items.redstone, 12), new ItemStack(Blocks.redstone_ore), 2);
		addRecipe(new ItemStack(Items.blaze_powder, 4), new ItemStack(Items.blaze_rod), 2);
		addRecipe(new ItemStack(Items.dye, 4, 15), new ItemStack(Items.bone), 1);
		addRecipe(new ItemStack(Items.gunpowder), new ItemStack(Items.flint), 1);
		addRecipe(new ItemStack(Items.flint), new ItemStack(Blocks.gravel), 1);
		addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.cobblestone), 1);
		addRecipe(new ItemStack(Blocks.sand), new ItemStack(Blocks.dirt), 1);
		addRecipe(new ItemStack(Items.glowstone_dust, 4), new ItemStack(Blocks.glowstone), 2);
		addRecipe(new ItemStack(Items.quartz, 7), new ItemStack(Blocks.quartz_ore), 2);
		addRecipe(new ItemStack(Items.coal, 3), new ItemStack(Blocks.coal_ore), 2);
		addRecipe(new ItemStack(Items.sugar, 2), new ItemStack(Items.reeds), 1);
		addRecipe(new ItemStack(Items.string, 4), new ItemStack(Blocks.wool), 1);
		addRecipe(new ItemStack(Items.dye, 4, 1), new ItemStack(Blocks.red_flower), 1);
		addRecipe(new ItemStack(Items.dye, 4, 2), new ItemStack(Blocks.cactus), 1);
		addRecipe(new ItemStack(Items.dye, 12, 4), new ItemStack(Blocks.lapis_ore), 2);
		addRecipe(new ItemStack(Items.dye, 4, 11), new ItemStack(Blocks.yellow_flower), 1);
		addRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.stone), 1);
		addRecipe(new ItemStack(Items.diamond, 2), new ItemStack(Blocks.diamond_ore), 2);
		addRecipe(new ItemStack(Items.emerald, 2), new ItemStack(Blocks.emerald_ore), 2);
		addRecipe(new ItemStack(Items.string, 3), new ItemStack(Blocks.web), 1);
		addRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.DOUGH), new ItemStack(Items.wheat), 1);
		addRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.DUST_MEAT), new ItemStack(Items.porkchop), 1);
		addRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.DUST_MEAT), new ItemStack(Items.beef), 1);
		addRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.DUST_MEAT), new ItemStack(Items.chicken), 1);
		addRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.DUST_MEAT), new ItemStack(Items.fish), 1);
		addRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.DUST_MEAT), new ItemStack(CSItems.matResource, 1, ItemResource.INGOT_MEAT), 1);
		
		for (int i = 0; i < 4; i++)
			addRecipe(new ItemStack(Blocks.sapling, 1, i), new ItemStack(Blocks.leaves, 1, i), 1);
		
		for (int i = 0; i < 2; i++)
			addRecipe(new ItemStack(Blocks.sapling, 1, i + 4), new ItemStack(Blocks.leaves2, 1, i), 1);
		
		for (int i = 0; i < 4; i++)
			addRecipe(new ItemStack(Blocks.planks, 6, i), new ItemStack(Blocks.log, 1, i), 1);
		
		for (int i = 0; i < 2; i++)
			addRecipe(new ItemStack(Blocks.planks, 6, i + 4), new ItemStack(Blocks.log2, 1, i), 1);
		
		for (int i = 0; i < 6; i++)
			addRecipe(new ItemStack(Items.stick, 4), new ItemStack(Blocks.planks, 1, i), 1);
	}
	
	public static void addModCompatRecipes(OreDictEntry entry) {
		if (!entry.ingots.isEmpty() && !entry.dusts.isEmpty()) {
			for (ItemStack ingot : entry.ingots)
				DisintegratorRecipeHandler.addRecipe(entry.getBaseDust(), ingot, 1);
		}
		if (!entry.ores.isEmpty() && !entry.dusts.isEmpty()) {
			for (ItemStack ore : entry.ores)
				DisintegratorRecipeHandler.addRecipe(new ItemStack(entry.getBaseDust().getItem(), 2, entry.getBaseDust().getItemDamage()), ore, 2);
		}
	}
	
	public static void addRecipe(ItemStack out, ItemStack in, int work) {
		recipeList.put(in, new RecipeDisintegrator(out, in, work));
	}
	
	public static class RecipeDisintegrator {
		
		private ItemStack input, output;
		private int workNeeded;
		
		public RecipeDisintegrator(ItemStack out, ItemStack in, int work) {
			input = in;
			output = out;
			workNeeded = work;
		}
		
		public ItemStack getInput() {
			return input;
		}
		
		public ItemStack getOutput() {
			return output;
		}
		
		public int getWorkNeeded() {
			return workNeeded;
		}
		
	}
	
}
