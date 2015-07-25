package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;

public class ToolCatalytical extends ItemModTool {

	public ToolCatalytical(ToolType t) {
		super(t, CSMaterialTypes.toolsCatalytical);
	}
	
	public static class ItemCatalyticalAxe extends ToolCatalytical {

		public ItemCatalyticalAxe() {
			super(ToolType.AXE);
			setUnlocalizedName(ItemConstants.CATAL_AXE_NAME);
		}
		
	}
	
	public static class ItemCatalyticalPickaxe extends ToolCatalytical {

		public ItemCatalyticalPickaxe() {
			super(ToolType.PICKAXE);
			setUnlocalizedName(ItemConstants.CATAL_PICK_NAME);
		}
		
	}
	
	public static class ItemCatalyticalSpade extends ToolCatalytical {

		public ItemCatalyticalSpade() {
			super(ToolType.SPADE);
			setUnlocalizedName(ItemConstants.CATAL_SPADE_NAME);
		}
		
	}
	
	public static class ItemCatalyticalSword extends ToolCatalytical {

		public ItemCatalyticalSword() {
			super(ToolType.SWORD);
			setUnlocalizedName(ItemConstants.CATAL_SWORD_NAME);
		}
		
	}
	
	public static class ItemCatalyticalHoe extends ToolCatalytical {

		public ItemCatalyticalHoe() {
			super(ToolType.HOE);
			setUnlocalizedName(ItemConstants.CATAL_HOE_NAME);
		}
		
	}
	
}
