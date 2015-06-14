package JAXB;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Processes.DisplayHandler;
import Processes.InputHandler;

public class XmlUnmarshall {
	
	InputHandler toActionList;
	DisplayHandler dispHandle;
	
	public XmlUnmarshall(InputHandler handler, DisplayHandler displayHandler)
	{
		this.toActionList = handler;
		this.dispHandle = DisplayHandler.getInstance();
	}
	
	public void parse(String type, String filename)
	{
		
		if (type.equals("LED"))
		{
		 try {
				File file = new File(filename);
				JAXBContext jaxbContext = JAXBContext.newInstance(XmlLED.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				XmlLED ledAction = (XmlLED) jaxbUnmarshaller.unmarshal(file);		
				
				toActionList.actionLED.add(ledAction);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		}
		
		if (type.equals("speech"))
		{
		 try {
			 
				File file = new File(filename);
				JAXBContext jaxbContext = JAXBContext.newInstance(XmlSPEECH.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				XmlSPEECH speechAction = (XmlSPEECH) jaxbUnmarshaller.unmarshal(file);
				
				toActionList.actionSPEECH.add(speechAction);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		}
		
		if (type.equals("motor"))
		{
		 try {
			 
				File file = new File(filename);
				JAXBContext jaxbContext = JAXBContext.newInstance(XmlMOTOR.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				XmlMOTOR motorAction = (XmlMOTOR) jaxbUnmarshaller.unmarshal(file);
				
				toActionList.actionMOTOR.add(motorAction);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		}
		
		if (type.equals("pan") || type.equals("tilt"))
		{
		 try {
			 
				File file = new File(filename);
				JAXBContext jaxbContext = JAXBContext.newInstance(XmlSERVO.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				XmlSERVO servoAction = (XmlSERVO) jaxbUnmarshaller.unmarshal(file);
				
				toActionList.actionSERVO.add(servoAction);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
		}
	}

}
