package io.github.phantamanta44.cliffside.proxy;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.block.BlockOre;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.gui.container.GuiAlchemicalBurner;
import io.github.phantamanta44.cliffside.gui.container.GuiDisintegrator;
import io.github.phantamanta44.cliffside.gui.container.GuiSmelter;
import io.github.phantamanta44.cliffside.handler.ExplosiveEnergizationHandler;
import io.github.phantamanta44.cliffside.handler.GuiHandler;
import io.github.phantamanta44.cliffside.handler.NintendiumCraftHandler;
import io.github.phantamanta44.cliffside.handler.WrenchHandler;
import io.github.phantamanta44.cliffside.handler.ZfgBootHandler;
import io.github.phantamanta44.cliffside.inventory.ContainerAlchemicalBurner;
import io.github.phantamanta44.cliffside.inventory.ContainerDisintegrator;
import io.github.phantamanta44.cliffside.inventory.ContainerSmelter;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.recipe.MasterRecipeManager;
import io.github.phantamanta44.cliffside.tile.TileAlchemicalBurner;
import io.github.phantamanta44.cliffside.tile.TileCreativeLumenSource;
import io.github.phantamanta44.cliffside.tile.TileDisintegrator;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneEnergizer;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneNode;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneStorage;
import io.github.phantamanta44.cliffside.tile.TileSmelter;
import io.github.phantamanta44.cliffside.util.BlockWithMeta;
import io.github.phantamanta44.cliffside.worldgen.WorldGenSimple;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		CSBlocks.init();
		CSItems.init();
		registerTileEntities();
	}

	public void init(FMLInitializationEvent event) {
		MasterRecipeManager.registerRecipes();
		MinecraftForge.EVENT_BUS.register(new ExplosiveEnergizationHandler());
		MinecraftForge.EVENT_BUS.register(new ZfgBootHandler());
		MinecraftForge.EVENT_BUS.register(new WrenchHandler());
		MinecraftForge.EVENT_BUS.register(new NintendiumCraftHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(ModCliffside.instance, new GuiHandler());
		registerGuis();
		GameRegistry.registerWorldGenerator(new WorldGenSimple(new BlockWithMeta(CSBlocks.ore, BlockOre.MITHRIL), 0, new BlockWithMeta(Blocks.stone), 4, 18, 4, 4), 8);
		GameRegistry.registerWorldGenerator(new WorldGenSimple(new BlockWithMeta(CSBlocks.ore, BlockOre.SILVER), 0, new BlockWithMeta(Blocks.stone), 4, 64, 10, 12), 8);
	}

	public void postInit(FMLPostInitializationEvent event) {
		MasterRecipeManager.postInitRecipes();
	}
	
	private void registerGuis() {
		registerGui(TileDisintegrator.class, ContainerDisintegrator.class, GuiDisintegrator.class);
		registerGui(TileAlchemicalBurner.class, ContainerAlchemicalBurner.class, GuiAlchemicalBurner.class);
		registerGui(TileSmelter.class, ContainerSmelter.class, GuiSmelter.class);
	}
	
	private void registerGui(Class c, Class serverSide, Class clientSide) {
		GuiHandler.containerMap.put(c, serverSide);
		GuiHandler.guiMap.put(c, clientSide);
	}
	
	private void registerTileEntities() {
		addTEMapping(TileAlchemicalBurner.class);
		addTEMapping(TileCreativeLumenSource.class);
		addTEMapping(TileDisintegrator.class);
		addTEMapping(TileGlowstoneEnergizer.class);
		addTEMapping(TileGlowstoneNode.class);
		addTEMapping(TileGlowstoneStorage.class);
		addTEMapping(TileSmelter.class);
	}
	
	private void addTEMapping(Class c) {
		TileEntity.addMapping(c, c.getName());
	}

}
