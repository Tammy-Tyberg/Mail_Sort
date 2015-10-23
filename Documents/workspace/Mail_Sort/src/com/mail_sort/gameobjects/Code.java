package com.mail_sort.gameobjects;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Color;


public class Code {
	
/////////////////////////////////////////////////////////Member Variables////////////////////////////////////////////////////	
	
	private Color color; //Color object called color
	List colorOptions = new ArrayList(); //array list called color options
	
	
////////////////////////////////////////////////////////Member Methods///////////////////////////////////////////////////////
	


	//constructor; receives no parameters but makes two method calls
	 public Code(){
		
		createCodeList();//call to create code list
		randomizeCode();//call to randomize colors
	}
	
	
	//creates code list is a method that adds colors to the color option list, 
	//as the game grows more colors can be added to the list
	void createCodeList(){
		
		colorOptions.add(Color.PURPLE); //adding purple
		colorOptions.add(Color.TEAL); //adding teal
		colorOptions.add(Color.YELLOW);//adding yellow
	}


	//randomizes the color options and sets the color in the code object to the first element of the newly shuffled list
	public void randomizeCode(){
		
		Collections.shuffle(colorOptions); //randomize order of color options
		
		setColor((Color) colorOptions.get(0)); //set color to whatever the color is in the first place of the list after the shuffle
		
	}


////////////////////////////////////////////////Getter and Setters///////////////////////////////////////////////////////////
	
	//get color
	private Color getColor() {
		return color;
	}


	//set color
	private void setColor(Color color) {
		this.color = color;
	}
	
	public List getColorOptions() {
		return colorOptions;
	}


	public void setColorOptions(List colorOptions) {
		this.colorOptions = colorOptions;
	}

	
	
	
}
