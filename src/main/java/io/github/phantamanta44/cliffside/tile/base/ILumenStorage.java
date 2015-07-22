package io.github.phantamanta44.cliffside.tile.base;


public interface ILumenStorage {
	
	public int getStoredEnergy();
	
	public int getMaximumEnergy();

	public static interface ILumenAcceptor extends ILumenStorage {
	
		public void acceptEnergy(int amount, ILumenProvider source);
		
		public boolean canAcceptEnergy(int amount, ILumenProvider source);
		
	}
	
	public static interface ILumenProvider extends ILumenStorage {
		
		public void provideEnergy(int amount, ILumenAcceptor destination);
		
		public boolean canProvideEnergy(int amount, ILumenAcceptor destination);
		
	}

}