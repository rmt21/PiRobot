package Processes;
import java.net.*;
import java.util.Vector;
import java.io.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class piServer extends Thread {
	private ServerSocket serverSocket;
	int count = 0;
	piInputHandler input = new piInputHandler();
	DefaultListModel<String> console;

	public piServer(int port, DefaultListModel<String> console) throws IOException {
		serverSocket = new ServerSocket(6066);
		serverSocket.setSoTimeout(5000000);
		this.console = console;
	}

	public void run() {
		while (true) {
			try {
				//receive from sender, based on type of send on what it requires
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				console.addElement("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("Connected" + server.getRemoteSocketAddress());
				console.addElement("Connected" + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				//get from handler
				String type = in.readUTF();
				console.addElement("Type = " + type);
				
				if (type.equals("LED"))
				{
					String input1 = in.readUTF();
					int LEDCount = in.readInt();
					
					int length = in.readInt();  
					byte[] input2 = new byte[length];// read length of incoming message
					if(length>0) {
					    in.readFully(input2); // read the message
					}
					
					int length2 = in.readInt();  
					byte[] input3 = new byte[length];// read length of incoming message
					if(length2>0) {
					    in.readFully(input3); // read the message
					}
					LEDObject toLed = new LEDObject();
					toLed.type = type;
					toLed.colour = input1;
					toLed.redResult = input2;
					toLed.greenResult = input3;
					toLed.LEDCount = LEDCount;					
					input.led.addElement(toLed);
				}
				
				if (type.equals("speech"))
				{
					String input1 = in.readUTF();
					input.speech.addElement(input1);
				}
				
				if (type.equals("motor"))
				{
					String input1 = in.readUTF();
					input.direction.addElement(input1);
				}
				
				if (type.equals("pan") || type.equals("tilt"))
				{
					String input1 = in.readUTF();
					String input2 = in.readUTF();
					servoObject servo = new servoObject();
					servo.type = input1;
					servo.direction = input2;
					input.servo.addElement(servo);
				}
				
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("received");
				console.addElement("received");
				server.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public void serverRun() {
		int port = Integer.parseInt("6066");
		try {
			Thread t = new piServer(port,console);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
