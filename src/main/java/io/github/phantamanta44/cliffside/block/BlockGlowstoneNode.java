package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneNode;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlowstoneNode extends BlockModSubs implements ITileEntityProvider {
	
	protected NodeType type;

	public BlockGlowstoneNode(NodeType nodeType) {
		super(Material.iron, 16);
		type = nodeType;
		setHardness(2.5F);
		setResistance(5.0F);
		setBlockName(type.blockName);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int face, float subX, float subY, float subZ) {
		if (!world.isRemote) {
			TileGlowstoneNode node = (TileGlowstoneNode)world.getTileEntity(x, y, z);
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[Glowstone Node] " + EnumChatFormatting.GRAY + ((ILumenStorage)node).getStoredEnergy() + " / " + ((ILumenStorage)node).getMaximumEnergy() + " Lumens"));
		}
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileGlowstoneNode(type, meta);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return 7;
	}
	
	@Override
	public void addToCreative() {
		setCreativeTab(ModCliffside.tabNode);
	}
	
	public static enum NodeType {
		
		BASIC(BlockConstants.GS_NODE_BASIC_NAME),
		NORMAL(BlockConstants.GS_NODE_NAME),
		DEEP(BlockConstants.GS_NODE_DEEP_NAME),
		FAST(BlockConstants.GS_NODE_FAST_NAME);
		
		public final String blockName;
		
		private NodeType(String name) {
			blockName = name;
		}
		
	}

}
