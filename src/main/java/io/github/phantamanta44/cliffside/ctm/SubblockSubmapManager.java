package io.github.phantamanta44.cliffside.ctm;

import io.github.phantamanta44.cliffside.ModCliffside;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SubblockSubmapManager implements ISubmapManager {

	@SideOnly(Side.CLIENT)
	private static RenderBlocksCTM rb;

	private TextureSubmap[] submap, submapSmall;
	private String textureName;

	/**
	 * @param textureName
	 *            The path to the texture, excluding the mod ID (this is passed into {@link #registerIcons(String, Block, IIconRegister)}).
	 *            <p>
	 *            The CTM texture will be this path, plus {@code "-ctm"}.
	 */
	public SubblockSubmapManager(String textureName) {
		this.textureName = textureName.replaceAll("tile\\.", "");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return submapSmall[meta].getBaseIcon();
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return getIcon(side, world.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(String modName, Block block, IIconRegister register) {
		throw new IllegalStateException("Renderer for " + textureName + " has subblocks!");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(String modName, Block block, IIconRegister register, int subBlocks) {
		submap = new TextureSubmap[subBlocks];
		submapSmall = new TextureSubmap[subBlocks];
		for (int i = 0; i < subBlocks; i++) {
			if (((ICTMBlock)block).getActualRenderMode(i) == ModCliffside.proxy.ctmRenderer.getRenderId())
				submap[i] = new TextureSubmap(register.registerIcon(modName + ":" + textureName + i + ".ctm"), 4, 4);
			submapSmall[i] = new TextureSubmap(register.registerIcon(modName + ":" + textureName + i), 2, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public RenderBlocks createRenderContext(RenderBlocks rendererOld, Block block, IBlockAccess world, int meta, int face) {
		if (rb == null)
			rb = new RenderBlocksCTM();
		rb.setRenderBoundsFromBlock(block);
		rb.submap = submap[meta];
		rb.submapSmall = submapSmall[meta];
		return rb;
	}

	@Override
	public void preRenderSide(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, ForgeDirection side) {
	}

	@Override
	public void postRenderSide(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, ForgeDirection side) {
	}
	
}