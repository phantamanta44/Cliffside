package io.github.phantamanta44.cliffside.proxy;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.ctm.CTMRenderer;
import io.github.phantamanta44.cliffside.gui.container.GuiAlchemicalBurner;
import io.github.phantamanta44.cliffside.gui.container.GuiDisintegrator;
import io.github.phantamanta44.cliffside.gui.container.GuiLunarDistillery;
import io.github.phantamanta44.cliffside.gui.container.GuiSmelter;
import io.github.phantamanta44.cliffside.handler.ExplosiveEnergizationHandler;
import io.github.phantamanta44.cliffside.handler.GuiHandler;
import io.github.phantamanta44.cliffside.handler.NintendiumCraftHandler;
import io.github.phantamanta44.cliffside.handler.WrenchHandler;
import io.github.phantamanta44.cliffside.handler.ZfgBootHandler;
import io.github.phantamanta44.cliffside.inventory.ContainerAlchemicalBurner;
import io.github.phantamanta44.cliffside.inventory.ContainerDisintegrator;
import io.github.phantamanta44.cliffside.inventory.ContainerLunarDistillery;
import io.github.phantamanta44.cliffside.inventory.ContainerSmelter;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.recipe.MasterRecipeManager;
import io.github.phantamanta44.cliffside.tile.TileAlchemicalBurner;
import io.github.phantamanta44.cliffside.tile.TileCreativeLumenSource;
import io.github.phantamanta44.cliffside.tile.TileDisintegrator;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneEnergizer;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneNode;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneStorage;
import io.github.phantamanta44.cliffside.tile.TileLunarDistillery;
import io.github.phantamanta44.cliffside.tile.TileSmelter;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public static CTMRenderer ctmRenderer;

	public void preInit(FMLPreInitializationEvent event) {
		CSBlocks.init();
		CSItems.init();
		registerTileEntities();
	}

	public void init(FMLInitializationEvent event) {
		MasterRecipeManager.registerRecipes();
		MinecraftForge.EVENT_BUS.register(new WrenchHandler());
		MinecraftForge.EVENT_BUS.register(new ExplosiveEnergizationHandler());
		MinecraftForge.EVENT_BUS.register(new ZfgBootHandler());
		MinecraftForge.EVENT_BUS.register(new NintendiumCraftHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(ModCliffside.instance, new GuiHandler());
		registerGuis();
	}

	public void postInit(FMLPostInitializationEvent event) {
		MasterRecipeManager.postInitRecipes();
	}
	
	private void registerGuis() {
		registerGui(TileDisintegrator.class, ContainerDisintegrator.class, GuiDisintegrator.class);
		registerGui(TileAlchemicalBurner.class, ContainerAlchemicalBurner.class, GuiAlchemicalBurner.class);
		registerGui(TileSmelter.class, ContainerSmelter.class, GuiSmelter.class);
		registerGui(TileLunarDistillery.class, ContainerLunarDistillery.class, GuiLunarDistillery.class);
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
		addTEMapping(TileLunarDistillery.class);
	}
	
	private void addTEMapping(Class c) {
		TileEntity.addMapping(c, c.getName());
	}

}
