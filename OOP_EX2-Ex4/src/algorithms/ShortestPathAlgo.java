package algorithms;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.*;
import Geom.Point3D;
import Geom.geom;

public class ShortestPathAlgo {
	private ArrayList<GIS_layer> pathes;
	private ArrayList<GIS_element> fruit;
	private int generalGrade;
	private double generaleTime;
	public ShortestPathAlgo(GameBoard GB) {
		pathes = new ArrayList<GIS_layer>();
		fruit = new ArrayList<GIS_element>();
		Iterator<GIS_element> GBIter = GB.getElement_Set().iterator();
		while (GBIter.hasNext()) {
			GIS_element curr = GBIter.next();
			if (curr instanceof pachman) {
				pathes.add(new pachman_path((pachman) curr));
				LocByTime curr_locByTime = new LocByTime(((geom)((pachman) curr).getGeom()).getP(),new Date().getTime());
				((pachman_metaData)((pachman) curr).getData()).getLoc_by_time().add(curr_locByTime);
			}else {
				fruit.add((fruit)curr);	
				LocByTime curr_locByTime = new LocByTime(((geom)((fruit) curr).getGeom()).getP(),new Date().getTime());
				((fruit_metaData)((fruit) curr).getData()).getLoc_by_time()[0] = (curr_locByTime);
			}
		}
		this.generalGrade=0;
		this.generaleTime=0;
	}
	

	public void calculate() {
		Object[] closest_pach_fruit = new Object[2];
		while (!fruit.isEmpty()) {

			closest_pach_fruit = findClosest();//find the short path by time
			//take care the fruit
			fruit.remove((fruit)closest_pach_fruit[1]);
			((pachman_path)closest_pach_fruit[0]).add((fruit)closest_pach_fruit[1]);
			//fruit location
			Point3D fruit_loc = ((geom)((fruit)closest_pach_fruit[1]).getGeom()).getP();
			
			//calculate and set the time to go through the route
			double time = ((pachman_path)closest_pach_fruit[0]).getTime() + (double)closest_pach_fruit[2];
			this.generaleTime = Math.max(generaleTime, time);
			((pachman_path)closest_pach_fruit[0]).setTime(time);//set path time
			
			//make timestamp pachman and fruit
			LocByTime curr_locByTime = new LocByTime(fruit_loc,new Date().getTime()+(long)(time*1000));	
			((pachman_metaData)((pachman_path)closest_pach_fruit[0]).getPach().getData()).getLoc_by_time().add(curr_locByTime);
			curr_locByTime = new LocByTime(fruit_loc,new Date().getTime()+(long)(time*1000));	
			((fruit_metaData)((fruit)closest_pach_fruit[1]).getData()).getLoc_by_time()[1] = (curr_locByTime);
			
			//calulate and set the new point
//			Point3D pach_loc = ((geom)((pachman_path)closest_pach_fruit[0]).getPach().getGeom()).getP();
//			MyCoords x = new MyCoords();
//			double[] AED = x.azimuth_elevation_dist(pach_loc, fruit_loc);
//			Point3D vecForNewPachLoc = new Point3D((Math.cos(Math.toRadians(AED[0]))*(double)closest_pach_fruit[3]),(Math.toDegrees(Math.sin(AED[0]))*(double)closest_pach_fruit[3]),fruit_loc.z()-pach_loc.z());
//			Point3D newPachLoc = x.add(pach_loc,vecForNewPachLoc);////////////////******************************
			//((geom)(((pachman_path)closest_pach_fruit[0]).getPach().getGeom())).setP(pachNewPoint((pachman_path)closest_pach_fruit[0],(fruit)closest_pach_fruit[1]));//set new pachman location
			((geom)(((pachman_path)closest_pach_fruit[0]).getPach().getGeom())).setP(fruit_loc);//set pachman location
			
			//set grade
			int fruitGrade = ((fruit_metaData)((fruit)closest_pach_fruit[1]).getData()).getweight();
			((pachman_path)closest_pach_fruit[0]).setGrade(fruitGrade);//set pachman grade
			this.generalGrade+=fruitGrade;
		}
		//return each pachman to his beginning point
		Iterator<GIS_layer> PathesIter = this.pathes.iterator();
		while (PathesIter.hasNext()) {
			pachman_path currPath = (pachman_path) PathesIter.next();
			currPath.setPachGeom(currPath.getStart_point()); 
		}
	}
	public static Point3D pachNewPoint(pachman_path path,fruit fruit) {
		Point3D pach_loc = ((geom)path.getPach().getGeom()).getP();
		int pach_radious = ((pachman_metaData)path.getPach().getData()).getRadius();
		Point3D fruit_loc = ((geom)fruit.getGeom()).getP();
		MyCoords x = new MyCoords();
		double[] AED = x.azimuth_elevation_dist(pach_loc, fruit_loc);
		Point3D vecForNewPachLoc = new Point3D((Math.cos(Math.toRadians(AED[0]))*(AED[2]-pach_radious)),(Math.cos(Math.toRadians(AED[0]))*(AED[2]-pach_radious)),fruit_loc.z()-pach_loc.z());
		Point3D newPachLoc = x.add(pach_loc,vecForNewPachLoc);////////////////******************************
		return newPachLoc;
	}


	private Object[] findClosest() {
		Object[] min = new Object[3];//min[0] the pachman in the min distance, min[1] the fruit in the min distance
		min[2] = Double.MAX_VALUE;
		Iterator<GIS_layer> PathesIter = this.pathes.iterator();
		while (PathesIter.hasNext()) {
			pachman_path currPath = (pachman_path) PathesIter.next();
			Iterator<GIS_element> FruitIter = this.fruit.iterator();
			while (FruitIter.hasNext()) {
				fruit currFruit = (fruit)FruitIter.next();
				MyCoords x =new MyCoords();
				Point3D currPachmanPoint = ((geom)currPath.getPach().getGeom()).getP();
				Point3D currFruitPoint = ((geom)currFruit.getGeom()).getP();
				double curr_dist = x.distance3d(currPachmanPoint,currFruitPoint)-((pachman_metaData)currPath.getPach().getData()).getRadius();
				curr_dist = Math.max(0, curr_dist);
				double curr_time = curr_dist/((pachman_metaData)currPath.getPach().getData()).getSpeed();
				if (curr_time < (Double)min[2]) {
					min[2] = curr_time;
					min[0] = currPath;
					min[1] = currFruit;
				}
			}
		}
		return  min;
	}
	
	public int getGeneralGrade() {
		return generalGrade;
	}
	
	public double getGeneraleTime() {
		return generaleTime;
	}

	public void setGeneraleTime(double generaleTime) {
		this.generaleTime = generaleTime;
	}

	public ArrayList<GIS_layer> getPathes() {
		return pathes;
	}

	public ArrayList<GIS_element> getFruit() {
		return fruit;
	}
}
