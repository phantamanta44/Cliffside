package io.github.phantamanta44.cliffside.handler;

import io.github.phantamanta44.cliffside.block.BlockAlchemical;
import io.github.phantamanta44.cliffside.block.BlockCompressed;
import io.github.phantamanta44.cliffside.block.CSBlocks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.world.ExplosionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExplosiveEnergizationHandler {

	@SubscribeEvent
	public void onExplode(ExplosionEvent.Detonate event) {
		List<ChunkPosition> blocks = event.getAffectedBlocks();
		Deque<ChunkPosition> gsBlocks = new ArrayDeque<>();
		Deque<ChunkPosition> rsBlocks = new ArrayDeque<>();
		World world = event.world;
		for (ChunkPosition block : blocks) {
			int x = block.chunkPosX, y = block.chunkPosY, z = block.chunkPosZ;
			if (world.getBlock(x, y, z) instanceof BlockCompressed) {
				int meta = world.getBlockMetadata(x, y, z);
				if (meta == BlockCompressed.COMP_GS)
					gsBlocks.offer(block);
				else if (meta == BlockCompressed.COMP_RS)
					rsBlocks.offer(block);
			}
		}
		ChunkPosition gs;
		ChunkPosition rs;
		while ((gs = gsBlocks.poll()) != null && (rs = rsBlocks.poll()) != null) {
			int x = gs.chunkPosX, y = gs.chunkPosY, z = gs.chunkPosZ;
			if (world.rand.nextInt(10) <= 3)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(CSBlocks.alchemBlk, 1, BlockAlchemical.ALCHEM_GS)));
			blocks.remove(gs);
			blocks.remove(rs);
			world.setBlock(x, y, z, Blocks.air);
			world.setBlock(rs.chunkPosX, rs.chunkPosY, rs.chunkPosZ, Blocks.air);
		}
	}
	
}