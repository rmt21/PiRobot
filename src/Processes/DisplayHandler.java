package Processes;

public class DisplayHandler {
	
	private static DisplayHandler instance;
	private DisplayHandler()
	{
		Thread actionPushToDisplay = new Thread(new Runnable() {
		     public void run() {
		          displayProcessor();
		     }
		}); 
		actionPushToDisplay.start();
	}
	
	private String infoLine = "pimus999";
	private String serverLine= "no data";
	private String motorLine= "no data";
	private String speechLine= "no data";
	private String obstacleLine= "no data";
	private String systemUsage= "no data";
	private String cameraLine= "no data";
	OledDisplay display = new OledDisplay();
	
	public static DisplayHandler getInstance()
	{
		if (instance == null)
		{
			instance = new DisplayHandler();
		}
		
		return instance;
	}
	
	public String getInfoLine() {
		return infoLine;
	}
	public void setInfoLine(String infoLine) {
		this.infoLine = infoLine;
	}
	public String getServerLine() {
		return serverLine;
	}
	public void setServerLine(String serverLine) {
		this.serverLine = serverLine;
	}
	public String getMotorLine() {
		return motorLine;
	}
	public void setMotorLine(String motorLine) {
		this.motorLine = motorLine;
	}
	public String getSpeechLine() {
		return speechLine;
	}
	public void setSpeechLine(String speechLine) {
		this.speechLine = speechLine;
	}
	public String getObstacleLine() {
		return obstacleLine;
	}
	public void setObstacleLine(String obstacleLine) {
		this.obstacleLine = obstacleLine;
	}
	public String getSystemUsage() {
		return systemUsage;
	}
	public void setSystemUsage(String systemUsage) {
		this.systemUsage = systemUsage;
	}
	public String getCameraLine() {
		return cameraLine;
	}
	public void setCameraLine(String cameraLine) {
		this.cameraLine = cameraLine;
	}
	
	public void displayProcessor()
	{
		while (true)
		{
		
		 String infoLineDisp =getInfoLine();
		 String serverLineDisp=getServerLine();
		 String motorLineDisp=getMotorLine();
		 String speechLineDisp=getSpeechLine();
		 String obstacleLineDisp=getObstacleLine();
		 String systemUsageDisp=getSystemUsage();
		 String cameraLineDisp=getCameraLine();
		 
		 if (infoLineDisp.length() != 13)
		 {
			 for (int i =infoLineDisp.length(); i<=13;i++)
			 {
				 infoLineDisp += " ";
			 }
		 }
		 if (serverLineDisp.length() != 13)
		 {
			 for (int i =serverLineDisp.length(); i<=13;i++)
			 {
				 serverLineDisp += " ";
			 }
		 }
		 if (motorLineDisp.length() != 13)
		 {
			 for (int i =motorLineDisp.length(); i<=13;i++)
			 {
				 motorLineDisp += " ";
			 }
		 }
		 if (speechLineDisp.length() != 13)
		 {
			 for (int i =speechLineDisp.length(); i<=13;i++)
			 {
				 speechLineDisp += " ";
			 }
		 }
		 if (obstacleLineDisp.length() != 13)
		 {
			 for (int i =obstacleLineDisp.length(); i<=13;i++)
			 {
				 obstacleLineDisp += " ";
			 }
		 }
		 if (systemUsageDisp.length() != 13)
		 {
			 for (int i =systemUsageDisp.length(); i<=13;i++)
			 {
				 systemUsageDisp += " ";
			 }
		 }
		 if (cameraLineDisp.length() != 13)
		 {
			 for (int i =cameraLineDisp.length(); i<=13;i++)
			 {
				 cameraLineDisp += " ";
			 }
		 }		
		String toDisplay = (infoLineDisp + serverLineDisp + motorLineDisp + speechLineDisp + obstacleLineDisp + systemUsageDisp + cameraLineDisp);
		display.stringToByte(toDisplay);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	

}
