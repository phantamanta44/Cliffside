package io.github.phantamanta44.cliffside.gui.container;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import scala.actors.threadpool.Arrays;

public abstract class GuiContainerMod extends GuiContainer {

	public GuiContainerMod(Container cont) {
		super(cont);
	}

	protected void drawHoveringText(String text, int x, int y) {
		drawHoveringText(Arrays.asList(new String[] {text}), x, y, fontRendererObj);
	}
	
}
