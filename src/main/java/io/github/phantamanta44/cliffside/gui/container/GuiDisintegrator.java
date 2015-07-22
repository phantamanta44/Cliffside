package io.github.phantamanta44.cliffside.gui.container;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.inventory.ContainerDisintegrator;
import io.github.phantamanta44.cliffside.tile.TileDisintegrator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiDisintegrator extends GuiContainerMod {
	
	public static final ResourceLocation guiLocation = new ResourceLocation(GlobalConstants.GUI_PREF + "processing.png");
	
	public GuiDisintegrator(InventoryPlayer ipl, TileDisintegrator te) {
		super(new ContainerDisintegrator(ipl, te));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String invName = StatCollector.translateToLocal(LangConstants.CONT_DISINT_NAME);
		int nameXPos = xSize / 2 - fontRendererObj.getStringWidth(invName) / 2;
		fontRendererObj.drawString(invName, nameXPos, 6, GlobalConstants.GUI_FONT_COLOR);
		TileDisintegrator tile = ((ContainerDisintegrator)inventorySlots).getTile();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(guiLocation);
		drawTexturedModalRect(79, 34, 176, 0, 1 + (int)(22F * ((ContainerDisintegrator)inventorySlots).getProgress()), 17);
		drawTexturedModalRect(56, 54, 176, 17, (int)(16F * ((ContainerDisintegrator)inventorySlots).getEnergy()), 1);
		
		int mX = mouseX - guiLeft, mY = mouseY - guiTop;
		if (func_146978_c(80, 34, 22, 16, mouseX, mouseY))
			drawHoveringText((int)(tile.getProgress() * 100)+ "%", mX, mY);
		if (func_146978_c(56, 54, 16, 2, mouseX, mouseY))
			drawHoveringText(tile.getStoredEnergy() + " Lm", mX, mY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float what, int are, int these) {
		mc.renderEngine.bindTexture(guiLocation);
		int x = width / 2 - xSize / 2, y = height / 2 - ySize / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
}
