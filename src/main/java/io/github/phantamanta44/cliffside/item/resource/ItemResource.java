package io.github.phantamanta44.cliffside.item.resource;

import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.item.ItemModSubs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class ItemResource extends ItemModSubs {

	public static final int DOUGH = 0, DUST_MEAT = 1, INGOT_MEAT = 2, GEAR_ALCHEM = 3, GEAR_CATAL = 4, GEAR_MITHRIL = 5, GEAR_QS = 6, GEAR_MEAT = 7, BOTTLE_MS = 8; 
	public static final ChatStyle EFFECT_STYLE = new ChatStyle();
	
	static {
		EFFECT_STYLE.setColor(EnumChatFormatting.DARK_PURPLE);
		EFFECT_STYLE.setItalic(true);
	}
	
	public ItemResource() {
		super(9);
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
		if (food.getItemDamage() == BOTTLE_MS) {
			doMoonshineStuff(player);
		}
		if (!player.capabilities.isCreativeMode)
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
		case BOTTLE_MS:
			return EnumAction.drink;
		default:
			return EnumAction.none;
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if ((player.canEat(false) && getItemUseAction(stack) == EnumAction.eat) || getItemUseAction(stack) == EnumAction.drink)
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
		case BOTTLE_MS:
			return EnumRarity.rare;
		default:
			return EnumRarity.common;
		}
	}
	
	public static void doMoonshineStuff(EntityPlayer player) {
		if (!player.worldObj.isRemote) {
			Random rand = player.getRNG();
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, rand.nextInt(200) + 500, rand.nextInt(3), true));
			player.attackEntityFrom(DamageSource.magic, 1.0F + rand.nextFloat() * 8);
			List<String> effectMsg = new ArrayList<>();
			int eff = 1 + rand.nextInt(1023);
			if ((eff & 0b1) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_WITHER));
				player.addPotionEffect(new PotionEffect(Potion.wither.id, rand.nextInt(300) + 100, rand.nextInt(3), true));
			}
			if ((eff & 0b10) != 0) { 
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_REGEN));
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, rand.nextInt(450) + 50, rand.nextInt(2), true));
			}
			if ((eff & 0b100) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_FIRE));
				int burnTime = rand.nextInt(40) + 5;
				player.setFire(burnTime == 44 ? Integer.MAX_VALUE : burnTime);
			}
			if ((eff & 0b1000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_POTION));
				player.addPotionEffect(new PotionEffect(rand.nextInt(24), rand.nextInt(100) + 20, rand.nextInt(12)));
			}
			if ((eff & 0b10000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_GUARDIAN));
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 5, 5, true));
				List<ChunkPosition> spawnPositions = new ArrayList<>();
				int xCoord = player.getPlayerCoordinates().posX, yCoord = player.getPlayerCoordinates().posY, zCoord = player.getPlayerCoordinates().posZ;
				int xOrig = xCoord - 8, yOrig = yCoord - 8, zOrig = zCoord - 8;
				int xTar = xCoord + 8, yTar = yCoord + 8, zTar = zCoord + 8;
				for (int x = xOrig; x <= xTar; x++) {
					for (int y = yOrig; y <= yTar; y++) {
						for (int z = zOrig; z <= zTar; z++) {
							if (player.worldObj.isAirBlock(x, y, z) && player.worldObj.isAirBlock(x, y + 1, z))
								spawnPositions.add(new ChunkPosition(x, y, z));
						}
					}
				}
				int mobAmt = Math.min(rand.nextInt(8) + 1, spawnPositions.size());
				List<Integer> usedPositions = new ArrayList<>();
				for (int i = 0; i < mobAmt; i++) {
					int posIndex = -1;
					while (posIndex < 0 || usedPositions.contains(Integer.valueOf(posIndex)))
						posIndex = rand.nextInt(spawnPositions.size());
					ChunkPosition pos = spawnPositions.get(posIndex);
					EntityBlaze blaze = new EntityBlaze(player.worldObj);
					blaze.setPosition(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
					player.worldObj.spawnEntityInWorld(blaze);
					usedPositions.add(Integer.valueOf(posIndex));
				}
			}
			if ((eff & 0b100000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_DROPINV));
				float f3 = 0.18F;
				for (int i = 0; i < player.inventory.mainInventory.length; i++) {
					if (player.inventory.mainInventory[i] != null) {
						float f = rand.nextFloat() * 0.8F + 0.1F;
						float f1 = rand.nextFloat() * 0.8F + 0.1F;
						float f2 = rand.nextFloat() * 0.8F + 0.1F;
						EntityItem ent = new EntityItem(player.worldObj, player.posX + f, player.posY + f1, player.posZ + f2, player.inventory.mainInventory[i].copy());
						ent.motionX = (double)((float)rand.nextGaussian() * f3);
						ent.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
						ent.motionZ = (double)((float)rand.nextGaussian() * f3);
						player.worldObj.spawnEntityInWorld(ent);
						player.inventory.mainInventory[i] = null;
					}
				}
				for (int i = 0; i < player.inventory.armorInventory.length; i++) {
					if (player.inventory.armorInventory[i] != null) {
						float f = rand.nextFloat() * 0.8F + 0.1F;
						float f1 = rand.nextFloat() * 0.8F + 0.1F;
						float f2 = rand.nextFloat() * 0.8F + 0.1F;
						EntityItem ent = new EntityItem(player.worldObj, player.posX + f, player.posY + f1, player.posZ + f2, player.inventory.armorInventory[i].copy());
						ent.motionX = (double)((float)rand.nextGaussian() * f3);
						ent.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
						ent.motionZ = (double)((float)rand.nextGaussian() * f3);
						player.worldObj.spawnEntityInWorld(ent);
						player.inventory.armorInventory[i] = null;
					}
				}
			}
			if ((eff & 0b1000000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_SKYJUMP));
				player.motionY = rand.nextFloat() * 20F;
				player.motionX = rand.nextFloat() * 12F;
				player.motionZ = rand.nextFloat() * 12F;
			}
			if ((eff & 0b10000000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_XP));
				int xpAmt = 50 + rand.nextInt(75);
				for (int i = 0; i < xpAmt; i++) {
					double xpX = (player.posX - 0.5) + (rand.nextDouble() * 0.5);
					double xpY = player.posY + (rand.nextDouble() * 2);
					double xpZ = (player.posZ - 0.5) + (rand.nextDouble() * 0.5);
					EntityXPOrb xp = new EntityXPOrb(player.worldObj, xpX, xpY, xpZ, 1);
					player.worldObj.spawnEntityInWorld(xp);
				}
			}
			if ((eff & 0b100000000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_ITEMRAIN));
				player.worldObj.createExplosion(player, player.posX, player.posY + 1, player.posZ, 0.0F, false);
				ChestGenHooks gen = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
				int itemAmt = 5 + rand.nextInt(12);
				for (int i = 0; i < itemAmt; i++) {
					float f = rand.nextFloat() * 0.8F + 0.1F;
					float f1 = rand.nextFloat() * 0.8F + 0.1F;
					float f2 = rand.nextFloat() * 0.8F + 0.1F;
					EntityItem ent = new EntityItem(player.worldObj, player.posX + f, player.posY + f1, player.posZ + f2, gen.getOneItem(rand));
					float f3 = 0.22F;
					ent.motionX = (double)((float)rand.nextGaussian() * f3);
					ent.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
					ent.motionZ = (double)((float)rand.nextGaussian() * f3);
					ent.delayBeforeCanPickup = 32;
					player.worldObj.spawnEntityInWorld(ent);
				}
			}
			if ((eff & 0b1000000000) != 0) {
				effectMsg.add(StatCollector.translateToLocal(LangConstants.MSEFF_CORRUPT));
				int xCoord = player.getPlayerCoordinates().posX, yCoord = player.getPlayerCoordinates().posY, zCoord = player.getPlayerCoordinates().posZ;
				int xOrig = xCoord - 3, yOrig = yCoord + 3, zOrig = zCoord - 3;
				int xTar = xCoord + 3, yTar = yCoord - 3, zTar = zCoord + 3;
				for (int x = xOrig; x <= xTar; x++) {
					for (int y = yOrig; y >= yTar; y--) {
						for (int z = zOrig; z <= zTar; z++) {
							if (Math.hypot(Math.hypot(xCoord - x, zCoord - z), yCoord - y) <= 3) {
								if (player.worldObj.getBlock(x, y, z).equals(Blocks.deadbush) || player.worldObj.getBlock(x, y, z).equals(Blocks.tallgrass)
										|| player.worldObj.getBlock(x, y, z).equals(Blocks.wheat) || player.worldObj.getBlock(x, y, z).equals(Blocks.carrots)
										|| player.worldObj.getBlock(x, y, z).equals(Blocks.potatoes)) {
									player.worldObj.setBlock(x, y, z, Blocks.nether_wart);
									player.worldObj.setBlock(x, y - 1, z, Blocks.soul_sand);
								}
								else if (player.worldObj.getBlock(x, y, z).equals(Blocks.dirt) || player.worldObj.getBlock(x, y, z).equals(Blocks.grass))
									player.worldObj.setBlock(x, y, z, Blocks.gravel);
								else if (player.worldObj.getBlock(x, y, z).equals(Blocks.stone) || player.worldObj.getBlock(x, y, z).equals(Blocks.cobblestone))
									player.worldObj.setBlock(x, y, z, Blocks.netherrack);
								else if (player.worldObj.getBlock(x, y, z).equals(Blocks.water))
									player.worldObj.setBlock(x, y, z, Blocks.lava);
								else if (player.worldObj.getBlock(x, y, z).equals(Blocks.flowing_water))
									player.worldObj.setBlock(x, y, z, Blocks.flowing_lava);
							}
						}
					}
				}
			}
			for (String msg : effectMsg) {
				ChatComponentText chatMsg = new ChatComponentText(msg);
				chatMsg.setChatStyle(EFFECT_STYLE);
				player.addChatMessage(chatMsg);
			}
		}
	}

}
