package com.mail_sort.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;
import com.mail_sort.gameobjects.Code;
import com.mail_sort.gameobjects.GameObject;
import com.mail_sort.gameobjects.Inbox;
import com.mail_sort.gameobjects.Mail;


public class Controller {

///////////////////////////////////////////////////////////Member Variables///////////////////////////////////////////////////////////
	
	float screenW = Gdx.graphics.getWidth(); //float for screen width
	float screenH = Gdx.graphics.getHeight(); //float for screen height
	
	//List colorOptions = new ArrayList<Color>(); _GETTING RID OF THIS IT"S REDUNDDANT
	
	ArrayList<GameObject> drawableObjects; //list of game objects called drawable objects
	Code colorCode; //code object
	int counter = 1; //counter starts at 1
	boolean removeMail; //boolean to remove mail object
	int frameCounter = 0; //frame counter to keep track of time
	int outs = 0; //outs to count how many times the user misses sorting the mail
	float speed = 10; //float for speed of mail
	
	
	
/////////////////////////////////////////////////////////////////Member Methods///////////////////////////////////////////////	
	public Controller(){
	
		drawableObjects = new ArrayList<GameObject>();  //creates list of game objects
		colorCode = new Code(); //initialize colorCode so shuffles color options and sets color to code object
		//createCodeList();
		initInbox(); //initialize inbox
		initMail(1);  //initialize mail with 1 as parameter
	
	}
	
	//method to initialize the mail and set up all methods pertaining to it
	private void initMail(int num){//Receives one parameter an integer
		
		//System.out.println("Entered initMail"); //decoding statement
		int w = 100; 
		int h = 50;
		
		Random rand = new Random(); //random set up
		
		for(int i = 0; i<num; i++){  //forloop to create mail objects, number created base on int parameter passed in
			
						colorCode.randomizeCode();//randomize colors in coloroptions
						Color key = (Color) colorCode.getColorOptions().get(0); //set key to whatever is now in color options at index 0
						
					//////////to be replaced by graphics pmap is temp to test framework//////////////////////////////////
						//!!!!!!!!!!!replace with graphics!!!!!!!!!!!//////////////////
						Pixmap pmap = new Pixmap(w, h, Format.RGB565); //creating pix map
						pmap.setColor(key); //color of pmap is color from key this is the color of the mail
						
						int W = rand.nextInt((Gdx.graphics.getWidth()/2 - 10) + 1) + 10; //int gets a value by random so not all mail created in same
						//position on the screen
						pmap.drawRectangle(w/2, 0, w/2, h);//draw the rectangle
						
						//create mail object with texture, key code, the int value, and counter to keep track of mail 
						//in same statement add it to the drawableObjects array list of game objects
						drawableObjects.add(new Mail(new Texture(pmap), key, W, counter));
						
						//debugging print statements
						System.out.println("screen width is: " + Gdx.graphics.getWidth());
						System.out.println("Creating letter: " + i + "At position: " + W);
						
						
						counter ++; //counter is incremented so next mail created has a diff id number
		 
		      }
		
		
	}
	
	
		
	//method to init Inbox 
	private void initInbox(){
		//get leghtn of code list
		//create an inbox for every color of the code list
		//add it to drawable objects
		int x = 100; 
		int y = 100;
		int radius = 40;
		float wPlace = 100;
		
		for(int i =0; i< colorCode.getColorOptions().size(); i++){  //forloop to create mailboxes one for every color in the color options list
			
					//!!!!!!!!!!!replace with graphics!!!!!!!!!!!//////////////////
					Pixmap pixmap = new Pixmap(x, y, Format.RGB565); //pmap
					pixmap.setColor((Color) colorCode.getColorOptions().get(i)); //set color
					pixmap.drawCircle(x/2, 40, radius);//draw circle
					pixmap.fillCircle(x/2 ,40, radius);//fill it
					
					//create inbox object with texture, color, and place of where it should be created along x- axis since y-axis the same for all inboxes
					drawableObjects.add(new Inbox(new Texture(pixmap), ((Color)colorCode.getColorOptions().get(i)), wPlace));
					wPlace = wPlace + 200; //increase x axis position by 200 px for next inbox creationg
					//x= x+ 150;
		}
	}
	
	

