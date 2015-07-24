package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.BlockConstants;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.ctm.ICTMBlock;
import io.github.phantamanta44.cliffside.ctm.ISubmapManager;
import io.github.phantamanta44.cliffside.ctm.RenderBlocksCTM;
import io.github.phantamanta44.cliffside.ctm.TextureSubmap;
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
import net.minecraft.client.renderer.RenderBlocks;
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
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMachine extends BlockModSubs implements ITileEntityProvider {

	public static final int ALCHEM_BURNER = 0, ENERGIZER = 1, DISINTEGRATOR = 2, SMELTER = 3, ADV_BURNER = 4, LEYLINE_AGGRO = 5, LUNA_DISTILL = 6;
	
	public BlockMachine() {
		super(Material.iron, 7);
		setHardness(5F);
		setResistance(10F);
		setBlockName(BlockConstants.ALCHEM_MACHINE_NAME);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister registry) {
		smMan = new MachineSubmapManager(getUnlocalizedName(), this);
		smMan.registerIcons(GlobalConstants.MOD_ID, this, registry, subblockCount);
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
		return smMan.getIcon(world, x, y, z, side);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return smMan.getIcon(side, meta);
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
	
	private static class MachineSubmapManager implements ISubmapManager {

		@SideOnly(Side.CLIENT)
		private static RenderBlocksCTM rb;
		private BlockMachine blk;
		private TextureSubmap sideIcon;
		private TextureSubmap[] iconsOff;
		private TextureSubmap[] iconsOn;
		private String tex;
		
		public MachineSubmapManager(String textureName, BlockMachine block) {
			tex = textureName.replaceAll("tile\\.", "");
			blk = block;
		}
		
		@Override
		public IIcon getIcon(int side, int meta) {
			if (side != 3)
				return sideIcon.getBaseIcon();
			return iconsOff[meta].getBaseIcon();
		}

		@Override
		public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
			if (side != blk.getFrontFace(world, x, y, z))
				return sideIcon.getBaseIcon();
			int meta = world.getBlockMetadata(x, y, z);
			if (blk.isActive(world, x, y, z))
				return iconsOn[meta].getBaseIcon();
			return iconsOff[meta].getBaseIcon();
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(String modName, Block block, IIconRegister register) {
			throw new IllegalStateException("Renderer for " + tex + " has subblocks!");
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(String modName, Block block, IIconRegister register, int subBlocks) {
			iconsOff = new TextureSubmap[subBlocks];
			iconsOn = new TextureSubmap[subBlocks];
			for (int i = 0; i < subBlocks; i++) {
				String texName = modName + ":" + tex + i;
				iconsOff[i] = new TextureSubmap(register.registerIcon(texName + "_off"), 2, 2);
				iconsOn[i] = new TextureSubmap(register.registerIcon(texName + "_on"), 2, 2);
			}
			sideIcon = new TextureSubmap(register.registerIcon(modName + ":" + BlockConstants.ALCHEM_MACHINE_SIDE_TEX), 2, 2);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public RenderBlocks createRenderContext(RenderBlocks rendererOld, Block block, IBlockAccess world, int meta, int face) {
			throw new IllegalStateException("Cannot create render context for " + tex + "; it has no CTM!");
		}

		@Override
		public void preRenderSide(RenderBlocks renderBlocksCTM,
				IBlockAccess blockAccess, int bx, int by, int bz,
				ForgeDirection face) {
		}

		@Override
		public void postRenderSide(RenderBlocks renderBlocksCTM,
				IBlockAccess blockAccess, int bx, int by, int bz,
				ForgeDirection face) {
		}
		
	}
	
}
