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

	// create gpio controller
    final GpioController gpio = GpioFactory.getInstance();
    
    final GpioPinDigitalOutput forwardA = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, PinState.LOW);
    final GpioPinDigitalOutput backwardA = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, PinState.LOW);
    final GpioPinDigitalOutput forwardB = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.LOW);
    final GpioPinDigitalOutput backwardB = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, PinState.LOW);
    
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
    	
    	Thread.sleep(1000);
    	
    	forwardB.toggle();
    }
    
    public void right() throws InterruptedException
    {
    	forwardA.toggle();
    	
    	Thread.sleep(1000);
    	
    	forwardA.toggle();
    }
    
    


	
	
}
