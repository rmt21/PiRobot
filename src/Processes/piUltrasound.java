package Processes;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;


public class piUltrasound {
	

	public piUltrasound()
	{
	}
		
		public int checkDistance()
		{
				int distance = calculateDistance();
				return distance;
		}
		
		
		int calculateDistance()
		{	
			// run pyton script as java response too slow to get reliable responses
			//System.out.println("calculate distance");
			try{
			ProcessBuilder pb = new ProcessBuilder("python","classes/sonarTest.py");
			
			Process p = pb.start();
			//System.out.println("pb start");
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			int ret = new Integer(in.readLine()).intValue();
			return ret;
			}catch(Exception e){System.out.println(e);}
			
			return 0;

		}
}


		
		


