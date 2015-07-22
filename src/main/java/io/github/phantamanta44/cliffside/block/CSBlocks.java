package io.github.phantamanta44.cliffside.block;

import io.github.phantamanta44.cliffside.block.BlockGlowstoneNode.NodeType;
import net.minecraft.block.Block;

public class CSBlocks {

	public static Block compBlk;
	public static Block alchemBlk;
	public static Block gsNodeBasic;
	public static Block gsNode;
	public static Block gsNodeDeep;
	public static Block gsNodeFast;
	public static Block machine;
	public static Block lumenLamp;
	public static Block ore;
	public static Block lumenSource;
	
	public static void init() {
		compBlk = new BlockCompressed();
		alchemBlk = new BlockAlchemical();
		gsNodeBasic = new BlockGlowstoneNode(NodeType.BASIC);
		gsNode = new BlockGlowstoneNode(NodeType.NORMAL);
		gsNodeDeep = new BlockGlowstoneNode(NodeType.DEEP);
		gsNodeFast = new BlockGlowstoneNode(NodeType.FAST);
		machine = new BlockMachine();
		lumenLamp = new BlockIlluminationLamp();
		ore = new BlockOre();
		lumenSource = new BlockCreativeLumenSource();
	}
	
}
