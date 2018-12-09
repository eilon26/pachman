package algorithms;

import java.util.ArrayList;
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
			}else fruit.add((fruit)curr);	
		}
		this.generalGrade=0;
		this.generaleTime=0;
	}
	


	public void calculate() {
		Object[] closest_pach_fruit = new Object[2];
		while (!fruit.isEmpty()) {
			MyCoords x = new MyCoords();
			closest_pach_fruit = findClosest();
			fruit.remove((fruit)closest_pach_fruit[1]);
			((pachman_path)closest_pach_fruit[0]).add((fruit)closest_pach_fruit[1]);
			Point3D pach_loc = ((geom)((pachman_path)closest_pach_fruit[0]).getPach().getGeom()).getP();
			int pach_speed =  ((pachman_metaData)(((pachman_path)closest_pach_fruit[0]).getPach().getData())).getSpeed();
			Point3D fruit_loc = ((geom)((fruit)closest_pach_fruit[1]).getGeom()).getP();
			double time = ((pachman_path)closest_pach_fruit[0]).getTime() + (x.distance3d(pach_loc, fruit_loc)/pach_speed);
			this.generaleTime = Math.max(generaleTime, time);
			((pachman_path)closest_pach_fruit[0]).setTime(time);
			Point3D ChosenFruitPoint = ((geom)((fruit)closest_pach_fruit[1]).getGeom()).getP();
			((geom)(((pachman_path)closest_pach_fruit[0]).getPach().getGeom())).setP(ChosenFruitPoint);
			int fruitGrade = ((fruit_metaData)((fruit)closest_pach_fruit[1]).getData()).getweight();
			((pachman_path)closest_pach_fruit[0]).setGrade(fruitGrade);
			this.generalGrade+=fruitGrade;
		}
		Iterator<GIS_layer> PathesIter = this.pathes.iterator();
		while (PathesIter.hasNext()) {
			pachman_path currPath = (pachman_path) PathesIter.next();
			currPath.setPachGeom(currPath.getStart_point()); 
		}
	}

	public int getGeneralGrade() {
		return generalGrade;
	}

	private Object[] findClosest() {
		Object[] min = new Object[2];//min[0] the pachman in the min distance, min[1] the fruit in the min distance
		double minDist = Double.MAX_VALUE;
		Iterator<GIS_layer> PathesIter = this.pathes.iterator();
		while (PathesIter.hasNext()) {
			pachman_path currPath = (pachman_path) PathesIter.next();
			Iterator<GIS_element> FruitIter = this.fruit.iterator();
			while (FruitIter.hasNext()) {
				fruit currFruit = (fruit)FruitIter.next();
				MyCoords x =new MyCoords();
				Point3D currPachmanPoint = ((geom)currPath.getPach().getGeom()).getP();
				Point3D currFruitPoint = ((geom)currFruit.getGeom()).getP();
				double curr_dist = x.distance3d(currPachmanPoint,currFruitPoint);
				if (curr_dist<minDist) {
					minDist=curr_dist;
					min[0] = currPath;
					min[1] = currFruit;
				}
			}
		}
		return  min;
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
