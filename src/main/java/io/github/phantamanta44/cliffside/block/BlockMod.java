package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.ctm.BasicSubmapManager;
import io.github.phantamanta44.cliffside.ctm.ICTMBlock;
import io.github.phantamanta44.cliffside.ctm.ISubmapManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMod extends Block implements ICTMBlock {

	protected ISubmapManager smMan;
	
	public BlockMod(Material material) {
		super(material);
		addToCreative();
	}

	@Override
	public Block setBlockName(String name) {
		if (GameRegistry.findBlock(GlobalConstants.MOD_ID, name) == null)
			GameRegistry.registerBlock(this, name);
		return super.setBlockName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		smMan = new BasicSubmapManager(getUnlocalizedName().replaceAll("tile\\.", ""));
		smMan.registerIcons(GlobalConstants.MOD_ID, this, registry);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int face) {
		return smMan.getIcon(world, x, y, z, face);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int face, int meta) {
		return smMan.getIcon(face, meta);
	}
	
	public void addToCreative() {
		setCreativeTab(ModCliffside.tabCS);
	}
	
	@Override
	public int getRenderType() {
		return ModCliffside.proxy.ctmRenderer.getRenderId();
	}

	@Override
	public ISubmapManager getManager(IBlockAccess world, int x, int y, int z, int meta) {
		return smMan;
	}

	@Override
	public ISubmapManager getManager(int meta) {
		return smMan;
	}

	@Override
	public int getActualRenderMode(int meta) {
		return 0;
	}

}
