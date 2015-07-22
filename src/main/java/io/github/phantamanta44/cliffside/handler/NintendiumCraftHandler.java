package io.github.phantamanta44.cliffside.handler;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.CSBlocks;
import io.github.phantamanta44.cliffside.item.CSItems;
import io.github.phantamanta44.cliffside.item.resource.ItemIngot;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.world.ExplosionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NintendiumCraftHandler {

	@SubscribeEvent
	public void onExplode(ExplosionEvent.Detonate event) {
		Explosion exp = event.explosion;
		List<ChunkPosition> blocks = event.getAffectedBlocks();
		Deque<ChunkPosition> compBlocks = new ArrayDeque<>();
		World world = event.world;
		for (ChunkPosition block : blocks) {
			int x = block.chunkPosX, y = block.chunkPosY, z = block.chunkPosZ;
			if (world.getBlock(x, y, z).equals(CSBlocks.alchemBlk)) {
				int meta = world.getBlockMetadata(x, y, z);
				if (meta == BlockAlchemical.NINT_MIX)
					compBlocks.offer(block);
			}
		}
		while (compBlocks.size() >= 26) {
			for (int i = 0; i < 26; i++) {
				ChunkPosition block = compBlocks.poll();
				world.setBlock(block.chunkPosX, block.chunkPosY, block.chunkPosZ, Blocks.air);
				blocks.remove(block);
			}
			EntityItem ent = new EntityItem(event.world, exp.explosionX, exp.explosionY, exp.explosionZ,
					new ItemStack(CSItems.matIngot, 1, ItemIngot.NINTENDIUM));
			float f3 = 0.05F;
			ent.motionX = (double)((float)world.rand.nextGaussian() * f3);
			ent.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
			ent.motionZ = (double)((float)world.rand.nextGaussian() * f3);
			world.spawnEntityInWorld(ent);
		}
	}
	
}
