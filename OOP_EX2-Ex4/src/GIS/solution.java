package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.*;

public class solution {
	private ArrayList<GIS_layer> pathes;
	private ArrayList<GIS_element> fruit;
	
	
	public solution(GameBoard GB) {
		Iterator<GIS_element> GBIter = GB.getElement_Set().iterator();
		while (GBIter.hasNext()) {
			GIS_element curr = GBIter.next();
			if (curr instanceof pachman) {
				pathes.add(new pachman_path((pachman) curr));
			}else fruit.add(new fruit((fruit)curr));	
		}
	}
	
	public void calculate() {
		Object[] closest_pach_fruit = new Object[2];
		while (!fruit.isEmpty()) {
			closest_pach_fruit = findClosest();
			fruit.remove((fruit)closest_pach_fruit[1]);
			((pachman_path)closest_pach_fruit[0]).add((fruit)closest_pach_fruit[1]);
			Point3D ChosenFruitPoint = ((geom)((fruit)closest_pach_fruit[1]).getGeom()).getP();
			((geom)(((pachman_path)closest_pach_fruit[0]).getPach().getGeom())).setP(ChosenFruitPoint);
		}
	}

	private Object[] findClosest() {
		Object[] min = new Object[2];//min[0] the pachman in the min distance, min[1] the fruit in the min distance
		double minDist = Double.MAX_VALUE;
		Iterator<GIS_layer> PathesIter = this.pathes.iterator();
		while (PathesIter.hasNext()) {
			Iterator<GIS_element> FruitIter = this.fruit.iterator();
			while (FruitIter.hasNext()) {
				pachman_path currPath = (pachman_path) PathesIter.next();
				fruit currFruit = (fruit)FruitIter.next();
				MyCoords x =new MyCoords();
				Point3D currPachmanPoint = ((geom)currPath.getPach().getGeom()).getP();
				Point3D currFruitPoint = ((geom)currFruit.getGeom()).getP();
				if (x.distance3d(currPachmanPoint,currFruitPoint)<minDist) {
					min[0] = currPath;
					min[1] = currFruit;
				}
			}
		}
		return  min;
	}
}
