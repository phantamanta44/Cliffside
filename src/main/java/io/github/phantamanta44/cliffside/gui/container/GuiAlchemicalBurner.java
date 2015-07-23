package io.github.phantamanta44.cliffside.gui.container;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.inventory.ContainerAlchemicalBurner;
import io.github.phantamanta44.cliffside.tile.TileAlchemicalBurner;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import scala.actors.threadpool.Arrays;

public class GuiAlchemicalBurner extends GuiContainerMod {
	
	public static final ResourceLocation guiLocation = new ResourceLocation(GlobalConstants.GUI_PREF + "burner.png");
	
	public GuiAlchemicalBurner(InventoryPlayer ipl, TileAlchemicalBurner te) {
		super(new ContainerAlchemicalBurner(ipl, te));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(StatCollector.translateToLocal(LangConstants.CONT_INV_NAME), 8, this.ySize - 96 + 2, 4210752);
		String invName = StatCollector.translateToLocal(LangConstants.CONT_BURNER_NAME);
		int nameXPos = xSize / 2 - fontRendererObj.getStringWidth(invName) / 2;
		fontRendererObj.drawString(invName, nameXPos, 6, GlobalConstants.GUI_FONT_COLOR);
		TileAlchemicalBurner tile = ((ContainerAlchemicalBurner)inventorySlots).getTile();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(guiLocation);
		int burnTime = (int)(16F * ((ContainerAlchemicalBurner)inventorySlots).getBurnTimePercentage());
		int work = (int)(16F * ((ContainerAlchemicalBurner)inventorySlots).getProgressPercentage());
		int energy = (int)(16F * ((ContainerAlchemicalBurner)inventorySlots).getEnergyPercentage());
		drawTexturedModalRect(80, 23 + (16 - burnTime), 176, 16 - burnTime, 2, burnTime);
		drawTexturedModalRect(87, 23 + (16 - work), 178, 16 - work, 2, work);
		drawTexturedModalRect(94, 23 + (16 - energy), 180, 16 - energy, 2, energy);
		
		int mX = mouseX - guiLeft, mY = mouseY - guiTop;
		if (func_146978_c(80, 23, 2, 16, mouseX, mouseY))
			drawHoveringText(tile.getBurnTime() + " Ticks", mX, mY);
		if (func_146978_c(87, 23, 2, 16, mouseX, mouseY))
			drawHoveringText(tile.getWork() + " / " + tile.getWorkNeeded() + " Work", mX, mY);
		if (func_146978_c(94, 23, 2, 16, mouseX, mouseY))
			drawHoveringText(tile.getStoredEnergy() + " Lm", mX, mY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float what, int are, int these) {
		mc.renderEngine.bindTexture(guiLocation);
		int x = width / 2 - xSize / 2, y = height / 2 - ySize / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
}
