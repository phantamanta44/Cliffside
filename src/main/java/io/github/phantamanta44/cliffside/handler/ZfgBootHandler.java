package io.github.phantamanta44.cliffside.handler;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.item.CSItems;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.world.ExplosionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ZfgBootHandler {

	@SubscribeEvent
	public void onExplode(ExplosionEvent.Detonate event) {
		List<Entity> entities = event.getAffectedEntities();
		for (Entity e : entities) {
			if (e instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)e;
				if (player.inventory.armorInventory[0] != null) {
					if (player.inventory.armorInventory[0].getItem().equals(CSItems.zfgBoots)) {
						player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 160, 2));
						player.addPotionEffect(new PotionEffect(Potion.resistance.id, 32, 9));
					}
				}
			}
		}
	}
	
}
