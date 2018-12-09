package GUI;

import java.util.Iterator;

import Coords.MyCoords;
import Geom.*;
import GIS.*;
import GIS.pachman_path;

public class draw_thread extends Thread {
	MyFrame frame;
	pachman_path path;
	
	public draw_thread(MyFrame frame,pachman_path path) {
		this.frame = frame;
		this.path = path;

	}
	
	public void run() {
		Iterator<GIS_element> IterF = path.iterator();
		while (IterF.hasNext()) {
			MyCoords x = new MyCoords();
			fruit destFruit = (fruit)IterF.next();
			Point3D pach_point = ((geom)path.getPach().getGeom()).getP();//global point
			Point3D destFruit_point = ((geom)destFruit.getGeom()).getP();//global point
			double[] AED = x.azimuth_elevation_dist(pach_point, destFruit_point);
			
		}
		
		
		frame.getGB().remove(path.getPach());//after moving
		//frame.getGB().remove(path.fruit)// if it is inside the radius
		
	}

}
