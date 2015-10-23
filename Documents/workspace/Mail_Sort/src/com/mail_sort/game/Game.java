package com.mail_sort.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class Game implements ApplicationListener {
	
//////////////////////////////////////////////////Member Methods//////////////////////////////////////////////////////////////	
	
	private Controller control; //controller object called control
	private Renderer render; //renderer object called render
	
	@Override
	public void create () { //create method
		
		control = new Controller(); //instantiate control 
		render = new Renderer(control); //instantiate render object by passing in control to the Renderer constructor
	}
	
	//render method- repeats througout the game
	@Override
	public void render () {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		control.update(); // Process inputs and update game world.
		
		render.render(); //calls render's render method
	}
	
	//dispose
	@Override
	public void dispose(){
		control.dispose(); //calls controller's dispose method
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
}

