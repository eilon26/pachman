package GIS;

import java.util.Date;

import Geom.Point3D;

public class LocByTime {
	private Point3D location;
	private String time;
	
	public LocByTime() {
		this.location = null;
		this.time = null;
	}

	public LocByTime(Point3D location, String time) {
		this.location = location;
		this.time = time;
	}

	public Point3D getLocation() {
		return location;
	}

	public String getTime() {
		return time;
	}
	
	
}

