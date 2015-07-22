package io.github.phantamanta44.cliffside;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GlobalConstants.MOD_ID, version = GlobalConstants.MOD_VERSION, name = GlobalConstants.MOD_NAME)
public class ModCliffside {
	
	@Instance(GlobalConstants.MOD_ID)
	public static ModCliffside instance;
	
	@SidedProxy(clientSide="io.github.phantamanta44.cliffside.proxy.ClientProxy", serverSide="io.github.phantamanta44.cliffside.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabCS = new CreativeTabCliffside();
	public static CreativeTabs tabNode = new CreativeTabCliffside.NodeTab();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
}
