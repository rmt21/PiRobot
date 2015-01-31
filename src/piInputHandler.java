import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;


public class piInputHandler {
	
	Vector <LEDObject> led = new Vector<LEDObject>();
	Vector <String> speech = new Vector<String>();
	Vector <String> direction = new Vector<String>();
	Vector <servoObject> servo = new Vector<servoObject>();
	piLEDEngine ledShow = new piLEDEngine();
	LEDObject object = new LEDObject();
	piMotor drive = new piMotor();
	piPanTilt moveServo = new piPanTilt();
	boolean cont = true;
	
	public piInputHandler()
	{
		Thread t1 = new Thread(new Runnable() {
		     public void run() {
		          try {
						LEDHandler();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		});  
		Thread t2 = new Thread(new Runnable() {
		     public void run() {
		          try {
						speechHandler();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		}); 
		Thread t3 = new Thread(new Runnable() {
		     public void run() {
		          try {
						motorHandler();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		}); 
		Thread t4 = new Thread(new Runnable() {
		     public void run() {
		          try {
						servoHandler();
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		}); 
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	void motorHandler() throws InterruptedException
	{
		while (cont)
		{
			int pass = 0;
			direction.trimToSize();
			for (int i=0;i<direction.size();i++)
			{
				String theDirection = direction.get(i).toString();
				if (theDirection.equals("forward"))
				{
					drive.forward();
				}
				if (theDirection.equals("backward"))
				{
					drive.backward();
				}
				if (theDirection.equals("left"))
				{
					//to be done
				}
				if (theDirection.equals("right"))
				{
					//to be done
				}
				pass++;	
			}		
			for (int i =0;i<pass;i++)
			{
				direction.remove(i);
			}
			
			Thread.sleep(5000);
			}
		}
	
	void LEDHandler() throws InterruptedException
	{
		while (cont)
		{
		
			int pass = 0;
		led.trimToSize();
		for (int i=0;i<led.size();i++)
		{
			object = led.get(i);
			ledShow.run(object.colour, object.greenResult, object.redResult, object.LEDCount);
			pass++;		
			System.out.print("led");
		}		
		for (int i =0;i<pass;i++)
		{
			led.remove(i);
		}
		
		Thread.sleep(5000);
		}
		
	}
	
	void speechHandler() throws InterruptedException
	{
		while (cont)
		{
		
			int pass = 0;
		speech.trimToSize();
		for (int i=0;i<speech.size();i++)
		{
			String speak = speech.get(i).toString();
			piSpeech talkBack = new piSpeech(speak);
			talkBack.speaker(speak);
			pass++;			
		}		
		for (int i =0;i<pass;i++)
		{
			speech.remove(i);
		}
		
		Thread.sleep(5000);
		}
	}
	
	void servoHandler() throws InterruptedException, IOException
	{
		while (cont)
		{		
			int pass = 0;
		servo.trimToSize();
		for (int i=0;i<servo.size();i++)
		{
			moveServo.servoMove(servo.get(i));
			pass++;			
		}		
		for (int i =0;i<pass;i++)
		{
			servo.remove(i);
		}
		
		Thread.sleep(5000);
		}
	}

}
