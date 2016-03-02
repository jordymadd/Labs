package exercise1;

import exercise1.Car.Speed;
import greenfoot.GreenfootImage;

public class RedCar extends WrapWorld {

	protected static int redIntsPassed = 0; 
	private static int drivingStyle; 

	public RedCar(int drivingStyle){
		this.drivingStyle = drivingStyle; 
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[3]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}


	//	public void turnDetermine(){
	//		//I think if the turnDetermine() is blank then that should let the car just go straight.. Yup I was right :) 
	//
	//	}


	//new turnDetermine -- this method replaces the old method, which the run settings will set the driving style the car uses
	public void turnDetermine(){
		//this will make the car alternate between left and right if driving style == 1
		if(drivingStyle==1){
			if(isTurningRight){
				turnRight();
				isTurningRight =false; 
			}
			else{
				turnLeft(); 
				isTurningRight =true; 
			}
		}
		else if(drivingStyle==2){
			turnRight();
		}
		else if(drivingStyle==3){
			turnLeft();
		}
		else if(drivingStyle==4){
			//left intentionally blank
		}

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
