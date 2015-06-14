package Processes;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

import JAXB.XmlUnmarshall;


public class serverXML extends Thread {
	public serverXML(int port) throws IOException {
		serverSocket = new ServerSocket(6066);
		serverSocket.setSoTimeout(5000000);
		//this.console = console;
	}
	
	private ServerSocket serverSocket;
	int count = 0;
	InputHandler input = new InputHandler();
	//DefaultListModel<String> console;
	XmlUnmarshall inXML = new XmlUnmarshall(input, DisplayHandler.getInstance());
	
	
	public void run() {
		while (true) {
			
				//receive from sender, based on type of send on what it requires
				System.out.println("Waiting for client on portXML " + serverSocket.getLocalPort() + "...");
				DisplayHandler.getInstance().setServerLine("wait " + serverSocket.getLocalPort());
				Socket server;
				try {
					server = serverSocket.accept();
				
				System.out.println("Connected " + server.getRemoteSocketAddress());
				DisplayHandler.getInstance().setServerLine("connect");//server.getRemoteSocketAddress().toString());
				DataInputStream in = new DataInputStream(server.getInputStream());
				//get from handler
				String xmlIn = in.readUTF();
				String type = in.readUTF();
				
				//write to file
				PrintWriter out = new PrintWriter("newAction.txt");
				out.write(xmlIn);
				out.close();
				
				//unmarshall xml file
				inXML.parse(type, "newAction.txt");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
}
}
