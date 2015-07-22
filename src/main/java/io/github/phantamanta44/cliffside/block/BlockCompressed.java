package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.item.block.ItemBlockCompressed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockCompressed extends BlockModSubs {
	
	public static final int COMP_GS = 0, COMP_RS = 1, COMP_ALCHEM = 2, COMP_CATAL = 3,
			COMP_MITHRIL = 4, COMP_NINT = 5, COMP_ALCHEM_GS = 6, COMP_QS = 7, COMP_SILVER = 8,
			NINT_COMPONENT = 9;
	
	public BlockCompressed() {
		super(Material.iron, 10);
		setHardness(4F);
		setResistance(7.5F);
		setBlockName(BlockConstants.COMPRESSED_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockCompressed.class, name);
		return super.setBlockName(name);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int bX, int bY, int bZ) {
		return true;
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case COMP_GS:
		case COMP_ALCHEM_GS:
		case NINT_COMPONENT:
			return 15;
		default:
			return 0;
		}
	}
	
}
