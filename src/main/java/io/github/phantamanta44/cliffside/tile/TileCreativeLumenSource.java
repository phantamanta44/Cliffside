package io.github.phantamanta44.cliffside.tile;

import io.github.phantamanta44.cliffside.tile.base.IDebuggable;
import io.github.phantamanta44.cliffside.tile.base.ILumenStorage.ILumenProvider;
import io.github.phantamanta44.cliffside.tile.base.TileMod;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileCreativeLumenSource extends TileMod implements ILumenProvider, IDebuggable {

	private int packetSize = 0;
	
	public TileCreativeLumenSource(int packet) {
		super();
		packetSize = packet;
	}
	
	public TileCreativeLumenSource() {
		super();
		packetSize = 8;
	}
	
	@Override
	public int getStoredEnergy() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean canProvideEnergy(int amount, ILumenAcceptor destination) {
		return true;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			Set<ILumenAcceptor> acceptors = new HashSet<>();
			for (int i = 0; i < 4 && acceptors.isEmpty(); i++) {
				int xOrig = xCoord - i, yOrig = yCoord - i, zOrig = zCoord - i;
				int xTar = xCoord + i, yTar = yCoord + i, zTar = zCoord + i;
				for (int x = xOrig; x <= xTar; x++) {
					for (int y = yOrig; y <= yTar; y++) {
						for (int z = zOrig; z <= zTar; z++) {
							TileEntity te;
							if ((te = worldObj.getTileEntity(x, y, z)) != null && te != this) {
								if (te instanceof ILumenAcceptor && (packetSize != -1 || ((ILumenAcceptor)te).canAcceptEnergy(packetSize, this)))
									acceptors.add((ILumenAcceptor)te);
							}
						}
					}
				}
			}
			if (!acceptors.isEmpty()) {
				if (packetSize == -1) {
					for (ILumenAcceptor acceptor : acceptors)
						acceptor.acceptEnergy(acceptor.getMaximumEnergy() - acceptor.getStoredEnergy(), this);
				}
				else {
					int split = (int)((float)packetSize / (float)acceptors.size());
					for (ILumenAcceptor acceptor : acceptors)
						acceptor.acceptEnergy(split, this);
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		packetSize = nbt.getShort("PacketSize");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("PacketSize", (short)packetSize);
	}

	@Override
	public int getMaximumEnergy() {
		return Integer.MAX_VALUE;
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
