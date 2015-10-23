package com.mail_sort.game;

import java.awt.Font;

import com.mail_sort.gameobjects.GameObject;
import com.mail_sort.gameobjects.Mail;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Renderer implements InputProcessor {
	
///////////////////////////////////////////////////////Member Variables///////////////////////////////////////////////////////////
	
	float framesCounter = 0; //float to count frames
	float secondsCounter = 0;//float to count second
	float minutesCounter = 0;//float to count minutes
	private SpriteBatch spriteBatch; //sprite batch
	private SpriteBatch endSP;//second sprite batch
	Controller control;//Controller object
	BitmapFont font;//font object
	private GameObject currentSprite; //game object
	Texture bg1, bg2; //two tex objects for moving background
	float bg1Xpos, bg2Xpos; //floats for x pos of backgrounds
	int screenH= Gdx.graphics.getHeight(); //screen height
	int screenW = Gdx.graphics.getWidth();//screen width
	float totalTime = 0; //float for total time
	private float minutes;//float minutes
	private float seconds;//float seconds
	float deltaT; //float for delta time
	private BitmapFont time; //font object for time
	FPSLogger fp; //
	private float frames; //float for frames
	private boolean gameOver;//boolean for game over
	private float timeUp;//boolean for time up
	private BitmapFont end; //font for end
	
	
/////////////////////////////////////////////////////////////////Member Methods////////////////////////////////////////////////////////////////	

	//constructor receives one parameter; a Controller object 
	public Renderer(Controller c) {
		
		control = c;//assign control to c - passed in controller object
		bg1 = new Texture(Gdx.files.internal("Assets/Msbg2.jpg"));//bg1 instantiated with texture from assets
		//System.out.println(bg1.getWidth()); //debugging statement to see width 
		bg2 = new Texture(Gdx.files.internal("Assets/Msbg2.jpg"));//bg2 instantiated with texture from assets
		bg1Xpos = 0; //x pos of bg1 
		bg2Xpos = bg1.getWidth();//x pos of bg 2 is width value of bg1 
		fp = new FPSLogger(); //instantiate FSPLogger
		gameOver = false; //gameOver is false 
		timeUp = 1; //1 minutes to play the game
		spriteBatch = new SpriteBatch(); //instantiate sprite batch
		endSP = new SpriteBatch();//instantiate sprite batch
		end = new BitmapFont(); //instantiate font with BitmapFont
		font = new BitmapFont();//instantiate font with BitmapFont
		time = new BitmapFont();//instantiate font with BitmapFont
		
	}

	
//render method
	public void render() {
		
		//if time up is equal to minutes then game is over or if outs equals max lives -there is no more outs
		if((timeUp == minutes) || (control.getOuts() == Constants.MAXLIVES) ){ 
			
				gameOver = true;//game is over
				gameOver();//game over
			
		   }
		
		//if(control.getOuts() == Constants.MAXLIVES){
			
			//	gameOver = true;
		//}
		
	//if the game is not over so boolean is false
	if(!gameOver){
		
				deltaT = Gdx.graphics.getDeltaTime();//set delta time
				spriteBatch.begin(); //sprite batch begin
				//renderBackground(); //render background
				fp.log(); //frames log
				font.setColor(Color.YELLOW);//set font color to yellow
				font.draw(spriteBatch, "Tammy's Mail Sort Game", 50, (Gdx.graphics.getHeight()- 40)); //mail sort game title
				timer(deltaT); //timer method called with delta time passed in
				time.setColor(Color.BLUE); //time font set to blue
				time.draw(spriteBatch, "Minutes: " + minutes + " Seconds:" + seconds + "Missed: " + control.getOuts(), 20, 20); //timer written and lives counter
			
				for(GameObject gObj : control.getDrawableObjects()){ //draw gameobjects from drawable objects
					gObj.sprite.draw(spriteBatch);
		       }
	
	//if user pressed down with mouse
	if(touchDown(0, 0, 0, 0)){
		
			int x = Gdx.input.getX(); // x is input x coordinate
			int y = Gdx.input.getY();//y is input y coordinate
			
			//System.out.println(" x: " + x + " y: " + y);//debugging print statement
			
			//iterate through drawable objects
			for(int i = 0; i < control.drawableObjects.size(); i++) {
				GameObject gObg = control.drawableObjects.get(i);
				
				//check if instance of mail
				if(gObg instanceof Mail){
					
						//if x input falls within width bounds of mail
						if(x > gObg.sprite.getX() && x < gObg.sprite.getX() + gObg.sprite.getWidth()){
							currentSprite = gObg; //current sprite equals that gObg object
							currentSprite.setCanMove(true); //can move is true
							Gdx.input.setInputProcessor(this); //put the input processor on it
							
							 //if the left button is pressed
							 if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
								 
								 //!!!!!!!!the mail should face that direction -- need to coordinate this with Mail update method
									((Mail) gObg).face(new Vector2(Gdx.input.getX()-gObg.sprite.getX(), -(screenH - Gdx.input
											.getY()- gObg.sprite.getY())));
							}
	
						}//end of if x in between mail width
						
					}//end of if gObg instance of mail
			
				}//end of forloop
	
			}//end of if touchdown
	
	//if touch up() ???????
	
		spriteBatch.end();//end spritebatch
	
	
		}//end of if game over
	
	
	
	}

	
