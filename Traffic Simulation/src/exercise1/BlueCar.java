package exercise1;

import greenfoot.GreenfootImage;

public class BlueCar extends Car{
	
	public BlueCar(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[0]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}

	public void turnDetermine(){
		if(isTurningRight){
			turnRight();
			isTurningRight =false; 
		}
		else{
			turnLeft(); 
			isTurningRight =true; 
		}

	}

}
