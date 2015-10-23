package com.mail_sort.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class GameObject {
	
/////////////////////////////////////////////////Member Variables///////////////////////////////////////////////////////////	
	
	private boolean drawable; //boolean if object is drawable
	private boolean updatable; //boolean if object is updatable
	public Sprite sprite; //sprite object called sprite for the game object
	public boolean canMove; //boolean can the object move 
	
////////////////////////////////////////////////Member Methods///////////////////////////////////////////////////////////////
	
	//default constructor
	public GameObject(){
	}
	
	//getter for drawable
	public boolean isDrawable(){
		return drawable;
	}
	
	//getter for updatable
	public boolean isUpdatable(){
		return updatable;
	}
	
	//setter for drawable
	public void setIsDrawable(boolean val){
		drawable = val;
	}
	
	//setter for updatable
	public void setIsUpdatable(boolean val){
		updatable = val;
	}

	//setter for canMove
	public void setCanMove(boolean b) {
		// TODO Auto-generated method stub
		canMove = b;
	}
	
}
