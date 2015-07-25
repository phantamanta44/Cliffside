package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;
import net.minecraft.item.EnumRarity;

public class ToolNintendium extends ItemModTool {

	public ToolNintendium(ToolType t) {
		super(t, CSMaterialTypes.toolsNintendium);
		setRarity(EnumRarity.epic);
		setTooltip(LangConstants.UNBREAKABLE_DESC);
		setMaxDamage(-1);
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
	
	public static class ItemNintendiumSword extends ToolNintendium {

		public ItemNintendiumSword() {
			super(ToolType.SWORD);
			setUnlocalizedName(ItemConstants.NINT_SWORD_NAME);
		}
		
	}
	
	public static class ItemNintendiumHoe extends ToolNintendium {

		public ItemNintendiumHoe() {
			super(ToolType.HOE);
			setUnlocalizedName(ItemConstants.NINT_HOE_NAME);
		}
		
	}
	
}
