package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.item.block.ItemBlockCreativeLumenSource;
import io.github.phantamanta44.cliffside.tile.TileCreativeLumenSource;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockCreativeLumenSource extends BlockModSubs implements ITileEntityProvider {

	public static final int LOW_CURRENT = 0, MID_CURRENT = 1, HIGH_CURRENT = 2, ULTRA_CURRENT = 3;
	
	public BlockCreativeLumenSource() {
		super(Material.iron, 4);
		setHardness(50F);
		setResistance(50F);
		setBlockName(BlockConstants.LUMEN_SOURCE_NAME);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int face, float subX, float subY, float subZ) {
		if (!world.isRemote)
			player.addChatComponentMessage(new ChatComponentText("Infinity / 0 lumens"));
		return true;
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockCreativeLumenSource.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileCreativeLumenSource(meta == 3 ? -1 : (meta == 2 ? 64 : (meta == 1 ? 8 : 4)));
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return 15;
	}
	
	@Override
	public void addToCreative() {
		setCreativeTab(ModCliffside.tabNode);
	}

}
