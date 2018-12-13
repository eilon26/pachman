package GUI;

public class repaint_thread extends Thread {
	private MyFrame frame;
	private long wait = 3000;
	
	public repaint_thread(MyFrame frame,long wait) {
		this.frame = frame;
		this.wait= wait;
	}
	
	public void run() {
		long sec_counter=0;
		while(frame.getSol().getGeneraleTime()>(sec_counter/1000)) {
			sec_counter+=wait;
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.frame.repaint();
		}
	}
	
}
