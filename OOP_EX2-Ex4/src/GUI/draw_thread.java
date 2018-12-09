package GUI;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;

import GIS.pachman_path;

public class draw_thread extends Thread {
	MyFrame frame;
	pachman_path path;
	
	public draw_thread(MyFrame frame,pachman_path path) {
		this.frame = frame;
		this.path = path;

	}
	
	public void run() {
		frame.getGB().remove(path.getPach());//after moving
		//frame.getGB().remove(path.fruit)// if it is inside the radius
		
	}

}
