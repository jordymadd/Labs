package exercise1;

import exercise1.Car.Speed;
import greenfoot.GreenfootImage;

public class YellowCar extends WrapWorld{

	protected static int yellowIntsPassed = 0; 
	private static int drivingStyle; 

	public YellowCar(int drivingStyle){
		this.drivingStyle = drivingStyle; 
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[2]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}


	//	public void turnDetermine(){
	//		//this method will determine whether or not to turn or go straight (25% chance right only), 75% straight)
	//		int r = rand.nextInt(100);
	//		if(r >= 75){
	//			turnRight();
	//		}
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
		yellowIntsPassed++; 
	}

	@Override
	public String toString() {
		String output = "" + yellowIntsPassed + "\t\t\t\t"; 
		return output; 
	}
}
