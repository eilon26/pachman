package GIS;

import java.sql.Time;

import Geom.Geom_element;
import Geom.Point3D;

public class element {
	private Point3D p;
	String name;
	Time time;
	
	public element(Point3D p, String name, Time time) {
		this.p = p;
		this.name = name;
		this.time = time;
	}
	
	public Geom_element getGeom() {
		return p;
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
