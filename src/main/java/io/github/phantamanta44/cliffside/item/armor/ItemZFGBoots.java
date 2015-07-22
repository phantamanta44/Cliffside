package io.github.phantamanta44.cliffside.item.armor;

import io.github.phantamanta44.cliffside.constant.GlobalConstants;
import io.github.phantamanta44.cliffside.constant.ItemConstants;
import io.github.phantamanta44.cliffside.constant.LangConstants;
import io.github.phantamanta44.cliffside.material.CSMaterialTypes;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ISpecialArmor;

public class ItemZFGBoots extends ItemModArmor implements ISpecialArmor {

	public ItemZFGBoots() {
		super(GlobalConstants.ARMOR_BOOTS, CSMaterialTypes.armorCatalytical);
		setUnlocalizedName(ItemConstants.ZFG_BOOTS_NAME);
		armorSetName = "zfgBoots";
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (source.isExplosion())
			return new ArmorProperties(1, damageReduceAmount / 12.5D, armor.getMaxDamage() + 1 - armor.getItemDamage());
		return new ArmorProperties(0, damageReduceAmount / 25.0D, armor.getMaxDamage() + 1 - armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		// NO-OP
	}
	
	/*@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (world.isRemote)
			return;
		if (stack.getItem().equals(CSItems.zfgBoots)) {
			float facing = (450 - ((player.rotationYawHead + 720) % 360)) % 360;
			double zFac = Math.sin(facing * (Math.PI / 180)), xFac = -Math.cos(facing * (Math.PI / 180));
			double xMot = player.motionX, zMot = player.motionZ, len = Math.hypot(xMot, zMot);
			if (len != 0) {
				xMot /= len;
				zMot /= len;
			}
			if (Math.abs(zMot + zFac) <= 0.5 && Math.abs(xMot + xFac) <= 0.5)
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 16));
		}
	} Broken because EntityPlayer.motionX and EntityPlayer.motionY don't work server-side for some stupid reason */
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someRandomArg) {
		info.add(StatCollector.translateToLocal(LangConstants.ZFG_BOOTS_DESC1));
		info.add(StatCollector.translateToLocal(LangConstants.ZFG_BOOTS_DESC2));
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}
	
}
