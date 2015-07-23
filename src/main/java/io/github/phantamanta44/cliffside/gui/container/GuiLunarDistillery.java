package io.github.phantamanta44.cliffside.gui.container;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.inventory.ContainerLunarDistillery;
import io.github.phantamanta44.cliffside.tile.TileLunarDistillery;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiLunarDistillery extends GuiContainerMod {
	
	public static final ResourceLocation guiLocation = new ResourceLocation(GlobalConstants.GUI_PREF + "distill.png");
	public static final ResourceLocation guiLocationActive = new ResourceLocation(GlobalConstants.GUI_PREF + "distill_active.png");
	
	public GuiLunarDistillery(InventoryPlayer ipl, TileLunarDistillery te) {
		super(new ContainerLunarDistillery(ipl, te));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(StatCollector.translateToLocal(LangConstants.CONT_INV_NAME), 8, this.ySize - 96 + 2, 4210752);
		String invName = StatCollector.translateToLocal(LangConstants.CONT_LUNA_NAME);
		int nameLength = fontRendererObj.getStringWidth(invName);
		TileLunarDistillery tile = ((ContainerLunarDistillery)inventorySlots).getTile();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(guiLocation);
		int msBuffer = (int)(55F * tile.getMoonshineBufferPercentage());
		int energy = (int)(16F * ((ContainerLunarDistillery)inventorySlots).getEnergyPercentage());
		drawTexturedModalRect(167, 8 + (55 - msBuffer), 176, 55 - msBuffer, 1, msBuffer);
		drawTexturedModalRect(80, 62, 176, 0, energy, 1);
		
		mc.renderEngine.bindTexture(guiLocationActive);
		if (tile.canWork())
			drawTexturedModalRect(8, 8, 0, 0, 156, 55);
		
		drawTexturedModalRect(0, -19, 0, 55, 7, 21);
		int i = 0;
		while (i < nameLength + 12)
			drawTexturedModalRect(3 + i++, -19, 6, 55, 1, 19);
		drawTexturedModalRect(3 + i, -19, 29, 55, 5, 19);
		
		int mX = mouseX - guiLeft, mY = mouseY - guiTop;
		if (func_146978_c(167, 8, 1, 55, mouseX, mouseY))
			drawHoveringText(tile.getMoonshineBuffer() + " / 10000 mB Moonshine", mX, mY);
		if (func_146978_c(80, 62, 16, 1, mouseX, mouseY))
			drawHoveringText(tile.getStoredEnergy() + " Lm", mX, mY);
		
		fontRendererObj.drawString(invName, 8, -12, GlobalConstants.GUI_FONT_COLOR);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float what, int are, int these) {
		mc.renderEngine.bindTexture(guiLocation);
		int x = width / 2 - xSize / 2, y = height / 2 - ySize / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
}
