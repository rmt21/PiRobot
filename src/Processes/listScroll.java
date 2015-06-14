package Processes;
import javax.swing.JList;


public class listScroll extends Thread {
	boolean cont = true;
	
	JList list;
	public listScroll(JList list) throws InterruptedException
	{
		this.list = list;
	}
	
	public void run()
	{
		while (cont)
		{
		int lastIndex = list.getModel().getSize() - 1;
		if (lastIndex >= 0) {
		   list.ensureIndexIsVisible(lastIndex);
		   list.repaint();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
