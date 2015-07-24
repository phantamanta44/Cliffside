package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import io.github.phantamanta44.cliffside.ctm.ISubmapManager;
import io.github.phantamanta44.cliffside.ctm.SubblockSubmapManager;
import io.github.phantamanta44.cliffside.item.block.ItemBlockWithMetadataAndName;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockModSubs extends BlockMod {

	protected final int subblockCount;
	
	public BlockModSubs(Material material, int blocks) {
		super(material);
		subblockCount = blocks;
	}

	@Override
	public void getSubBlocks(Item parent, CreativeTabs tab, List items) {
		for (int i = 0; i < subblockCount; i++)
			items.add(new ItemStack(parent, 1, i));
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	public Block setBlockName(String name) {
		if (GameRegistry.findBlock(GlobalConstants.MOD_ID, name) == null)
			GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		smMan = new SubblockSubmapManager(getUnlocalizedName().replaceAll("tile\\.", ""));
		smMan.registerIcons(GlobalConstants.MOD_ID, this, registry, subblockCount);
	}
	
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int face) {
		return smMan.getIcon(world, x, y, z, face);
	}
	
	@Override
	public IIcon getIcon(int id, int meta) {
		return smMan.getIcon(id, meta);
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
