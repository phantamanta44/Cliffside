package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.item.block.ItemBlockTransparent;
import io.github.phantamanta44.cliffside.util.BlockWithMeta;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTransparent extends BlockModSubs {
	
	public static final int QS_GLASS = 0;

	public BlockTransparent() {
		super(Material.glass, 1);
		setStepSound(Block.soundTypeGlass);
		setHardness(0.3F);
		setResistance(1.0F);
		setBlockName(BlockConstants.GLASS_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockTransparent.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;	
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int face) {
		BlockWithMeta block = new BlockWithMeta(
				world.getBlock(x - Facing.offsetsXForSide[face], y - Facing.offsetsYForSide[face], z - Facing.offsetsZForSide[face]),
				world.getBlockMetadata(x - Facing.offsetsXForSide[face], y - Facing.offsetsYForSide[face], z - Facing.offsetsZForSide[face]));
		BlockWithMeta adj = new BlockWithMeta(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
		if (block.getBlock() == adj.getBlock()) {
			if (block.getMeta() == adj.getMeta())
				return false;
		}
		return true;
	}

}
