package io.github.phantamanta44.cliffside.handler;

import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.ItemWrench;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage;
import io.github.phantamanta44.cliffside.tile.base.IWrenchable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WrenchHandler {

	@SubscribeEvent
	public void onWrench(PlayerInteractEvent event) {
		if (!event.world.isRemote) {
			if (event.action != Action.RIGHT_CLICK_BLOCK)
				return;
			TileEntity te;
			if ((te = event.world.getTileEntity(event.x, event.y, event.z)) == null)
				return;
			if (event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.getCurrentEquippedItem().getItem().equals(CSItems.wrench)) {
				if (event.entityPlayer.getCurrentEquippedItem().getItemDamage() == ItemWrench.WRENCH) {
					if (te instanceof IWrenchable) {
						if (event.entityPlayer.isSneaking())
							((IWrenchable)te).onWrenchUseSneaking(event.face, event.entityPlayer, event.entityPlayer.getCurrentEquippedItem());
						else
							((IWrenchable)te).onWrenchUse(event.face, event.entityPlayer, event.entityPlayer.getCurrentEquippedItem());
						event.setCanceled(true);
						event.useItem = Result.ALLOW;
					}
				}
				else if (event.entityPlayer.getCurrentEquippedItem().getItemDamage() == ItemWrench.READER) {
					if (te instanceof ILumenStorage) {
						event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "[Illumination Reader] " + EnumChatFormatting.GRAY + ((ILumenStorage)te).getStoredEnergy() + " / " + ((ILumenStorage)te).getMaximumEnergy() + " Lumens"));
						event.setCanceled(true);
						event.useItem = Result.ALLOW;
					}
				}
				else if (event.entityPlayer.getCurrentEquippedItem().getItemDamage() == ItemWrench.DEBUG) {
					if (te instanceof IDebuggable) {
						((IDebuggable)te).onDebug(event.entityPlayer);
						event.setCanceled(true);
						event.useItem = Result.ALLOW;
					}
				}
			}
		}
	}
	
}
