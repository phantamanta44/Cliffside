package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.block.BlockGlowstoneNode.NodeType;
import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenAcceptor;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenProvider;
import io.github.phantamanta44.cliffside.tile.base.TileMod;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileGlowstoneNode extends TileMod implements ILumenAcceptor, ILumenProvider, IDebuggable {

	private int bufferSize, packetSize;
	private float energyLoss = 0F;
	private int lumenBuffer = 0;
	private int color;
	private ILumenProvider lastProvision = null;
	
	public TileGlowstoneNode(NodeType type, int nodeColor) {
		super();
		switch (type) {
		case BASIC:
			bufferSize = 4;
			packetSize = 4;
			energyLoss = 0.15F;
			break;
		case DEEP:
			bufferSize = 64;
			packetSize = 16;
			energyLoss = 0.01F;
			break;
		case FAST:
			bufferSize = 4;
			packetSize = 4;
			energyLoss = 0.01F;
			break;
		case NORMAL:
			bufferSize = 8;
			packetSize = 8;
			energyLoss = 0.01F;
			break;
		}
		color = nodeColor;
	}
	
	public TileGlowstoneNode() {
		super();
		bufferSize = 0;
		packetSize = 0;
		color = 0;
	}
	
	@Override
	public int getStoredEnergy() {
		return lumenBuffer;
	}

	@Override
	public boolean canProvideEnergy(int amount, ILumenAcceptor destination) {
		return lumenBuffer >= amount;
	}

	@Override
	public void acceptEnergy(int amount, ILumenProvider source) {
		lumenBuffer = Math.min(lumenBuffer + amount, bufferSize);
		lastProvision = source;
	}

	@Override
	public boolean canAcceptEnergy(int amount, ILumenProvider source) {
		return lumenBuffer < bufferSize;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			lumenBuffer = Math.max(0, Math.min(lumenBuffer, bufferSize));
			
			if (lumenBuffer >= packetSize) {
				Set<ILumenAcceptor> acceptors = new HashSet<>();
				for (int i = 0; i < 4 && acceptors.isEmpty(); i++) {
					int xOrig = xCoord - i, yOrig = yCoord - i, zOrig = zCoord - i;
					int xTar = xCoord + i, yTar = yCoord + i, zTar = zCoord + i;
					for (int x = xOrig; x <= xTar; x++) {
						for (int y = yOrig; y <= yTar; y++) {
							for (int z = zOrig; z <= zTar; z++) {
								TileEntity te;
								if ((te = worldObj.getTileEntity(x, y, z)) != null && te != this) {
									if (te instanceof ILumenAcceptor && ((ILumenAcceptor)te).canAcceptEnergy(packetSize, this) && !te.equals(lastProvision)) {
										if (te instanceof TileGlowstoneNode) {
											TileGlowstoneNode node = (TileGlowstoneNode)te;
											if (node.color == this.color || node.color == 0 || color == 0)
												acceptors.add(node);
										}
										else
											acceptors.add((ILumenAcceptor)te);
									}
								}
							}
						}
					}
				}
				if (!acceptors.isEmpty()) {
					int split = (int)Math.floor(((float)packetSize * (1.0F - energyLoss)) / (float)acceptors.size());
					for (ILumenAcceptor acceptor : acceptors)
						acceptor.acceptEnergy(split, this);
					lumenBuffer -= packetSize;
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lumenBuffer = nbt.getShort("LumenBuffer");
		bufferSize = nbt.getShort("BufferSize");
		packetSize = nbt.getShort("PacketSize");
		color = nbt.getByte("Color");
		energyLoss = nbt.getFloat("EnergyLoss");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("LumenBuffer", (short)lumenBuffer);
		nbt.setShort("BufferSize", (short)bufferSize);
		nbt.setShort("PacketSize", (short)packetSize);
		nbt.setByte("Color", (byte)color);
		nbt.setFloat("EnergyLoss", energyLoss);
	}

	@Override
	public int getMaximumEnergy() {
		return bufferSize;
	}

	@Override
	public void provideEnergy(int amount, ILumenAcceptor destination) {
		destination.acceptEnergy(amount, this);
	}
	
	@Override
	public void onDebug(EntityPlayer player) {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields) {
			try {
				player.addChatMessage(new ChatComponentText(f.getName() + ": " + f.get(this).toString()));
			} catch (Throwable th) { }
		}
	}

}
