package Processes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import JAXB.XmlLED;
import JAXB.XmlMOTOR;
import JAXB.XmlSERVO;
import JAXB.XmlSPEECH;


public class InputHandler {
	public InputHandler()
	{
		Thread actionLEDHandler = new Thread(new Runnable() {
		     public void run() {
		          try {
					actionLEDListProcessor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		}); 
		Thread actionSPEECHHandler = new Thread(new Runnable() {
		     public void run() {
		          try {
					actionSPEECHListProcessor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		}); 
		Thread actionMOTORHandler = new Thread(new Runnable() {
		     public void run() {
		          try {
					actionMOTORListProcessor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		});
		Thread actionSERVOHandler = new Thread(new Runnable() {
		     public void run() {
		          try {
					actionSERVOListProcessor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		});
		actionSPEECHHandler.start();
		actionLEDHandler.start();
		actionMOTORHandler.start();
		actionSERVOHandler.start();
		
	}
	piMotor motor = new piMotor();
	piPanTilt pt = new piPanTilt();

	piLEDEngine ledShow = new piLEDEngine();
	
	public List<XmlLED> actionLED = new ArrayList();
	public List<XmlSPEECH> actionSPEECH = new ArrayList();
	public List<XmlMOTOR> actionMOTOR = new ArrayList();
	public List<XmlSERVO> actionSERVO = new ArrayList();
	
	
	
	
	
	
	
	private void actionLEDListProcessor() throws InterruptedException
	{
		// processes through the list of actions to be performed
		while (true)
		for (int i = 0; i< actionLED.size(); i++)
		{
				XmlLED add = actionLED.get(i);
				ledShow.run(add.getColour(), add.getGreenResult(), add.getRedResult(), add.getLEDLineCount());
				actionLED.remove(i);
		}
	}
	
	private void actionSPEECHListProcessor() throws InterruptedException
	{
		while (true)
			for (int i = 0; i< actionSPEECH.size(); i++)
			{
				XmlSPEECH add = actionSPEECH.get(i);
				String speak = add.getSpeech();
				piSpeech talkBack = new piSpeech(speak);
				talkBack.speaker(speak);
				actionSPEECH.remove(i);
			}
	}
	
	private void actionMOTORListProcessor() throws InterruptedException
	{
		while (true)
			for (int i = 0; i< actionMOTOR.size(); i++)
			{
				XmlMOTOR add = actionMOTOR.get(i);
				motor.motorDirection(add.getDirection());
				actionMOTOR.remove(i);
			}
	}
	private void actionSERVOListProcessor() throws InterruptedException
	{
		while (true)
		{
			for (int i = 0; i< actionSERVO.size(); i++)
			{
				XmlSERVO add = actionSERVO.get(i);
					pt.servoMove(add);
				actionSERVO.remove(i);
			}
		}
	}

}
