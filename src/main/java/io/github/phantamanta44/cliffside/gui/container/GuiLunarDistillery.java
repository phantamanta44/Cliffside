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
		TileLunarDistillery tile = ((ContainerLunarDistillery)inventorySlots).getTile();
		mc.renderEngine.bindTexture(guiLocation);
		int msBuffer = (int)(55F * tile.getMoonshineBufferPercentage());
		int energy = (int)(16F * ((ContainerLunarDistillery)inventorySlots).getEnergyPercentage());
		drawTexturedModalRect(167, 8 + (55 - msBuffer), 176, 55 - msBuffer, 1, msBuffer);
		drawTexturedModalRect(80, 62, 176, 0, energy, 1);
		
		mc.renderEngine.bindTexture(guiLocationActive);
		if (tile.canWork())
			drawTexturedModalRect(8, 8, 0, 0, 156, 55);
		
		int mX = mouseX - guiLeft, mY = mouseY - guiTop;
		if (func_146978_c(167, 8, 1, 55, mouseX, mouseY))
			drawHoveringText(tile.getMoonshineBuffer() + " / 10000 mB Moonshine", mX, mY);
		if (func_146978_c(80, 62, 16, 1, mouseX, mouseY))
			drawHoveringText(tile.getStoredEnergy() + " Lm", mX, mY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float what, int are, int these) {
		mc.renderEngine.bindTexture(guiLocation);
		int x = width / 2 - xSize / 2, y = height / 2 - ySize / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
}
