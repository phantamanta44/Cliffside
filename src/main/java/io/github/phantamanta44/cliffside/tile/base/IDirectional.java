package io.github.phantamanta44.cliffside.tile.base;

public interface IDirectional {

	public int getFrontFace();
	
	public void setFrontFace(int face);
	
	public void rotateClockwise();
	
	public void rotateCounterclockwise();
	
}