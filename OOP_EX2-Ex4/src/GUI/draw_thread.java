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
		MyCoords x = new MyCoords();
		if (IterF.hasNext()) {
			fruit destFruit = (fruit)IterF.next();
			Point3D pach_point = ((geom)path.getPach().getGeom()).getP();//global point
			int pach_speed = ((pachman_metaData)path.getPach().getData()).getSpeed();
			Point3D destFruit_point = ((geom)destFruit.getGeom()).getP();//global point
			double time2DestFruit = x.distance3d(pach_point, destFruit_point)/pach_speed;
			try {
				Thread.sleep((long) (time2DestFruit*1000));
			} catch (InterruptedException e) { e.printStackTrace();}
			path.add_to_draw_point(pach_point);
			path.add_to_draw_point(destFruit_point);
			frame.getGB().remove(destFruit);
			frame.getGB().remove(path.getPach());
			path.getPach().setGe(new geom(destFruit_point));
			frame.repaint();
		}
		while  (IterF.hasNext()) {
			fruit destFruit = (fruit)IterF.next();
			Point3D pach_point = ((geom)path.getPach().getGeom()).getP();//global point
			int pach_speed = ((pachman_metaData)path.getPach().getData()).getSpeed();
			Point3D destFruit_point = ((geom)destFruit.getGeom()).getP();//global point
			double time2DestFruit = x.distance3d(pach_point, destFruit_point)/pach_speed;
			try {
				Thread.sleep((long) (time2DestFruit*1000));
			} catch (InterruptedException e) { e.printStackTrace();}
			
			path.add_to_draw_point(destFruit_point);
			frame.getGB().remove(destFruit);
			path.getPach().setGe(new geom(destFruit_point));
			frame.repaint();
		}
		

	}

}
