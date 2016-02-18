package exercise1;

import greenfoot.GreenfootImage;

public class PurpleCar extends Car {
	
	public PurpleCar(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[1]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}

	public void turnDetermine(){
		//this method will determine whether or not to turn or go straight (25% chance left (only), 75% straight)
		int r = rand.nextInt(100);
		if(r >= 75){
			turnLeft();
		}

	}
}
