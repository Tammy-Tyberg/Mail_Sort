package com.mail_sort.desktop;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mail_sort.game.Game;



public class MailSortLauncher extends ApplicationAdapter {
	
	//main program
	public static void main (String[] arg) {
		
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration(); //config is LwglAppConfig object
		
		config.title = "MailSort"; //title of frame is Mail Sort
		config.width = 800; //width of frame
		config.height = 480; //height of frame
		
		//this line passes in all the other packages/classes just from game(), config has simple specs of screen
		new LwjglApplication(new Game(), config); //new Lwjgl application pass in game and config
	}
}

