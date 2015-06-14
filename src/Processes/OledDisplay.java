package Processes;

import com.pi4j.jni.I2C;

public class OledDisplay {
	
	private int[] oledInitialise;
	private int fd;
	byte[] buffer = new byte[1025];
	public OledDisplay()
	{
		
		oledInitialise = new int[26];
		oledInitialise[0] =0xae; //clear
		oledInitialise[1] =0xd5;
		oledInitialise[2] =0x80;
		oledInitialise[3] =0xa8;
		oledInitialise[4] =0x3f;
		oledInitialise[5] =0xd3;
		oledInitialise[6] =0x0;
		oledInitialise[7] =0x0;//0x0 
		oledInitialise[8] =0x8d;
		oledInitialise[9] =0x14;
		oledInitialise[10] =0x20;
		oledInitialise[11] =0x0;
		oledInitialise[12] =0xa1;
		oledInitialise[13] =0xc8;
		oledInitialise[14] =0xda;
		oledInitialise[15] =0x12;//0x80
		oledInitialise[16] =0x81;
		oledInitialise[17] =0xcf;
		oledInitialise[18] =0xd9;
		oledInitialise[19] =0xf1;
		oledInitialise[20] =0xdb;
		oledInitialise[21] =0x40;
		oledInitialise[22] =0xa4;
		oledInitialise[23] =0xa6;
		oledInitialise[24] =0xaf;
		oledInitialise[25] =0;
		
		fd = I2C.i2cOpen("/dev/i2c-1"); // open connection
		initialiseDisplay();
		clearDisplay();
		
	}
	
	private void initialiseDisplay()
	{
		for (int i = 0;i < 25; i++ )
		{
			 I2C.i2cWriteByte(fd, 0x3c, 0x00, (byte) oledInitialise[i]);
		}	
	}
	
	public void clearDisplay()
	{


		for (int i=0;i <=1024;i++)
		{
			buffer[i] = 0x00;
		}
		
		I2C.i2cWriteBytes(fd, 0x3c, 0x40, 1024, 0, buffer);
		
		I2C.i2cWriteByte(fd, 0x3c, 0x21,(byte) 0);
		I2C.i2cWriteByte(fd, 0x3c, 0x21,(byte) 127);
		
		I2C.i2cWriteByte(fd, 0x3c, 0x21,(byte) 0);
		I2C.i2cWriteByte(fd, 0x3c, 0x21,(byte) 7);

		
	}
	
	public void writeDisplay(byte[] buffer)
	{
		I2C.i2cWriteBytes(fd, 0x3c, 0x40, 1024, 0, buffer);
	}
	
