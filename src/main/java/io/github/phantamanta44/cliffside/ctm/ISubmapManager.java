package io.github.phantamanta44.cliffside.ctm;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface ISubmapManager {

	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int face);
	
	IIcon getIcon(int side, int meta);

	public void preRenderSide(RenderBlocks renderBlocksCTM,
			IBlockAccess blockAccess, int bx, int by, int bz,
			ForgeDirection face);

	public void postRenderSide(RenderBlocks renderBlocksCTM,
			IBlockAccess blockAccess, int bx, int by, int bz,
			ForgeDirection face);

	public RenderBlocks createRenderContext(RenderBlocks rendererOld,
			Block block, IBlockAccess world, int meta);

	void registerIcons(String modName, Block block, IIconRegister register);

	void registerIcons(String modName, Block block, IIconRegister register,
			int subBlocks);
	
}
