package GUI;
/**'
 * the class responsible to update the situation on the screen every "sec" seconds
 * @author EILON
 *
 */
public class paint_thread extends Thread {
	private double sec;
	private MyFrame frame;
/**
 * the constructor of paint_thread
 * @param frame MyFrame parameter
 * @param sec double parameter
 */
	public paint_thread (MyFrame frame,double sec) {
		this.sec = sec;
		this.frame = frame;
	}
	/**
	 * paint_thread run method
	 */
	public void run() {
		double sum =0;
		while(frame.getSol().getGeneraleTime()>=sum) {
			try {Thread.sleep((long) (sec*1000));//refer to the defined time rati
			} catch (InterruptedException e) {}
			sum+=sec;
			this.frame.repaint();
		}
	}

}