	//method to update
	public void update() {
	
		frameCounter++; //if enters this method a new frame has passed
		//processKeyboardInput(); //call to process keyboard input
		
		Random rn = new Random();//instantiate random object
		int create = rn.nextInt(4) + 1;//using the random seed, create gets an int to decide how many mail objects to create this frame
		
		if(frameCounter == 180){ //that means three second passed or 180 miliseconds so time to put more mail in game
			
						initMail(create); //call initmail to create more instance of mail with whatever number was given to create
						frameCounter = 0; //set frameCounter to 0
						
						
								if(speed < Constants.MAXSPEED){ //if speed is less than max speed
							
											speed = (float) (speed *1.5); //increase speed by 1.5 times
									}
								
					}
		
		for(int i = 0; i < drawableObjects.size(); i++) {//forloop to iterate through game objects in list
			
			GameObject gObg = drawableObjects.get(i); //get ith element in the list and cast to gObg
			
			if(gObg instanceof Mail){//check if instance of mail class
				
							((Mail)gObg).update(Gdx.graphics.getDeltaTime()); //if so cast gObg to mail and call Mail's update method
				
							for(int j =0; j< drawableObjects.size(); j++){		//begin an inner forloop to iterate through drawable objects again
								
								GameObject inbObj = drawableObjects.get(j); //cast ith element to GameObject but a differ name
					
											if(inbObj instanceof Inbox){ //check if inbObg is instance of Inbox class
												
												//if so now check if inbox obj and mail obj overlap - collision detection
												//cast the current gObg to Mail and access sprite- check if bounding rectangle overlaps 
												//the bounding rectangle of the sprite of the game object casted to Inbox
												if((((Mail)gObg).sprite.getBoundingRectangle().overlaps(((Inbox)inbObj).sprite.getBoundingRectangle()))){  
											
														//if they do, check if their codes are equal - means have the same color
															if(((Mail)gObg).getCode() == ((Inbox)inbObj).code){//compare if same codes
										
																	               removeMail = true; //removeMail is true since we are done this mail
																			       drawableObjects.remove((Mail)gObg);//remove this isntance Mail object from the drawable list
																		
															}else {
																//thier codes don't match but they collided
																if(((Mail)gObg).getCode() != ((Inbox)inbObj).code){
													
																	outs = outs + 1;//outs increases by 1
																	removeMail = true;//remove the mail is true
																	drawableObjects.remove((Mail)gObg);//remove this instance of mail from drawable objects
																}
															}
		
						                           }//end of if both gObg objects overlap
					
				                             }//end of if instance of inbox
			
									}//end of inner forloop
		
		
						}//end of if instance of mail
		
		
		
		          }//end of outter forloop
		
		
		
	}//end of update()

	
	//////////////////////////////////////Old Code for figuring out mouse movement -here as saftey net/////////////////////////////////////////
	private void processKeyboardInput(){
			  //Now check to see if mouse coordinates are within a Mail object
			 //if so call mouseOnMail method to touch drag mail			 
			 //need to change to event driven, may be to slow using polling when mail gets bigger 
			 
		/**	 
			float minx, miny, maxx, maxy;
			 
				for(int i = 0; i < drawableObjects.size(); i++) {
					GameObject gObg = drawableObjects.get(i);
					if(gObg instanceof Mail){
							minx = ((Mail)gObg).sprite.getX();
							miny= ((Mail)gObg).sprite.getX();
							maxx= minx + ((Mail)gObg).sprite.getWidth();
							maxy = miny + ((Mail)gObg).sprite.getHeight();
							
						//if(ship.sprite.getBoundingRectangle().overlaps(((Asteroid)gObg).sprite.getBoundingRectangle()) && !shipCrashed){
							//shipCrashed = true;
			 
								if ((Gdx.input.getX() >= minx && Gdx.input.getX() <= maxx) && (Gdx.input.getY() >= miny && Gdx.input.getY() <= maxy)) {
									
										//((Mail)gObg).mouseOnMail(Gdx.graphics.getDeltaTime(), Gdx.input.getX(), Gdx.input.getY());
									
									((Mail)gObg).sprite.setPosition(Gdx.input.getX()- ((Mail)gObg).sprite.getWidth()/2,
											Gdx.graphics.getHeight() - Gdx.input.getY()- ((Mail)gObg).sprite.getHeight()/2);
												System.out.println("moved mail no:" + ((Mail)gObg).getNumber());
									
									//Need to figure out delta time 
								
									
										}
					}
			
			 
		 }
		         
		}*/
	}


	//getter for drawableObjects
	public ArrayList<GameObject> getDrawableObjects(){
		return drawableObjects;
	}
	
	//getter for outs
	public int getOuts() {
		return outs;
	}
	
	//setter for outs
	public void setOuts(int outs) {
		this.outs = outs;
	}


//dispose of objects like sound when game ends
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
