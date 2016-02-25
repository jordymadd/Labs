package exercise1;

import exercise1.Car.Speed;
import greenfoot.GreenfootImage;

public class RedCar extends WrapWorld {
	
	protected static int redIntsPassed = 0; 

	public RedCar(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[3]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}

	
	public void turnDetermine(){
		//I think if the turnDetermine() is blank then that should let the car just go straight.. Yup I was right :) 

	}
	
	@Override
	public void inInterSection(Intersection intersection) {
		
		
		if (intersection.isGreen(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isYellow(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isRed(direction)){
			speed =Speed.REGULAR; 
		}
	redIntsPassed++; 
	}
	
	@Override
	public String toString() {
		String output = "" + redIntsPassed + "\t\t\t\t"; 
		return output; 
	}

}
