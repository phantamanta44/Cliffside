package io.github.phantamanta44.cliffside.recipe.machine;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.BlockCompressed;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.util.BlockWithMeta;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;

public class EnergizerRecipeHandler {
	
	public static Map<BlockWithMeta, RecipeEnergizer> recipeList = new HashMap<>();
	
	public static void registerRecipes() {
		addRecipe(new BlockWithMeta(CSBlocks.alchemBlk, BlockAlchemical.ALCHEM_GS), new BlockWithMeta(CSBlocks.compBlk, BlockCompressed.COMP_GS), 3);
		addRecipe(new BlockWithMeta(CSBlocks.alchemBlk, BlockAlchemical.CATAL), new BlockWithMeta(CSBlocks.compBlk, BlockCompressed.COMP_ALCHEM_GS), 5);
		addRecipe(new BlockWithMeta(CSBlocks.alchemBlk, BlockAlchemical.NINT_MIX), new BlockWithMeta(CSBlocks.compBlk, BlockCompressed.NINT_COMPONENT), 8);
		addRecipe(new BlockWithMeta(CSBlocks.alchemBlk, BlockAlchemical.ETCHED_OBS), new BlockWithMeta(Blocks.obsidian), 4);
	}
	
	public static void addRecipe(BlockWithMeta out, BlockWithMeta in, int work) {
		recipeList.put(in, new RecipeEnergizer(out, in, work));
	}
	
	public static class RecipeEnergizer {
		
		private BlockWithMeta input, output;
		private int workNeeded;
		
		public RecipeEnergizer(BlockWithMeta out, BlockWithMeta in, int work) {
			input = in;
			output = out;
			workNeeded = work;
		}
		
		public BlockWithMeta getInput() {
			return input;
		}
		
		public BlockWithMeta getOutput() {
			return output;
		}
		
		public int getWorkNeeded() {
			return workNeeded;
		}
		
	}
	
}
