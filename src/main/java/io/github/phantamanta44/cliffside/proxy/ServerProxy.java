package io.github.phantamanta44.cliffside.proxy;

import io.github.phantamanta44.cliffside.block.BlockOre;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.util.BlockWithMeta;
import io.github.phantamanta44.cliffside.worldgen.WorldGenSimple;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		GameRegistry.registerWorldGenerator(new WorldGenSimple(new BlockWithMeta(CSBlocks.ore, BlockOre.MITHRIL), 0, new BlockWithMeta(Blocks.stone), 4, 18, 4, 4), 8);
		GameRegistry.registerWorldGenerator(new WorldGenSimple(new BlockWithMeta(CSBlocks.ore, BlockOre.SILVER), 0, new BlockWithMeta(Blocks.stone), 4, 64, 10, 12), 8);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
