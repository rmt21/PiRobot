import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	     int port = 6066;
	      try
	      {
	         Thread t = new piServer(port);
	         t.start();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	      
//	      executor.scheduleAtFixedRate(pi, 10, 10, TimeUnit.SECONDS);
		
	}

}
