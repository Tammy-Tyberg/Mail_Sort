package com.mail_sort.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Inbox extends GameObject {

///////////////////////////////////////////////////Member Variables//////////////////////////////////////////////////////////	
	
	public Color code; //color object since each inbox is differ color and needs to match mail so it's the code
	
	//private float rotationalVel; for when next version of game mail comes flying out
	//private Vector2 dirAndVel; ditto from above
	
	private int screenH=Gdx.graphics.getHeight(); //gets screen height
	private int screenW = Gdx.graphics.getWidth(); //gets screen width
	private int h = screenH/2; //screen height in half
	private int inboxY = 330;//what Y coordinate the inboxes should be on
	
////////////////////////////////////////Member Methods//////////////////////////////////////////////////////////////////////	
	
	//constructor receives three parameters; texture, color, and float
	public Inbox(Texture tex, Color key, float W){
		
		code= key; //inbox code is set to color passed in
		sprite = new Sprite(tex); //sprite is set to tex passed in
		//sprite.setOrigin(tex.getWidth()/2, tex.getHeight()/2);

		sprite.setPosition(W, inboxY); //pos is set to passed in W for x but y value is always the same
		setIsDrawable(true);//is drawable
		
	}
	
	
	
	
	
	
	
	
	

}
