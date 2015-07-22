package io.github.phantamanta44.cliffside.item.resource;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.item.ItemModSubs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemResource extends ItemModSubs {

	public static final int DOUGH = 0, DUST_MEAT = 1, INGOT_MEAT = 2, GEAR_ALCHEM = 3, GEAR_CATAL = 4, GEAR_MITHRIL = 5, GEAR_QS = 6, GEAR_MEAT = 7; 
	
	public ItemResource() {
		super(8);
		setUnlocalizedName(ItemConstants.RESOURCE_ITEM_NAME);
	}
	
	@Override
	public ItemStack onEaten(ItemStack food, World world, EntityPlayer player) {
		player.getFoodStats().addStats(getHealAmount(food), getSaturationAmount(food));
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		if (food.getItemDamage() == GEAR_MEAT) {
			world.playSoundAtEntity(player, "mob.zombie.metal", 0.5F, world.rand.nextFloat() * 0.1F + 0.55F);
			player.attackEntityFrom(DamageSource.cactus, 1.0F);
		}
		food.stackSize--;
		return food;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		 return 32;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case DUST_MEAT:
		case INGOT_MEAT:
		case GEAR_MEAT:
			return EnumAction.eat;
		default:
			return EnumAction.none;
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player.canEat(false))
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	public int getHealAmount(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case DUST_MEAT:
			return 2;
		case INGOT_MEAT:
			return 10;
		case GEAR_MEAT:
			return 20;
		default:
			return 0;
		}
	}
	
	public float getSaturationAmount(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case DUST_MEAT:
			return 1F;
		case INGOT_MEAT:
			return 8F;
		case GEAR_MEAT:
			return 13.5F;
		default:
			return 0F;
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case GEAR_QS:
			return EnumRarity.uncommon;
		default:
			return EnumRarity.common;
		}
	}

}
