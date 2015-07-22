package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.item.block.ItemBlockCompressed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockOre extends BlockModSubs {
	
	public static final int MITHRIL = 0, SILVER = 1;
	
	public BlockOre() {
		super(Material.rock, 2);
		setHardness(1.2F);
		setResistance(4.8F);
		setBlockName(BlockConstants.ORE_NAME);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case MITHRIL:
		case SILVER:
			return 4;
		default:
			return 0;
		}
	}
	
}
