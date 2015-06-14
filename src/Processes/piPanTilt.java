package Processes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import JAXB.XmlSERVO;


public class piPanTilt {
		
	private int panPos = 1500;//1500;
	private int tiltPos = 1500;
	
	volatile boolean pan = true;
	volatile boolean tilt = true;


	
	public piPanTilt()
	{
		writeToPanServo(panPos);
		writeToTiltServo(tiltPos);
	}

	Runnable contServoPanLeft = new Runnable() {
	     public void run() {
				try {
					panLeftCont();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	     }
	};
	Runnable contServoPanRight = new Runnable() {
	     public void run() {
				try {
					panRightCont();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	     }
	};
	Runnable contServoTiltUp = new Runnable() {
	     public void run() {
				try {
					tiltUpCont();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	     }
	};
	Runnable contServoTiltDown = new Runnable() {
	     public void run() {
				try {
					tiltDownCont();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	     }
	};
	
	//1000 == -90°
	//1500 == 0°
	//2000 == 90°
	
	public void servoMove(XmlSERVO servo)
	{
		System.out.print(servo.getType());
		if (servo.getType().equals("pan"))
		{
			try {
				panCont(servo.getDirection());
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (servo.getType().equals("tilt"))
		{
			try {
				tiltCont(servo.getDirection());
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void servoPan(String direction) throws InterruptedException, IOException
	{
		if (direction.equals("right"))
		{
			
		}
		
		if (direction.equals("left"))
		{
			
		}
	}
	
	void servoTilt(String direction) throws InterruptedException, IOException
	{
		if (direction.equals("up"))
		{
			
		}
		
		if (direction.equals("down"))
		{
			
		}
	}
	
	void panCont(String direction) throws InterruptedException, IOException
	{
		if (direction.equals("right"))
		{
			pan = true;
			Thread panRight = new Thread(contServoPanRight);
			panRight.start();
		}
		
		if (direction.equals("left"))
		{
			pan = true;
			Thread panLeft = new Thread(contServoPanLeft);
			panLeft.start();
		}
		
		if (direction.equals("stop"))
		{
			pan = false;
			System.out.println("set pan to false  =  " + pan);
		}
	}
	
	void tiltCont(String direction) throws InterruptedException, IOException
	{
		if (direction.equals("up"))
		{
			tilt = true;
			Thread tiltUp = new Thread(contServoTiltUp);
			tiltUp.start();
		}
		
		if (direction.equals("down"))
		{
			tilt = true;
			Thread tiltDown = new Thread(contServoTiltDown);
			tiltDown.start();
		}
		
		if (direction.equals("stop"))
		{
			tilt = false;
		}
	}
	
	
	void panRight() throws InterruptedException, IOException
	{
		panPos = panPos-100;
		writeToPanServo(panPos);
	}
	
	void panLeft() throws InterruptedException, IOException
	{
		panPos = panPos+100;
		writeToPanServo(panPos);
	}

	
	void tiltUp() throws InterruptedException, IOException
	{
		tiltPos = tiltPos-100;
		writeToTiltServo(tiltPos);
	}
	
	void tiltDown() throws InterruptedException, IOException
	{
		tiltPos = tiltPos+100;
		writeToTiltServo(tiltPos);
	}
	
	void panRightCont() throws InterruptedException, IOException
	{
		DisplayHandler.getInstance().setMotorLine("right");
		while(pan == true)
		{
		panPos = panPos-50;
		writeToPanServo(panPos);
		Thread.sleep(50);
		}
		DisplayHandler.getInstance().setMotorLine("stop");
	}
	
	void panLeftCont() throws InterruptedException, IOException
	{
		DisplayHandler.getInstance().setMotorLine("left");
		while(pan == true)
		{
		panPos = panPos+50;
		writeToPanServo(panPos);
		Thread.sleep(50);
		}
		DisplayHandler.getInstance().setMotorLine("stop");
	}

	
	void tiltUpCont() throws InterruptedException, IOException
	{
		DisplayHandler.getInstance().setMotorLine("up");
		while(tilt == true)
		{
		tiltPos = tiltPos-50;
		writeToTiltServo(tiltPos);
		Thread.sleep(50);
		}
		DisplayHandler.getInstance().setMotorLine("stop");
	}
	
	void tiltDownCont() throws InterruptedException, IOException
	{
		DisplayHandler.getInstance().setMotorLine("down");
		while(tilt == true)
		{
		tiltPos = tiltPos+50;
		writeToTiltServo(tiltPos);
		Thread.sleep(50);
		}
		DisplayHandler.getInstance().setMotorLine("stop");
	}
	
	
	void writeToPanServo(int value)
	{
		final String servo = "/dev/servoblaster";
		final File servoFile = new File(servo);
		String toFile = Integer.toString(value);
		String output = "7="+toFile+"us";

			try {
				OutputStream out = new FileOutputStream(servoFile);
				OutputStreamWriter writer = new OutputStreamWriter(out);
				writer.write(output+"\n");
				System.out.print(output+"\n");
				//DisplayHandler.getInstance().setMotorLine(DisplayHandler.getInstance().getMotorLine() + " "+output);
				writer.flush();
				writer.close();
			} catch(IOException e) {
				System.out.println("IOError: "+e.toString());
			}
	}
	
	void writeToTiltServo(int value)
	{
		final String servo = "/dev/servoblaster";
		final File servoFile = new File(servo);
		String toFile = Integer.toString(value);
		String output = "6="+toFile+"us";

			try {
				OutputStream out = new FileOutputStream(servoFile);
				OutputStreamWriter writer = new OutputStreamWriter(out);
				writer.write(output+"\n");
				System.out.print(output+"\n");
				//DisplayHandler.getInstance().setMotorLine(DisplayHandler.getInstance().getMotorLine() + " "+output);
				writer.flush();
				writer.close();
			} catch(IOException e) {
				System.out.println("IOError: "+e.toString());
			}
	}

}
