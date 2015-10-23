package com.mail_sort.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mail_sort.game.Constants;

public class Mail extends GameObject implements Updatable{
	
///////////////////////////////////////////////////////////Member Variables////////////////////////////////////////////////////	
	
	private Color code; //color object called code so can check against mail box code for collision detection
	private float rotationalVel; //for when next version of game mail comes flying out
	private Vector2 dirAndVel; //ditto from above
	private int screenW; //screen width
	private int screenH = Gdx.graphics.getHeight(); //screen height
	int number; 
	boolean canMove; //boolean mail move
	private Vector2 velocity; //velocity vector for mail
	private final float MIN_VELOCITY = 20; //min velocity constant
	private Vector2 direction; //direction vector for direction moving in
	private Vector2 targetDirection; //vector for direction user moves the mail in, continue along that path




////////////////////////////////////////////////////Member Methods/////////////////////////////////////////////////////////////////
	
	//constructor that receives four parameters; texture, color, two integers
	public  Mail(Texture tex, Color key, int randW, int counter){
		
		//velocity =  new Vector2(0, Constants.SPEED); //initialize velocity vector
		canMove = false; 
		code= key; //code receives color that was sent in by key
		number = counter; //number receives counter variable 
		screenW = Gdx.graphics.getWidth(); //set screen Width
		screenH = Gdx.graphics.getHeight();//set screen height
		sprite = new Sprite(tex); //sprite initialized with tex passed into constructor
		//sprite.setOrigin(tex.getWidth()/2, tex.getHeight()/2);
		sprite.setPosition(randW, 75);//set position on same Y axis but random width, with variable passed into constructor
		direction = new Vector2(0, -1);//set direction vector
		targetDirection = new Vector2(0, -1);//set target direction
		velocity =  new Vector2(0, MIN_VELOCITY); //initialize velocity vector to 0 and minimum vel
		//dirAndVel = new Vector2(dir.x, -dir.y);
		//dirAndVel.scl(VELOCITY); //same as dirandVel *= velocity
		//sprite.rotate(dirAndVel.angle() + 90);
		//Gdx.input.setInputProcessor(this);
		setIsDrawable(true);	
		
	}
	
	
	


	@Override
	public void update(float deltaTime) {

	///////!!!!!!!!!!!!!!!!!!!!!/FiX the vector calculations so moves smoothly and increases speed in that direction!!!!!!!!!!!!!!!!!!!!!/////////////////	
		//maybe use face method
		
		//get theta by dot product of vectors  (targetDirection and direction vectors) over targetDirection normalized
		double theta = Math.acos(targetDirection.dot(direction)/targetDirection.len());  
		
		//convert to degrees (maybe it's in radians?????)
		theta = Math.toDegrees(theta);
		
		//so if cross product of direction and targetDirection is greater than 0  than multiply theta by -1 so opposite
		if (direction.crs(targetDirection) > 0)
			theta *= -1;
		
		//then rotate the sprite around theta times deltaT to slow to game time
	    sprite.rotate((float)theta * deltaTime); 
	    
		direction.rotate(-(float)theta * deltaTime);// why negative -- i think because of screen coordinates vs. sprite coordinate system
	
		//if normlaized vel vector is greater than min vel scale it by the differ of 1 and deltaT
		if(velocity.len() > MIN_VELOCITY){
			velocity.scl(1 - deltaTime);
		}
		
		//translate sprite using velocity and delta time
		sprite.translate( velocity.x * deltaTime, velocity.y * deltaTime);
	}

	//getter for velocity
	public Vector2 getVelocity() {
		return velocity;
	}

	//setter for velocity
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}




//when user puts the mouse on mail
	public void mouseOnMail(float deltaTime, int x, int y){
		//sprite.setPosition(x- sprite.getWidth()/2, Gdx.graphics.getHeight() - y- sprite.getHeight()/2);
		sprite.setPosition(x, y);
		//sprite.setPosition(x, Gdx.graphics.getHeight() - y);
		
		//Mail.sprite.setPosition(Gdx.input.getX() - sprite.getWidth()/2,
	               // Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight()/2);
		
	}


//getter for number
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}




//getter for can move
	public boolean isCanMove() {
		return canMove;
	}




//setter for can move
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}



//face to change target direction vector
	public void face(Vector2 targetPos){
		targetDirection = targetPos;
	}

	
//getter for code
	public Color getCode() {
		return code;
	}




//setter for code
	public void setCode(Color code) {
		this.code = code;
	}




///////////////////////////////////////////////////OLD CODE here as safety net//////////////////////////////////////////////////////////

/**	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		//canMove = true;
		
//BUG: need to perform a check to make sure this instance can move otherwise anytime click down will move first instance
//??????????????//MAYBE LOOK AT SHIP AND MISSILE IF DISCREPENCY BETWEEN MOUSE INPUT/CLICK AND SPRITE COORDINATES?????????????????
	//screen coordiantes from bottom left (0,0)
	
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		System.out.println(" x: " + x + " y: " + y);
		
		
		//i think there is a problem with getting bounds of the letter maybe similar error to grid class blocking??
	/**	if( ( (x - sprite.getWidth()/2 >= ((int)sprite.getX()) ) && (x-sprite.getWidth()/2 <= ((int) (sprite.getX() + sprite.getWidth() ) )) )  && 
				(( Gdx.graphics.getHeight() - y- sprite.getHeight()/2 >= ( (int)sprite.getY() ))
				&& ( Gdx.graphics.getHeight() - y- sprite.getHeight()/2 <= ( (int) (sprite.getY() + sprite.getHeight() ) ) ) )){
		
		
		if( (x - sprite.getWidth()/2 > sprite.getX() && x- sprite.getWidth()/2 < sprite.getX() + sprite.getWidth())  && 
				(Gdx.graphics.getHeight() - y- sprite.getHeight()/2 < (sprite.getY() + sprite.getHeight())  && Gdx.graphics.getHeight() - y- sprite.getHeight()/2 > sprite.getY() ) ) {
		
		
																				canMove = true;
																			}else{
																				canMove = false;
																			}
		
		if(canMove){
			return true;
		}else{
			return false;
		}
		

		
	}


*/





}
