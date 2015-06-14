package Processes;


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

public class piLEDEngine {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */

	private int[] LEDColumn;
	private byte[] greenResult;
	private byte[] redResult;
	private int fd;
	private List<String> LEDLine;
	private String sCurrentLine;
	private int lineCount;
	private int LEDLineCount;
	private boolean cont = true;
	
	public piLEDEngine()
	{
		//constructors
		LEDColumn = new int[17];
		LEDColumn[0] = 0x0f;
		LEDColumn[1] = 0x0e;
		LEDColumn[2] = 0x0d;
		LEDColumn[3] = 0x0c;
		LEDColumn[4] = 0x0b;
		LEDColumn[5] = 0x0a;
		LEDColumn[6] = 0x09;
		LEDColumn[7] = 0x08;
		LEDColumn[8] = 0x07;
		LEDColumn[9] = 0x06;
		LEDColumn[10] = 0x05;
		LEDColumn[11] = 0x04;
		LEDColumn[12] = 0x03;
		LEDColumn[13] = 0x02;
		LEDColumn[14] = 0x01;
		LEDColumn[15] = 0x00;
		LEDColumn[16] = 0;
	
		greenResult = null;
		redResult = null;
		
		LEDLine = new ArrayList<>();
		lineCount = 0;
		LEDLineCount = 0;
		
		fd = I2C.i2cOpen("/dev/i2c-1"); // open connection
		initialiseDisplay();
	}
	
	public void run(String colour, byte[] greenResult, byte[] redResult, int LEDCount) throws InterruptedException
	
	{
		LEDLineCount = LEDCount;
		
		if (colour.equals("both"))
		{
			System.out.print("both");
			populateDisplayBi(redResult, greenResult);
		}
		
		if (colour.equals("green"))
		{
			populateDisplaySingle("green", greenResult);
		}
		
		if (colour.equals("red"))
		{
			populateDisplaySingle("red", redResult);
		}
		
		
		}
		
	public void resetDisplay()
	{
		clearMatrix(fd, LEDColumn);
	}
		
	private void initialiseDisplay()
	{
	    //turn on
	    I2C.i2cWriteByteDirect(fd, 0x70, (byte) 0x21);
	    I2C.i2cWriteByteDirect(fd, 0x70, (byte) 0x81);
	    I2C.i2cWriteByteDirect(fd, 0x70, (byte) 0xe0);
	    clearMatrix(fd, LEDColumn); // clear led matrix for use
	}
	
	private void populateDisplaySingle(String colour, byte[] result) throws InterruptedException
	{
		if (colour.equals("red"))
		{
		for (int scrollCount = 0; scrollCount <LEDLineCount; scrollCount++)
	    {
	    	int showResult = scrollCount;
	    	for (int columnCount = 0; columnCount <16; columnCount++)
	    	{
	    		if (showResult < LEDLineCount)
	    		{
	    			//write saved values to display RED LED
	    			showResult++;
	    			I2C.i2cWriteByte(fd ,0x70 ,LEDColumn[columnCount] , result[showResult]);
	    			columnCount++;
	    	}
	    }
    	//sleep so scrolling can be seen
	    Thread.sleep(200);
	    }
		}
		if (colour.equals("green"))
		{
	    	for (int scrollCount = 0; scrollCount <LEDLineCount; scrollCount++)
		    {
		    	int showResult = scrollCount;		 
		    	for (int columnCount = 1; columnCount <16; columnCount++)
		    	{
		    		if (showResult < LEDLineCount)
		    		{
		    			//write saved values to display GREEN LED
		    			showResult++;
		    			I2C.i2cWriteByte(fd ,0x70 ,LEDColumn[columnCount] , result[showResult]);
		    			columnCount++;
		    		}
		    }
	    	//sleep so scrolling can be seen
		    Thread.sleep(200);
		    }
		}
	}
	
	private void populateDisplayBi(byte[] redResult, byte[] greenResult) throws InterruptedException
	{

		for (int scrollCount = 0; scrollCount <LEDLineCount; scrollCount++)
	    {
	    	int showResult = scrollCount;
	    	for (int columnCount = 0; columnCount <16; columnCount++)
	    	{
	    		if (showResult < LEDLineCount/2)
	    		{
	    			//write saved values to display RED LED
	    			showResult++;
	    			I2C.i2cWriteByte(fd ,0x70 ,LEDColumn[columnCount] , redResult[showResult]);
	    			I2C.i2cWriteByte(fd ,0x70 ,LEDColumn[columnCount+1] , greenResult[showResult]);
	    			columnCount++;
	    	}
	    }
/*
	    	for (int scrollCount2 = 0; scrollCount2 <LEDLineCount; scrollCount2++)
		    {
		    	int showResult2 = scrollCount2;		 
		    	for (int columnCount = 1; columnCount <16; columnCount++)
		    	{
		    		if (showResult2 < LEDLineCount)
		    		{
		    			//write saved values to display GREEN LED
		    			showResult2++;
		    			I2C.i2cWriteByte(fd ,0x70 ,LEDColumn[columnCount] , greenResult[showResult2]);
		    			columnCount++;
		    		}
		    }
		    }
		    */
		    //sleep so scrolling can be seen
		    Thread.sleep(200);
		}
	}
	
	private static void clearMatrix(int fd, int[] LEDColumn)
	{
		for (int i =0; i <=16; i++)
		{
		I2C.i2cWriteByte(fd ,0x70 ,LEDColumn[i] ,(byte) 0x00);
		}
	}
}



