package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.ModCliffside;
import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.IconHelper;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModTool extends ItemTool {

	private static final Set axeSet = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});
	private static final Set pickSet = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	private static final Set spadeSet = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});
	private static final Set emptySet = Sets.newHashSet();

	protected ToolType type;
	protected EnumRarity rarity = EnumRarity.common;
	protected String tooltip;
	
	public ItemModTool(ToolType t, ToolMaterial material) {
		super(t.dmgBoost, material, t.effectiveAgainst);
		type = t;
	}

	public ItemModTool(float dmgBoost, ToolMaterial material, Set effectiveAgainst) {
		this(dmgBoost, material, effectiveAgainst, ToolType.NONE);
	}
	
	public ItemModTool(float dmgBoost, ToolMaterial material, Set effectiveAgainst, ToolType t) {
		super(dmgBoost, material, effectiveAgainst);
		type = t;
		setCreativeTab(ModCliffside.tabCS);
	}

	@Override
	public Item setUnlocalizedName(String name) {
		GameRegistry.registerItem(this, name);
		return super.setUnlocalizedName(name);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemstack) {
		return super.getUnlocalizedNameInefficiently(itemstack).replaceAll("item\\.", "item." + GlobalConstants.MOD_PREF);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registry) {
		itemIcon = IconHelper.forItem(registry, this);
	}
	
	public ItemModTool setRarity(EnumRarity level) {
		rarity = level;
		return this;
	}
	
	public ItemModTool setTooltip(String key) {
		tooltip = key;
		return this;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		if (tooltip != null && !tooltip.isEmpty())
			info.add(StatCollector.translateToLocal(tooltip));
	}
	
	@Override
	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		ItemStack mat = toolMaterial.getRepairItemStack();
		if (mat != null && OreDictionary.itemMatches(mat, p_82789_2_, false))
			return true;
		return super.getIsRepairable(p_82789_1_, p_82789_2_);
	}
	
	@Override
	public float func_150893_a(ItemStack tool, Block block) {
		switch (type) {
		case AXE:
			return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? super.func_150893_a(tool, block) : this.efficiencyOnProperMaterial;
		case PICKAXE:
			return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? super.func_150893_a(tool, block) : this.efficiencyOnProperMaterial;
		case SWORD:
			if (block == Blocks.web)
				return 15.0F;
			Material material = block.getMaterial();
			return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
		default:
			return super.func_150893_a(tool, block);
		}
	}
	
	@Override
	public boolean func_150897_b(Block block) {
		switch (type) {
		case PICKAXE:
			return block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? true : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
		case SPADE:
			return block == Blocks.snow_layer ? true : block == Blocks.snow;
		case SWORD:
			return block == Blocks.web;
		default:
			return super.func_150897_b(block);
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (type == ToolType.SWORD)
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return type == ToolType.SWORD ? EnumAction.block : EnumAction.none;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return type == ToolType.SWORD ? 72000 : 0;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
		if (type == ToolType.SWORD) {
			if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
				stack.damageItem(2, player);
			return true;
		}
		return super.onBlockDestroyed(stack, world, block, x, y, z, player);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
		stack.damageItem(type == ToolType.SWORD ? 1 : 2, player);
		return true;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int face, float subX, float subY, float subZ)
	{
		if (type != ToolType.HOE)
			return false;
		if (!player.canPlayerEdit(x, y, z, face, stack))
			return false;
		
		UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
		if (MinecraftForge.EVENT_BUS.post(event))
			return false;

		if (event.getResult() == Result.ALLOW) {
			stack.damageItem(1, player);
			return true;
		}

		Block block = world.getBlock(x, y, z);

		if (face != 0 && world.getBlock(x, y + 1, z).isAir(world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt)) {
			Block block1 = Blocks.farmland;
			world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

			if (world.isRemote)
				return true;
			world.setBlock(x, y, z, block1);
			stack.damageItem(1, player);
			return true;
		}
		return false;
	}
	
	public static enum ToolType {
		
		AXE(3F, axeSet),
		PICKAXE(2F, pickSet),
		SPADE(1F, spadeSet),
		SWORD(4F, emptySet),
		HOE(0F, emptySet),
		NONE(0F, emptySet);
		
		public final float dmgBoost;
		public final Set effectiveAgainst;
		
		private ToolType(float dmg, Set effective) {
			dmgBoost = dmg;
			effectiveAgainst = effective;
		}
		
	}
	
}
