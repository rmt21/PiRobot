package Processes;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PiGUI extends JFrame {

	private JPanel contentPane;
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
//	piLEDEngine pi = new piLEDEngine();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PiGUI frame = new PiGUI();
					frame.setVisible(true);
					frame.setLocation(0, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PiGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel console = new DefaultListModel();
		console.addElement("Program started");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 274, 91);
		contentPane.add(scrollPane);
		JList consoleList = new JList(console);
		scrollPane.setViewportView(consoleList);
		
		 Thread tL;
			try {
				tL = new listScroll(consoleList);
				tL.start();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		JLabel lblRaspiRobotServer = new JLabel("PiMus Server v0.1");
		lblRaspiRobotServer.setBounds(98, 177, 149, 14);
		contentPane.add(lblRaspiRobotServer);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnYes.setBounds(10, 11, 89, 67);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnNo.setBounds(195, 11, 89, 67);
		contentPane.add(btnNo);
		
	     int port = 6066;
	      try
	      {
	        // Thread t = new piServer(port, console);
	    	  Thread t = new serverXML(port);
	         t.start();
	         
	         String speak = "pimus is ready";
				piSpeech talkBack = new piSpeech(speak);
				talkBack.speaker(speak);
	         
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	      
//	      executor.scheduleAtFixedRate(pi, 10, 10, TimeUnit.SECONDS);
		
	}
}
