package io.github.phantamanta44.cliffside.constant;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public final class IconHelper {

		public static IIcon forName(IIconRegister registry, String name) {
			return registry.registerIcon(GlobalConstants.MOD_PREF + name);
		}

		public static IIcon forName(IIconRegister registry, String name, String dregistry) {
			return registry.registerIcon(GlobalConstants.MOD_PREF + dregistry + "/" + name);
		}

		@Deprecated
		public static IIcon forBlock(IIconRegister registry, Block block) {
			return forName(registry, block.getUnlocalizedName().replaceAll("tile\\.", ""));
		}

		@Deprecated
		public static IIcon forBlock(IIconRegister registry, Block block, int meta) {
			return forBlock(registry, block, Integer.toString(meta));
		}

		@Deprecated
		public static IIcon forBlock(IIconRegister registry, Block block, int meta, String dregistry) {
			return forBlock(registry, block, Integer.toString(meta), dregistry);
		}

		@Deprecated
		public static IIcon forBlock(IIconRegister registry, Block block, String s) {
			return forName(registry, block.getUnlocalizedName().replaceAll("tile\\.", "") + s);
		}
		
		@Deprecated
		public static IIcon forBlock(IIconRegister registry, Block block, String s, String dregistry) {
			return forName(registry, block.getUnlocalizedName().replaceAll("tile\\.", "") + s, dregistry);
		}

		public static IIcon forItem(IIconRegister registry, Item item) {
			return forName(registry, item.getUnlocalizedName().replaceAll("item\\.", ""));
		}

		public static IIcon forItem(IIconRegister registry, Item item, int i) {
			return forItem(registry, item, Integer.toString(i));
		}

		public static IIcon forItem(IIconRegister registry, Item item, String s) {
			return forName(registry, item.getUnlocalizedName().replaceAll("item\\.", "") + s);
		}

	}