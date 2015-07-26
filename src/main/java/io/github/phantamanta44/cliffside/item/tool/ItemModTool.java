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
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
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

	private static final Set axeSet = Sets.newHashSet(new Material[] {Material.wood, Material.leaves, Material.vine, Material.circuits, Material.cactus, Material.gourd});
	private static final Set pickSet = Sets.newHashSet(new Material[] {Material.rock, Material.iron, Material.glass, Material.piston, Material.anvil, Material.circuits});
	private static final Set spadeSet = Sets.newHashSet(new Material[] {Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay});
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
		if (stack.getItem().isDamageable()) {
			float damagePercent = (float)(stack.getMaxDamage() - stack.getItemDamage()) / (float)stack.getMaxDamage();
			String color = (damagePercent == 1F ? EnumChatFormatting.AQUA : (damagePercent > 0.7F ? EnumChatFormatting.GREEN : (damagePercent > 0.5F ? EnumChatFormatting.YELLOW : (damagePercent > 0.2F ? EnumChatFormatting.GOLD : EnumChatFormatting.DARK_RED)))).toString();
			info.add(String.format("Durability: %s%d%%", color, (int)(damagePercent * 100)));
		}
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
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		if (toolClass == type.toolClass)
			return toolMaterial.getHarvestLevel();
		return -1;
	}
	
	@Override
	public float getDigSpeed(ItemStack tool, Block block, int meta) {
		if (type.toolClass.equals(block.getHarvestTool(meta)))
			return toolMaterial.getEfficiencyOnProperMaterial();
		if (type.effectiveAgainst.contains(block.getMaterial()) && toolMaterial.getHarvestLevel() > block.getHarvestLevel(meta))
			return toolMaterial.getEfficiencyOnProperMaterial();
		return super.getDigSpeed(tool, block, meta);
	}
	
	@Override
	public boolean canHarvestBlock(Block block, ItemStack tool) {
		if (type.effectiveAgainst.contains(block.getMaterial()))
			return true;
		return super.canHarvestBlock(block, tool);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
		stack.damageItem(2, player);
		return true;
	}
	
	@Override
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
	
	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}
	
	public static enum ToolType {
		
		AXE(3F, axeSet, "axe"),
		PICKAXE(2F, pickSet, "pickaxe"),
		SPADE(1F, spadeSet, "shovel"),
		HOE(0F, emptySet, "hoe"),
		NONE(0F, emptySet, "none");
		
		public final float dmgBoost;
		public final Set effectiveAgainst;
		public final String toolClass;
		
		private ToolType(float dmg, Set effective, String clazz) {
			dmgBoost = dmg;
			effectiveAgainst = effective;
			toolClass = clazz;
		}
		
	}
	
}
