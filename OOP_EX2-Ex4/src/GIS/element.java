package GIS;

import java.sql.Time;

import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;

public class element {
	geom ge; 
	metaData md;
	
	public element(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(String[] line, int[] geomIndexes);
		this.md= new metaData(String[] line, int[] metaIndexes);
		
	}
	
	public Geom_element getGeom() {
		return null;
	}
	
	public Meta_data getData() {
		return null;
	}
	
	public void translate(Point3D vec) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
