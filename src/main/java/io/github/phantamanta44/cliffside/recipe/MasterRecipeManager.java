package io.github.phantamanta44.cliffside.recipe;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.BlockCompressed;
import io.github.phantamanta44.cliffside.block.BlockMachine;
import io.github.phantamanta44.cliffside.block.BlockTransparent;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.ItemWrench;
import io.github.phantamanta44.cliffside.item.resource.ItemDust;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;
import io.github.phantamanta44.cliffside.item.resource.ItemResource;
import io.github.phantamanta44.cliffside.recipe.machine.DisintegratorRecipeHandler;
import io.github.phantamanta44.cliffside.recipe.machine.EnergizerRecipeHandler;
import io.github.phantamanta44.cliffside.util.OreDictEntry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class MasterRecipeManager {

	public static void registerRecipes() {
		OreDictHandler.registerOres();
		
		// Dust from alchemical blocks
		addShapelessOreDictRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.ALCHEM_GS), new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS));
		addShapelessOreDictRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.CATAL), new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.CATAL));
		
		// Alchemical blocks from dust
		addOreDictRecipe(new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS), "dd", "dd", 'd', new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM_GS));
		addOreDictRecipe(new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.CATAL), "dd", "dd", 'd', "dustCatalyst");
		
		// Metal binding agent
		addShapelessOreDictRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.BINDING), new ItemStack(Items.flint), "ingotIron", new ItemStack(Items.clay_ball));
		
		// Alchemical metal dust
		addShapelessOreDictRecipe(new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM), new ItemStack(CSItems.matDust, 1, ItemDust.BINDING), new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM_GS));
		
		// Mithril dust
		addShapelessOreDictRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.MITHRIL), "dustCatalyst", "dustAlchemical", "dustAlchemical");
		
		// Quicksilver dust
		addShapelessOreDictRecipe(new ItemStack(CSItems.matDust, 2, ItemDust.QS), "dustMithril", "dustSilver");
		
		// Nintendium mixture
		addOreDictRecipe(new ItemStack(CSBlocks.compBlk, 10, BlockCompressed.NINT_COMPONENT), "pop", "obo", "pop", 'p', new ItemStack(Items.ender_eye), 'o', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ETCHED_OBS), 'b', new ItemStack(CSItems.matResource, 1, ItemResource.BOTTLE_MS));
		
		// Bread
		addSmelting(new ItemStack(Items.bread), new ItemStack(CSItems.matResource, 1, ItemResource.DOUGH), 4200);
		
		// Meat ingot
		addSmelting(new ItemStack(CSItems.matResource, 1, ItemResource.INGOT_MEAT), new ItemStack(CSItems.matResource, 1, ItemResource.DUST_MEAT), 4200);
		
		// Magic-conductive rod
		addOreDictRecipe(new ItemStack(CSItems.matResource, 2, ItemResource.STICK_COND), "q", "q", 'q', "ingotQuicksilver");
		
		// Abyssal rod
		addOreDictRecipe(new ItemStack(CSItems.matResource, 1, ItemResource.STICK_OBS), "o", "o", "d", 'o', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ETCHED_OBS), 'd', "gemDiamond");
		
		// Alchemists' wrench
		addOreDictRecipe(new ItemStack(CSItems.wrench, 1, ItemWrench.WRENCH), "a a", " i ", " a ", 'a', "ingotAlchemical", 'i', "ingotSilver");
		addOreDictRecipe(new ItemStack(CSItems.wrench, 1, ItemWrench.WRENCH), "a a", " g ", " a ", 'a', "ingotAlchemical", 'g', "ingotGold");
		
		// Illumination reader
		addOreDictRecipe(new ItemStack(CSItems.wrench, 1, ItemWrench.READER), "maa", "mga", 'a', "ingotAlchemical", 'm', "ingotMithril", 'g', "blockGlass");
		addOreDictRecipe(new ItemStack(CSItems.wrench, 1, ItemWrench.READER), "aam", "agm", 'a', "ingotAlchemical", 'm', "ingotMithril", 'g', "blockGlass");
		
		// ZFG boots
		addOreDictRecipe(new ItemStack(CSItems.zfgBoots), "ltl", "lnl", 'l', new ItemStack(Items.leather), 't', new ItemStack(Blocks.tnt), 'n', "ingotNintendium");
		
		// Glowstone nodes
		addOreDictRecipe(new ItemStack(CSBlocks.gsNodeBasic, 2), "d d", " g ", "d d", 'd', "nuggetAlchemical", 'g', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS));
		addOreDictRecipe(new ItemStack(CSBlocks.gsNode, 2), "dmd", "mgm", "dmd", 'd', "ingotAlchemical", 'g', new ItemStack(CSBlocks.gsNodeBasic), 'm', "dustMithril");
		addOreDictRecipe(new ItemStack(CSBlocks.gsNodeDeep, 2), "cnc", "nbn", "cnc", 'n', new ItemStack(CSBlocks.gsNode), 'c', "dustCatalyst", 'b', "blockCatalyst");
		addOreDictRecipe(new ItemStack(CSBlocks.gsNodeFast, 2), "cnc", "nbn", "cnc", 'n', new ItemStack(CSBlocks.gsNode), 'c', "dustCatalyst", 'b', "blockMithril");
		for (int i = 0; i < 16; i++) {
			addOreDictRecipe(new ItemStack(CSBlocks.gsNodeBasic, 8, i), "nnn", "ndn", "nnn", 'n', new ItemStack(CSBlocks.gsNodeBasic), 'd', GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNodeBasic, 1, i), new ItemStack(CSBlocks.gsNodeBasic), GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNodeBasic), new ItemStack(CSBlocks.gsNodeBasic, 1, i));
			addOreDictRecipe(new ItemStack(CSBlocks.gsNodeFast, 8, i), "nnn", "ndn", "nnn", 'n', new ItemStack(CSBlocks.gsNodeFast), 'd', GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNodeFast, 1, i), new ItemStack(CSBlocks.gsNodeFast), GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNodeFast), new ItemStack(CSBlocks.gsNodeFast, 1, i));
			addOreDictRecipe(new ItemStack(CSBlocks.gsNodeDeep, 8, i), "nnn", "ndn", "nnn", 'n', new ItemStack(CSBlocks.gsNodeDeep), 'd', GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNodeDeep, 1, i), new ItemStack(CSBlocks.gsNodeDeep), GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNodeDeep), new ItemStack(CSBlocks.gsNodeDeep, 1, i));
			addOreDictRecipe(new ItemStack(CSBlocks.gsNode, 8, i), "nnn", "ndn", "nnn", 'n', new ItemStack(CSBlocks.gsNode), 'd', GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNode, 1, i), new ItemStack(CSBlocks.gsNode), GlobalConstants.DYE_OREDICT[i]);
			addShapelessOreDictRecipe(new ItemStack(CSBlocks.gsNode), new ItemStack(CSBlocks.gsNode, 1, i));
		}
		
		// Illumination lamps
		addOreDictRecipe(new ItemStack(CSBlocks.lumenLamp), "mgm", "gng", "mgm", 'm', "ingotMithril", 'g', "blockGlass", 'n', new ItemStack(CSBlocks.gsNodeDeep));
		for (int i = 1; i < 10; i++) {
			if (i < 4)
				addOreDictRecipe(new ItemStack(CSBlocks.lumenLamp, 1, i), "mgm", "gng", "mgm", 'm', "ingotMithril", 'n', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS), 'g', new ItemStack(CSBlocks.lumenLamp, 1, i - 1));
			else if (i < 8)
				addOreDictRecipe(new ItemStack(CSBlocks.lumenLamp, 1, i), "mgm", "gng", "mgm", 'm', "ingotQuicksilver", 'n', "gemDiamond", 'g', new ItemStack(CSBlocks.lumenLamp, 1, i - 1));
			else if (i < 12)
				addOreDictRecipe(new ItemStack(CSBlocks.lumenLamp, 1, i), "mgm", "gng", "mgm", 'm', "ingotNintendium", 'n', new ItemStack(Items.ender_eye), 'g', new ItemStack(CSBlocks.lumenLamp, 1, i - 1));
		}
		
		// Conductive frame
		addOreDictRecipe(new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.MACHINE_FRAME), "mgm", "grg", "mgm", 'm', "ingotMithril", 'g', "blockGlass", 'r', "gearAlchemical");
		
		// Quicksilver glass
		addOreDictRecipe(new ItemStack(CSBlocks.matGlass, 1, BlockTransparent.QS_GLASS), " q ", "qgq", " q ", 'g', "blockGlass", 'q', "dustQuicksilver");
		
		// Alchemical burner
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.ALCHEM_BURNER), "aaa", "gng", "afa", 'a', "ingotAlchemical", 'g', "ingotGold", 'n', new ItemStack(CSBlocks.gsNodeBasic), 'f', new ItemStack(Blocks.furnace));
		
		// Glowstone energizer
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.ENERGIZER), "nan", "aga", "nan", 'n', new ItemStack(CSBlocks.gsNodeBasic), 'a', new ItemStack(CSItems.matIngot, 1, ItemIngot.ALCHEM), 'g', new ItemStack(CSItems.matDust, 1, ItemDust.ALCHEM_GS));
		
		// Catalytical disintegrator
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.DISINTEGRATOR), "aaa", "cfc", "aaa", 'a', "ingotAlchemical", 'c', "gearCatalyst", 'f', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.MACHINE_FRAME));
		
		// Alcheimcal smelter
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.SMELTER), "ana", "gfg", "asa", 'a', "ingotAlchemical", 'n', "ingotBrickNether", 'g', "gearMithril", 'f', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.MACHINE_FRAME), 's', new ItemStack(Items.flint_and_steel));
		
		// Advanced burner
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.ADV_BURNER), "olo", "gfg", "aba", 'a', "ingotAlchemical", 'l', new ItemStack(Items.lava_bucket), 'o', new ItemStack(Blocks.obsidian), 'g', "gearQuicksilver", 'b', new ItemStack(CSBlocks.machine, 1, BlockMachine.ALCHEM_BURNER), 'f', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.MACHINE_FRAME));
		
		// Leyline aggregator
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.LEYLINE_AGGRO), "ccc", "gfg", "cmc", 'c', "ingotCatalyst", 'g', "gearMithril", 'm', new ItemStack(Items.bucket), 'f', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.MACHINE_FRAME));
		
		// Lunar distillery
		addOreDictRecipe(new ItemStack(CSBlocks.machine, 1, BlockMachine.LUNA_DISTILL), "olo", "gfg", "ccc", 'o', new ItemStack(Blocks.obsidian), 'l', "blockGlassHardened", 'g', "gearQuicksilver", 'f', new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.MACHINE_FRAME), 'c', "ingotQuicksilver");
		
		IngotRecipeHandler.registerRecipes();
		StorageBlockRecipeHandler.registerRecipes();
		ToolRecipeHandler.registerRecipes();
		DungeonLootHandler.registerDungeonLoot();
		
		EnergizerRecipeHandler.registerRecipes();
		DisintegratorRecipeHandler.registerRecipes();
		RecurringComponentRecipeHandler.registerRecipes();
	}
	
	public static void postInitRecipes() {
		String[] names = OreDictionary.getOreNames();
		for (String name : names) {
			if (name.matches("ingot[A-Z]\\w*")) {
				OreDictEntry entry = new OreDictEntry(name.replaceAll("ingot([A-Z]\\w*)", "$1"));
				DisintegratorRecipeHandler.addModCompatRecipes(entry);
			}
		}
	}
	
	protected static void addSmelting(ItemStack output, ItemStack input, int xp) {
		GameRegistry.addSmelting(input, output, xp);
	}
	
	protected static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	protected static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
	
}
