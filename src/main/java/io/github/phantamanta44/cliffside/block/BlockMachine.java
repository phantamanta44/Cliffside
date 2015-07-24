package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;
import io.github.phantamanta44.cliffside.tile.TileAlchemicalBurner;
import io.github.phantamanta44.cliffside.tile.TileDisintegrator;
import io.github.phantamanta44.cliffside.tile.TileGlowstoneEnergizer;
import io.github.phantamanta44.cliffside.tile.TileLeylineAggregator;
import io.github.phantamanta44.cliffside.tile.TileLunarDistillery;
import io.github.phantamanta44.cliffside.tile.TileSmelter;
import io.github.phantamanta44.cliffside.tile.base.IActiveMachine;
import io.github.phantamanta44.cliffside.tile.base.IDirectional;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachine extends BlockModSubs implements ITileEntityProvider {

	public static final int ALCHEM_BURNER = 0, ENERGIZER = 1, DISINTEGRATOR = 2, SMELTER = 3, ADV_BURNER = 4, LEYLINE_AGGRO = 5, LUNA_DISTILL = 6;
	IIcon[] icons; // Too lazy to write an ISubblockManager
	
	public BlockMachine() {
		super(Material.iron, 7);
		setHardness(5F);
		setResistance(10F);
		setBlockName(BlockConstants.ALCHEM_MACHINE_NAME);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister registry) {
		icons = new IIcon[subblockCount * 2 + 1];
		for (int i = 0; i < subblockCount; i++) {
			icons[i * 2] = IconHelper.forName(registry, getUnlocalizedName().replaceAll("tile\\.", "") + i + "_off");
			icons[i * 2 + 1] = IconHelper.forName(registry, getUnlocalizedName().replaceAll("tile\\.", "") + i + "_on");
		}
		icons[icons.length - 1] = registry.registerIcon(GlobalConstants.MOD_PREF + BlockConstants.ALCHEM_MACHINE_SIDE_TEX);
	}
	
	public int getFrontFace(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof IDirectional)
			return ((IDirectional)tile).getFrontFace();
		return -1;
	}
	
	public boolean isActive(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof IActiveMachine)
			return ((IActiveMachine)tile).isActive();
		return false;
	}
	
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side != getFrontFace(world, x, y, z))
			return icons[icons.length - 1];
		int meta = world.getBlockMetadata(x, y, z);
		return icons[meta * 2 + (isActive(world, x, y, z) ? 1 : 0)];
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side != 3)
			return icons[icons.length - 1];
		return icons[meta * 2];
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return isActive(world, x, y, z) ? 10 : 0;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case ALCHEM_BURNER:
			return new TileAlchemicalBurner();
		case ENERGIZER:
			return new TileGlowstoneEnergizer();
		case DISINTEGRATOR:
			return new TileDisintegrator();
		case SMELTER:
			return new TileSmelter();
		case ADV_BURNER:
			return new TileAlchemicalBurner(72);
		case LEYLINE_AGGRO:
			return new TileLeylineAggregator();
		case LUNA_DISTILL:
			return new TileLunarDistillery();
		}
		return null;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		int face = (int)Math.floor((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (face == 0)
			face = 2;
		else if (face == 1)
			face = 5;
		else if (face == 2)
			face = 3;
		else
			face = 4;
		((IDirectional)world.getTileEntity(x, y, z)).setFrontFace(face);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof IInventory) {
			IInventory inv = (IInventory)te;
			ItemStack stack;
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				if ((stack = inv.getStackInSlot(i)) != null) {
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
					while (stack.stackSize > 0) {
						int subSize = Math.min(stack.stackSize, world.rand.nextInt(21) + 10);
						stack.stackSize -= subSize;
						EntityItem ent = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(stack.getItem(), subSize, stack.getItemDamage()));
						if (stack.hasTagCompound())
							ent.getEntityItem().setTagCompound(stack.getTagCompound());
						float f3 = 0.05F;
						ent.motionX = (double)((float)world.rand.nextGaussian() * f3);
						ent.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
						ent.motionZ = (double)((float)world.rand.nextGaussian() * f3);
						world.spawnEntityInWorld(ent);
					}
				}
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int face, float subX, float subY, float subZ) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y, z);
			switch (world.getBlockMetadata(x, y, z)) {
			case ALCHEM_BURNER:
			case DISINTEGRATOR:
			case SMELTER:
			case ADV_BURNER:
			case LUNA_DISTILL:
				player.openGui(ModCliffside.instance, 420, world, x, y, z);
				break;
			}
		}
		return true;
	}
	
}
