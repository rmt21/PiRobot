package Processes;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.Gpio;
// not implementing.

public class piObstacle {
	
		final GpioController gpio = GpioFactory.getInstance();
		boolean cont = true;
    
		final GpioPinDigitalInput leftSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);
		final GpioPinDigitalInput rightSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_17, PinPullResistance.PULL_DOWN);
		
		
		

    
    public void checkObstacle()
    {
    	
    	while (cont)
    	{
    		System.out.println(leftSensor.getState());
    		System.out.println(rightSensor.getState());

    	}
    }

}