	public byte[] textByte(char letter)
	{
		byte[] text = new byte[8];
		if (letter == ('a'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x09;
			text[3] = (byte) 0x09;
			text[4] = (byte) 0x09;
			text[5] = (byte) 0x09;
			text[6] = (byte) 0xff;
			text[7] = (byte) 0xff;
		}
		
		if (letter == ('b'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x99;
			text[3] = (byte) 0x99;
			text[4] = (byte) 0x99;
			text[5] = (byte) 0x99;
			text[6] = (byte) 0xff;
			text[7] = (byte) 0xff;
		}
		if (letter == ('c'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xc3;
			text[3] = (byte) 0xc3;
			text[4] = (byte) 0xc3;
			text[5] = (byte) 0xc3;
			text[6] = (byte) 0xc3;
			text[7] = (byte) 0xc3;
		}
		if (letter == ('d'))
		{
			text[0] = (byte) 0xf0;
			text[1] = (byte) 0xf0;
			text[2] = (byte) 0x90;
			text[3] = (byte) 0x90;
			text[4] = (byte) 0x90;
			text[5] = (byte) 0x90;
			text[6] = (byte) 0xff;
			text[7] = (byte) 0xff;
		}
		if (letter == ('e'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xdb;
			text[3] = (byte) 0xdb;
			text[4] = (byte) 0xdb;
			text[5] = (byte) 0xdb;
			text[6] = (byte) 0xdb;
			text[7] = (byte) 0xdb;
		}
		if (letter == ('f'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x9;
			text[3] = (byte) 0x9;
			text[4] = (byte) 0x9;
			text[5] = (byte) 0x9;
			text[6] = (byte) 0x9; 
			text[7] = (byte) 0x9;
		}
		if (letter == ('g'))
		{
			text[0] = (byte) 0xf;
			text[1] = (byte) 0xf;
			text[2] = (byte) 0xc9;
			text[3] = (byte) 0xc9;
			text[4] = (byte) 0xc9;
			text[5] = (byte) 0xc9;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;	
		}
		if (letter == ('h'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x18;
			text[3] = (byte) 0x18;
			text[4] = (byte) 0x18;
			text[5] = (byte) 0x18; 
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('i'))
		{
			text[0] = (byte) 0xc3;
			text[1] = (byte) 0xc3;
			text[2] = (byte) 0xc3;
			text[3] = (byte) 0xff;
			text[4] = (byte) 0xff;
			text[5] = (byte) 0xc3; 
			text[6] = (byte) 0xc3; 
			text[7] = (byte) 0xc3;
		}
		if (letter == ('j'))
		{
			text[0] = (byte) 0xe3;
			text[1] = (byte) 0xe3;
			text[2] = (byte) 0xc3;
			text[3] = (byte) 0xc3;
			text[4] = (byte) 0xc3;
			text[5] = (byte) 0xc3; 
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('k'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x18;
			text[3] = (byte) 0x14;
			text[4] = (byte) 0x24;
			text[5] = (byte) 0x22; 
			text[6] = (byte) 0x42; 
			text[7] = (byte) 0x81;
		}
		if (letter == ('l'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xc0;
			text[3] = (byte) 0xc0;
			text[4] = (byte) 0xc0;
			text[5] = (byte) 0xc0;
			text[6] = (byte) 0xc0; 
			text[7] = (byte) 0xc0;
		}
		if (letter == ('m'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x2;
			text[3] = (byte) 0x4;
			text[4] = (byte) 0x4;
			text[5] = (byte) 0x2;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('n'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x2;
			text[3] = (byte) 0x4;
			text[4] = (byte) 0x8;
			text[5] = (byte) 0x10;
			text[6] = (byte) 0x20; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('o'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xc3;
			text[3] = (byte) 0xc3;
			text[4] = (byte) 0xc3;
			text[5] = (byte) 0xc3;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('p'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xb;
			text[3] = (byte) 0xb;
			text[4] = (byte) 0xb;
			text[5] = (byte) 0xb;
			text[6] = (byte) 0xf; 
			text[7] = (byte) 0xf;
		}
		if (letter == ('q'))
		{
			text[0] = (byte) 0xf;
			text[1] = (byte) 0xf;
			text[2] = (byte) 0xb;
			text[3] = (byte) 0xb;
			text[4] = (byte) 0xb;
			text[5] = (byte) 0xff;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0x60;
		}
		if (letter == ('r'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xb;
			text[3] = (byte) 0xb;
			text[4] = (byte) 0x1b;
			text[5] = (byte) 0x2b;
			text[6] = (byte) 0x4b; 
			text[7] = (byte) 0x8f;
		}
		if (letter == ('s'))
		{
			text[0] = (byte) 0x8f;
			text[1] = (byte) 0x8f;
			text[2] = (byte) 0x89;
			text[3] = (byte) 0x89;
			text[4] = (byte) 0x89;
			text[5] = (byte) 0x89;
			text[6] = (byte) 0xf1; 
			text[7] = (byte) 0xf1;
		}
		if (letter == ('t'))
		{
			text[0] = (byte) 0x3;
			text[1] = (byte) 0x3;
			text[2] = (byte) 0x3;
			text[3] = (byte) 0xff;
			text[4] = (byte) 0xff;
			text[5] = (byte) 0x3;
			text[6] = (byte) 0x3; 
			text[7] = (byte) 0x3;
			
		}
		if (letter == ('u'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xc0;
			text[3] = (byte) 0xc0;
			text[4] = (byte) 0xc0;
			text[5] = (byte) 0xc0;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('v'))
		{
			text[0] = (byte) 0x3f;
			text[1] = (byte) 0x3f;
			text[2] = (byte) 0x40;
			text[3] = (byte) 0x80;
			text[4] = (byte) 0x80;
			text[5] = (byte) 0x40;
			text[6] = (byte) 0x3f; 
			text[7] = (byte) 0x3f;
		}
		if (letter == ('w'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0x40;
			text[3] = (byte) 0x20;
			text[4] = (byte) 0x20;
			text[5] = (byte) 0x40;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('x'))
		{
			text[0] = (byte) 0xc3;
			text[1] = (byte) 0xc3;
			text[2] = (byte) 0x36;
			text[3] = (byte) 0x8;
			text[4] = (byte) 0x8;
			text[5] = (byte) 0x36;
			text[6] = (byte) 0xc3; 
			text[7] = (byte) 0xc3;
		}
		if (letter == ('y'))
		{
			text[0] = (byte) 0xcf;
			text[1] = (byte) 0xcf;
			text[2] = (byte) 0x8c;
			text[3] = (byte) 0x8c;
			text[4] = (byte) 0x8c;
			text[5] = (byte) 0x8c;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('z'))
		{
			text[0] = (byte) 0xe3;
			text[1] = (byte) 0xe3;
			text[2] = (byte) 0xa3;
			text[3] = (byte) 0x93;
			text[4] = (byte) 0x8b;
			text[5] = (byte) 0x87;
			text[6] = (byte) 0xc7; 
			text[7] = (byte) 0xc7;
		}
		if (letter == (' '))
		{
			text[0] = (byte) 0x00;
			text[1] = (byte) 0x00;
			text[2] = (byte) 0x00;
			text[3] = (byte) 0x00;
			text[4] = (byte) 0x00;
			text[5] = (byte) 0x00;
			text[6] = (byte) 0x00; 
			text[7] = (byte) 0x00;
		}
		if (letter == ('0'))
		{
			text[0] = (byte) 0x18;
			text[1] = (byte) 0xe7;
			text[2] = (byte) 0xc3;
			text[3] = (byte) 0xc3;
			text[4] = (byte) 0xc3;
			text[5] = (byte) 0xc3;
			text[6] = (byte) 0xe7; 
			text[7] = (byte) 0x18;
		}
		if (letter == ('1'))
		{
			text[0] = (byte) 0x00;
			text[1] = (byte) 0x00;
			text[2] = (byte) 0x00;
			text[3] = (byte) 0x6;
			text[4] = (byte) 06;
			text[5] = (byte) 0xff;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('2'))
		{
			text[0] = (byte) 0xf1;
			text[1] = (byte) 0xf1;
			text[2] = (byte) 0xc9;
			text[3] = (byte) 0xc9;
			text[4] = (byte) 0xc9;
			text[5] = (byte) 0xc9;
			text[6] = (byte) 0xf; 
			text[7] = (byte) 0xf;
		}
		if (letter == ('3'))
		{
			text[0] = (byte) 0x82;
			text[1] = (byte) 0x81;
			text[2] = (byte) 0x91;
			text[3] = (byte) 0x91;
			text[4] = (byte) 0x91;
			text[5] = (byte) 0x91;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('4'))
		{
			text[0] = (byte) 0xf;
			text[1] = (byte) 0xf;
			text[2] = (byte) 0x18;
			text[3] = (byte) 0x18;
			text[4] = (byte) 0xfc;
			text[5] = (byte) 0xfc;
			text[6] = (byte) 0x18; 
			text[7] = (byte) 0x15;
		}
		if (letter == ('5'))
		{
			text[0] = (byte) 0xcf;
			text[1] = (byte) 0xcf;
			text[2] = (byte) 0xc9;
			text[3] = (byte) 0xc9;
			text[4] = (byte) 0xc9;
			text[5] = (byte) 0xc9;
			text[6] = (byte) 0xf1; 
			text[7] = (byte) 0xf1;
		}
		if (letter == ('6'))
		{
			text[0] = (byte) 0xf8;
			text[1] = (byte) 0x94;
			text[2] = (byte) 0x94;
			text[3] = (byte) 0x92;
			text[4] = (byte) 0x92;
			text[5] = (byte) 0xf1;
			text[6] = (byte) 0xf1; 
			text[7] = (byte) 0xf1;
		}
		if (letter == ('7'))
		{
			text[0] = (byte) 0x83;
			text[1] = (byte) 0x43;
			text[2] = (byte) 0x23;
			text[3] = (byte) 0x13;
			text[4] = (byte) 0x13;
			text[5] = (byte) 0xb;
			text[6] = (byte) 0x7; 
			text[7] = (byte) 0x7;
		}
		if (letter == ('8'))
		{
			text[0] = (byte) 0xff;
			text[1] = (byte) 0xff;
			text[2] = (byte) 0xc9;
			text[3] = (byte) 0xc9;
			text[4] = (byte) 0xc9;
			text[5] = (byte) 0xc9;
			text[6] = (byte) 0xff; 
			text[7] = (byte) 0xff;
		}
		if (letter == ('9'))
		{
			text[0] = (byte) 0x8f;
			text[1] = (byte) 0x49;
			text[2] = (byte) 0x49;
			text[3] = (byte) 0x29;
			text[4] = (byte) 0x29;
			text[5] = (byte) 0x19;
			text[6] = (byte) 0x1f; 
			text[7] = (byte) 0x1f;
		}
		if (letter == ('.'))
		{
			text[0] = (byte) 0x00;
			text[1] = (byte) 0x00;
			text[2] = (byte) 0x00;
			text[3] = (byte) 0xc0;
			text[4] = (byte) 0xc0;
			text[5] = (byte) 0x00;
			text[6] = (byte) 0x00; 
			text[7] = (byte) 0x00;
		}
		
		return text;
	}
	
	public void stringToByte(String data)
	{
		byte[] hold = new byte[7];
		int count = 16; // cant get buffer to start at top...?
		int lineEnd = 13;
		for (int i=0; i < data.length();i++)
		{
			
			hold = textByte(data.charAt(i));
			for (int ii =0; ii <8;ii++)
			{
				if (count < 1024)
				{
				buffer[count] = hold[ii];
				count++;
				}
			}
			if (count < 1024)
			{
			buffer[count] = 0;
			count++;
			if (i == lineEnd)
			{
				
				buffer[count] = 0;
				count++;
				buffer[count] = 0;
				count++;
				lineEnd = lineEnd+14;
			}
			}
		}
		
		if (count < 1024)
		{
			for (int iii = count; iii <=1024;iii++)
			{
				buffer [iii] = 0x00;
			}
		}
		
		writeDisplay(buffer);
		
	}
	

}