//method to create timer receives one parameter; delta time
	public void timer(float deltaT){
	
		totalTime = totalTime + deltaT; //total time equals total time plus delta time
		frames = ++framesCounter; //frames is framecounter ,incremented first since entered the method so another frame passed
		
		//if equals 60 frames
		if(framesCounter == 60){
			
			seconds = ++secondsCounter;//seconds increases and gets value of seconds counter increased
			framesCounter = 0;//frame counter resets to 0
		}
		
		if(secondsCounter == 60){ //if second counter equals 60 - that means entered previous if 60 times
			
			minutes = ++minutesCounter; //minutes gets value of minutes counter which is first incremented by  1
			secondsCounter = 0; //seconds counter restarts
		}
		
		
	}
	
	//game over user either maxxed out on lives or time ran out
	public void gameOver(){
		
		//GameOver
		endSP.begin(); //start end sprite batch
		
		end.setColor(Color.YELLOW); //set color to yellow
		end.draw(endSP, "GAME OVER!", (Gdx.graphics.getWidth()/2), (Gdx.graphics.getHeight()/2)); //game over is displayed
		
		endSP.end(); //end sprite batch
	}
	
	
	//method to render the background
	public void renderBackground(){
		
		//0, 0, width to half of screen, height whole screen
		spriteBatch.draw(bg1, bg1Xpos, 0, screenW, screenH);//draw first background
		spriteBatch.draw(bg2, bg2Xpos, 0, screenW, screenH);//second background
		
		if(bg2Xpos == screenW/2){ //if bg2 x pos equals half the scren width
				
			bg1Xpos = bg2.getWidth(); //set bg1 x pos to bg2 width value so it moves smoothly
		}
		
		
		bg1Xpos -= 0.3; //decrease x pos by 0.3 so moves to left
		bg2Xpos -= 0.3;//decrease x pos by 0.3 so moves to left
		
	}
	
	
 ////////////////////////////////methods for implementing input processor/////////////////////////////////////////////////////////
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

		return true;
	}



	//user holds down mouse and moves
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		
		//subtract because of difference between screen coordinates and sprite coordinates
			currentSprite.sprite.setPosition(Gdx.input.getX() - currentSprite.sprite.getWidth()/2, Gdx.graphics.getHeight() -Gdx.input.getY());
			
		return true;
	}


//this kind of defeats the purpose since they all move anyway - delete after testing
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		currentSprite.setCanMove(false);
		return true;
	}

}
