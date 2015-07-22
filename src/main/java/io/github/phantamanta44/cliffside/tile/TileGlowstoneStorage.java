package io.github.phantamanta44.cliffside.tile;

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
import cpw.mods.fml.common.Optional;

public class TileGlowstoneStorage extends TileMod implements ILumenAcceptor, ILumenProvider, IDebuggable {

	private int lumenBuffer = 0, bufferSize = 0, packetSize = 0;
	private ILumenProvider lastProvision = null;
	
	public TileGlowstoneStorage(int storage, int packet) {
		super();
		bufferSize = storage;
		packetSize = packet;
	}
	
	public TileGlowstoneStorage() {
		super();
		bufferSize = 0;
		packetSize = 0;
	}
	
	@Override
	public int getStoredEnergy() {
		return lumenBuffer;
	}

	@Override
	public boolean canProvideEnergy(int amount, ILumenAcceptor destination) {
		return lumenBuffer >= amount && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
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
			
			if (lumenBuffer >= packetSize && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				Set<ILumenAcceptor> acceptors = new HashSet<>();
				for (int i = 0; i < 4 && acceptors.isEmpty(); i++) {
					int xOrig = xCoord - i, yOrig = yCoord - i, zOrig = zCoord - i;
					int xTar = xCoord + i, yTar = yCoord + i, zTar = zCoord + i;
					for (int x = xOrig; x <= xTar; x++) {
						for (int y = yOrig; y <= yTar; y++) {
							for (int z = zOrig; z <= zTar; z++) {
								TileEntity te;
								if ((te = worldObj.getTileEntity(x, y, z)) != null && te != this) {
									if (te instanceof ILumenAcceptor && ((ILumenAcceptor)te).canAcceptEnergy(packetSize, this) && te != lastProvision)
										acceptors.add((ILumenAcceptor)te);
								}
							}
						}
					}
				}
				if (!acceptors.isEmpty()) {
					int split = (int)((float)packetSize / (float)acceptors.size());
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
		lumenBuffer = nbt.getInteger("LumenBuffer");
		bufferSize = nbt.getInteger("BufferSize");
		packetSize = nbt.getShort("PacketSize");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("LumenBuffer", lumenBuffer);
		nbt.setInteger("BufferSize", bufferSize);
		nbt.setShort("PacketSize", (short)packetSize);
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
