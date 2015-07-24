package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.item.block.ItemBlockAlchemical;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAlchemical extends BlockModSubs {
	
	public static final int ALCHEM_GS = 0, CATAL = 1, NINT_MIX = 2, MACHINE_FRAME = 3, ETCHED_OBS = 4;

	public BlockAlchemical() {
		super(Material.iron, 5);
		setStepSound(Block.soundTypeGlass);
		setHardness(3F);
		setResistance(7F);
		setBlockName(BlockConstants.ALCHEMICAL_NAME);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockAlchemical.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case ETCHED_OBS:
		case MACHINE_FRAME:
			return 9;
		default:
			return 15;
		}
	}
	
	@Override
	public int getActualRenderMode(int meta) {
		switch (meta) {
		case MACHINE_FRAME:
		case ETCHED_OBS:
			return getRenderType();
		default:
			return 0;
		}
	}
	
	@Override
	public float getEnchantPowerBonus(World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
		case ETCHED_OBS:
			return 4F;
		default:
			return 0F;
		}
	}

}
