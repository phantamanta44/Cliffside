package io.github.phantamanta44.cliffside.item.tool;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;

public class ToolAlchemical extends ItemModTool {

	public ToolAlchemical(ToolType t) {
		super(t, CSMaterialTypes.toolsAlchemical);
	}
	
	public static class ItemAlchemicalAxe extends ToolAlchemical {

		public ItemAlchemicalAxe() {
			super(ToolType.AXE);
			setUnlocalizedName(ItemConstants.ALCHEM_AXE_NAME);
		}
		
	}
	
	public static class ItemAlchemicalPickaxe extends ToolAlchemical {

		public ItemAlchemicalPickaxe() {
			super(ToolType.PICKAXE);
			setUnlocalizedName(ItemConstants.ALCHEM_PICK_NAME);
		}
		
	}
	
	public static class ItemAlchemicalSpade extends ToolAlchemical {

		public ItemAlchemicalSpade() {
			super(ToolType.SPADE);
			setUnlocalizedName(ItemConstants.ALCHEM_SPADE_NAME);
		}
		
	}
	
	public static class ItemAlchemicalSword extends ToolAlchemical {

		public ItemAlchemicalSword() {
			super(ToolType.SWORD);
			setUnlocalizedName(ItemConstants.ALCHEM_SWORD_NAME);
		}
		
	}
	
	public static class ItemAlchemicalHoe extends ToolAlchemical {

		public ItemAlchemicalHoe() {
			super(ToolType.HOE);
			setUnlocalizedName(ItemConstants.ALCHEM_HOE_NAME);
		}
		
	}
	
}
