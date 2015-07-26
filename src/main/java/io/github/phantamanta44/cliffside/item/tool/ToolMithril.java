package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;

public class ToolMithril extends ItemModTool {

	public ToolMithril(ToolType t) {
		super(t, CSMaterialTypes.toolsMithril);
		setTooltip(LangConstants.MAGICAL_DESC);
	}
	
	public static class ItemMithrilAxe extends ToolMithril {

		public ItemMithrilAxe() {
			super(ToolType.AXE);
			setUnlocalizedName(ItemConstants.MITHRIL_AXE_NAME);
		}
		
	}
	
	public static class ItemMithrilPickaxe extends ToolMithril {

		public ItemMithrilPickaxe() {
			super(ToolType.PICKAXE);
			setUnlocalizedName(ItemConstants.MITHRIL_PICK_NAME);
		}
		
	}
	
	public static class ItemMithrilSpade extends ToolMithril {

		public ItemMithrilSpade() {
			super(ToolType.SPADE);
			setUnlocalizedName(ItemConstants.MITHRIL_SPADE_NAME);
		}
		
	}
	
	public static class ItemMithrilSword extends ItemModSword {

		public ItemMithrilSword() {
			super(CSMaterialTypes.toolsMithril);
			setUnlocalizedName(ItemConstants.MITHRIL_SWORD_NAME);
			setTooltip(LangConstants.MAGICAL_DESC);
		}
		
	}
	
	public static class ItemMithrilHoe extends ToolMithril {

		public ItemMithrilHoe() {
			super(ToolType.HOE);
			setUnlocalizedName(ItemConstants.MITHRIL_HOE_NAME);
		}
		
	}
	
}
