package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import io.github.phantamanta44.cliffside.item.block.ItemBlockWithMetadataAndName;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockModSubs extends BlockMod {

	protected final int subblockCount;
	protected IIcon[] icons;
	
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
		icons = new IIcon[subblockCount];
		for (int i = 0; i < subblockCount; i++)
			icons[i] = IconHelper.forBlock(registry, this, i);
	}
	
	@Override
	public IIcon getIcon(int id, int meta) {
		try {
			return icons[meta];
		} catch (IndexOutOfBoundsException ex) {
			return icons[0];
		}
	}
	
}
