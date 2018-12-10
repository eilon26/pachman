package GIS;

import java.util.ArrayList;
import java.util.Date;

import Geom.Point3D;

public class pachman_metaData implements Meta_Data {
	private int id;
	private int speed;
	private int radius;
	private ArrayList<LocByTime> loc_by_time;
	
	public pachman_metaData(String[] line,int[] metaIndexes) {
		this.id = Integer.parseInt(line[metaIndexes[1]]);
		this.speed = Integer.parseInt(line[metaIndexes[2]]);
		this.radius = Integer.parseInt(line[metaIndexes[3]]);
		this.loc_by_time = new ArrayList<LocByTime>();
	}
	public pachman_metaData(int id,int speed,int radius) {
		this.id=id;
		this.speed=speed;
		this.radius=radius;
		this.loc_by_time = new ArrayList<LocByTime>();
	}
	
	public ArrayList<LocByTime> getLoc_by_time() {
		return loc_by_time;
	}
	public int getId() {
		return id;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return "pachman [id=" + id + ", speed=" + speed + ", radius=" + radius + "]";
	}

	public int getRadius() {
		return radius;
	}

	@Override
	public long getUTC() {
		return new Date().getTime();
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
