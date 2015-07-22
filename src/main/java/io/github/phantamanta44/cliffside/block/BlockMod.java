package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMod extends Block {

	public BlockMod(Material material) {
		super(material);
		addToCreative();
	}

	@Override
	public Block setBlockName(String name) {
		if (GameRegistry.findBlock(GlobalConstants.MOD_ID, name) == null)
			GameRegistry.registerBlock(this, name);
		return super.setBlockName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		blockIcon = IconHelper.forBlock(registry, this);
	}
	
	public void addToCreative() {
		setCreativeTab(ModCliffside.tabCS);
	}

}
