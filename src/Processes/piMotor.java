package Processes;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.*;
import com.pi4j.jni.I2C;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JToggleButton;

public class piMotor {
	
    public piMotor()
    {
    }
    
	// create gpio controller
    final GpioController gpio = GpioFactory.getInstance();
    
    final GpioPinDigitalOutput forwardA = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, PinState.LOW);
    final GpioPinDigitalOutput backwardA = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, PinState.LOW);
    final GpioPinDigitalOutput forwardB = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.LOW);
    final GpioPinDigitalOutput backwardB = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, PinState.LOW);
   // piObstacle obstacle = new piObstacle();
    piUltrasound distanceAway = new piUltrasound();

    volatile boolean move = true;    
    volatile int distance = 20;
    
    Runnable motorForward = new Runnable() {
	     public void run() {
				try {
					contForward();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	     }
	};
	
	  Runnable motorBackward= new Runnable() {
		     public void run() {
					try {
						contBackward();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
		     }
		};
		
		  Runnable motorLeft = new Runnable() {
			     public void run() {
						try {
							contLeft();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
			     }
			};
			
			  Runnable motorRight = new Runnable() {
				     public void run() {
							try {
								contRight();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
				     }
				};
	
    public void distanceCalculation()
    {
    	
    		//distance = distanceAway.checkDistance();
			//DisplayHandler.getInstance().setObstacleLine(Integer.toString(distance));
    	
    }
				
    public void motorDirection(String theDirection) throws InterruptedException
    {
    	
    	if (theDirection.equals("forward"))
		{
    		move = true;
			Thread moveForward = new Thread(motorForward);
			moveForward.start();
		}
		if (theDirection.equals("back"))
		{
			move = true;
			Thread moveBackward = new Thread(motorBackward);
			moveBackward.start();
		}
		if (theDirection.equals("left"))
		{
			move = true;
			Thread moveLeft= new Thread(motorLeft);
			moveLeft.start();
		}
		if (theDirection.equals("right"))
		{
			move = true;
			Thread moveRight = new Thread(motorRight);
			moveRight.start();
		}
		if (theDirection.equals("stop"))
		{
			move = false;
		}
    }
    
    public void forward() throws InterruptedException
    {
    	forwardA.toggle();
    	forwardB.toggle();
    	
    	Thread.sleep(1000);
    	
    	forwardA.toggle();
    	forwardB.toggle();  	
    }
    
    public void backward() throws InterruptedException
    {
    	backwardA.toggle();
    	backwardB.toggle();
    	
    	Thread.sleep(1000);
    	
    	backwardA.toggle();
    	backwardB.toggle();   	
    }
    
    public void left() throws InterruptedException
    {
    	forwardB.toggle();
    	
    	Thread.sleep(3000);
    	
    	forwardB.toggle();
    }
    
    public void right() throws InterruptedException
    {
    	forwardA.toggle();
    	
    	Thread.sleep(3000);
    	
    	forwardA.toggle();
    }
    
    public void contForward() throws InterruptedException
    {
    	//DisplayHandler.getInstance().setMotorLine("forward");
    	forwardA.toggle();
    	forwardB.toggle();
    	while (move == true)
    	{
    	Thread.sleep(50);
    	}
    	forwardA.toggle();
    	forwardB.toggle();  
    	//DisplayHandler.getInstance().setMotorLine("stop");
    }
    
    public void contBackward() throws InterruptedException
    {
    	//DisplayHandler.getInstance().setMotorLine("backward");
    	backwardA.toggle();
    	backwardB.toggle();
    	while (move == true)
    	{
    	Thread.sleep(50);
    	}
    	backwardA.toggle();
    	backwardB.toggle();
    	//DisplayHandler.getInstance().setMotorLine("stop");
    }
    
    public void contLeft() throws InterruptedException
    {
    	//DisplayHandler.getInstance().setMotorLine("left");
    	forwardB.toggle();
    	backwardA.toggle();
    	while (move == true)
    	{
    	Thread.sleep(50);
    	}
    	forwardB.toggle();
    	backwardA.toggle();
    	//DisplayHandler.getInstance().setMotorLine("stop");
    }
    
    public void contRight() throws InterruptedException
    {
    	//DisplayHandler.getInstance().setMotorLine("right");
    	forwardA.toggle();
    	backwardB.toggle();
    	while (move == true)
    	{
    	Thread.sleep(50);
    	}
    	forwardA.toggle();
    	backwardB.toggle();
    	//DisplayHandler.getInstance().setMotorLine("stop");
    	
    }

}
