package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneStorage;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIlluminationLamp extends BlockModSubs implements ITileEntityProvider {

	public BlockIlluminationLamp() {
		super(Material.iron, 10);
		setHardness(3F);
		setResistance(5.0F);
		setBlockName(BlockConstants.ILLUMINATION_LAMP_NAME);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int face, float subX, float subY, float subZ) {
		if (!world.isRemote) {
			TileGlowstoneStorage lamp = (TileGlowstoneStorage)world.getTileEntity(x, y, z);
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[Illumination Lamp] " + EnumChatFormatting.GRAY + ((ILumenStorage)lamp).getStoredEnergy() + " / " + ((ILumenStorage)lamp).getMaximumEnergy() + " Lumens"));
		}
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileGlowstoneStorage((int)(64 * Math.pow(4, meta)), 8);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return 5 + world.getBlockMetadata(x, y, z);
	}
	
	@Override
	public void addToCreative() {
		setCreativeTab(ModCliffside.tabNode);
	}

}
