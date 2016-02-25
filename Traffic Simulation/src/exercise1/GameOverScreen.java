package exercise1;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class GameOverScreen extends Actor{

	String msgTxt = "SIM TEXT";  

	public GameOverScreen(String txt){
		msgTxt = txt;
	}


	 public void addedToWorld(World world)
	    {
	       GreenfootImage image = new GreenfootImage(world.getWidth(), world.getHeight());
	        image.setColor(Color.cyan);
	        image.fill();
	        GreenfootImage txtImg = new GreenfootImage(msgTxt, 12, Color.black, new Color(0, 0, 0, 0));
//	        image.drawImage(txtImg, (image.getWidth() - txtImg.getWidth()) / 2, (image.getHeight() - txtImg.getHeight() / 2));
	        image.drawImage(txtImg, 375, 100);
	        setImage(image);
	       
	    }
	 
	public void act(){boolean removeMe = false;

    if (Greenfoot.isKeyDown("b")) removeMe = true;
    if (Greenfoot.isKeyDown("n")) removeMe = true;
    if (Greenfoot.isKeyDown("m")) removeMe = true;
    if (removeMe) getWorld().removeObject(this);
		
		
	}
		 
}

