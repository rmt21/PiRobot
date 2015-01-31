import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class piPanTilt {
		
	private int panPos = 1500;
	private int tiltPos = 1500;

	
	piPanTilt()
	{
		writeToPanServo(panPos);
		writeToTiltServo(tiltPos);
	}

	//1000 == -90°
	//1500 == 0°
	//2000 == 90°
	
	public void servoMove(servoObject servo) throws InterruptedException, IOException
	{
		if (servo.type.equals("pan"))
		{
			pan(servo.direction);
		}
		
		if (servo.type.equals("tilt"))
		{
			tilt(servo.direction);
		}
	}
	
	void pan(String direction) throws InterruptedException, IOException
	{
		if (direction.equals("right"))
		{
			panRight();
			tiltUp();
		}
		
		if (direction.equals("left"))
		{
			panLeft();
		}
	}
	
	void tilt(String direction) throws InterruptedException, IOException
	{
		if (direction.equals("up"))
		{
			tiltUp();
		}
		
		if (direction.equals("down"))
		{
			tiltDown();
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
				writer.flush();
				writer.close();
			} catch(IOException e) {
				System.out.println("IOError: "+e.toString());
			}
	}

}
