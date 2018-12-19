package GUI;

import java.util.Iterator;
import java.util.TimerTask;

import javax.swing.Timer;

import Coords.MyCoords;
import Geom.*;
import algorithms.ShortestPathAlgo;
import GIS.*;
import GIS.pachman_path;
/**
 * the class that responsible to draw the progress of the pachman along its path in real time
 * @author EILON
 *
 */
public class draw_thread extends Thread  {
	private MyFrame frame;
	private pachman_path path;
	private double sec;
	/**
	 * the thread constructor by frame and path
	 * @param frame MyFrame parameter
	 * @param path pachman_path parameter
	 */
	public draw_thread(MyFrame frame,pachman_path path,double sec) {
		this.frame = frame;
		this.path = path;
		this.sec = sec;

	}
	/**
	 * the rum method of the thread that actually responsible to draw the progress of the pachman along its path in real time
	 */
	public void run() {
		Iterator<GIS_element> IterF = path.iterator();
		MyCoords x = new MyCoords();
		if (IterF.hasNext()) {
			Point3D pach_point = ((geom)path.getPach().getGeom()).getP();//global point
			path.add_to_draw_point(pach_point);
			frame.getGB().remove(path.getPach());
		}
		while  (IterF.hasNext()) {
			fruit destFruit = (fruit)IterF.next();
			Point3D pach_point = ((geom)path.getPach().getGeom()).getP();//global point
			int pach_speed = ((pachman_metaData)path.getPach().getData()).getSpeed();
			Point3D end_session_pachNewPoint = ShortestPathAlgo.pachNewPoint(path,destFruit);
			double dest2NewFruit = x.distance3d(pach_point, end_session_pachNewPoint);
			double time2DestFruit = (Math.max(0,dest2NewFruit ))/pach_speed;
			Point3D theBigVector = x.vector3D(pach_point, end_session_pachNewPoint);
			double timeRatio = sec/time2DestFruit;//time ratio set to 1 sec
			Point3D theLittleVector = new Point3D(timeRatio*theBigVector.x(),timeRatio*theBigVector.y(),timeRatio*theBigVector.z());
			for (double i=sec;i<=time2DestFruit;i=i+sec) {//refer to the defined time ratio
				pach_point = x.add(pach_point, theLittleVector);
				path.add_to_draw_point(pach_point);
				path.getPach().setGe(new geom(pach_point));//set pachman new point
				try {Thread.sleep((long) (sec*1000));//refer to the defined time rati
				} catch (InterruptedException e) {}
			}
			destFruit.setAlive(false);
			
			
		}
		

	}

}
