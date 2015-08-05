package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IWarpingGear;
import cpw.mods.fml.common.Optional;

@Optional.Interface(modid="Thaumcraft", iface="api.thaumcraft.IWarpingGear")
public class ToolNintendium extends ItemModTool implements IWarpingGear {

	public ToolNintendium(ToolType t) {
		super(t, CSMaterialTypes.toolsNintendium);
		setRarity(EnumRarity.epic);
		setTooltip(LangConstants.UNBREAKABLE_DESC);
		setMaxDamage(-1);
	}
	
	@Override
	public boolean isDamageable() {
		return false;
	}
	
	public static class ItemNintendiumAxe extends ToolNintendium {

		public ItemNintendiumAxe() {
			super(ToolType.AXE);
			setUnlocalizedName(ItemConstants.NINT_AXE_NAME);
		}
		
	}
	
	public static class ItemNintendiumPickaxe extends ToolNintendium {

		public ItemNintendiumPickaxe() {
			super(ToolType.PICKAXE);
			setUnlocalizedName(ItemConstants.NINT_PICK_NAME);
		}
		
	}
	
	public static class ItemNintendiumSpade extends ToolNintendium {

		public ItemNintendiumSpade() {
			super(ToolType.SPADE);
			setUnlocalizedName(ItemConstants.NINT_SPADE_NAME);
		}
		
	}
	
	public static class ItemNintendiumSword extends ItemModSword {

		public ItemNintendiumSword() {
			super(CSMaterialTypes.toolsNintendium);
			setUnlocalizedName(ItemConstants.NINT_SWORD_NAME);
			setRarity(EnumRarity.epic);
			setTooltip(LangConstants.UNBREAKABLE_DESC);
			setMaxDamage(-1);
		}
		
		@Override
		public boolean isDamageable() {
			return false;
		}
		
	}
	
	public static class ItemNintendiumHoe extends ToolNintendium {

		public ItemNintendiumHoe() {
			super(ToolType.HOE);
			setUnlocalizedName(ItemConstants.NINT_HOE_NAME);
		}
		
	}

	@Override
	@Optional.Method(modid="Thaumcraft")
	public int getWarp(ItemStack itemstack, EntityPlayer player) {
		return 2;
	}
	
}
