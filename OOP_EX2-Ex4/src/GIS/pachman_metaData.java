package GIS;

import java.util.Date;

import Geom.Point3D;

public class pachman_metaData implements Meta_Data {
	private int id;
	private int speed;
	private int radius;
	
	public pachman_metaData(String[] line,int[] metaIndexes) {
		id = Integer.parseInt(line[metaIndexes[1]]);
		speed = Integer.parseInt(line[metaIndexes[2]]);
		radius = Integer.parseInt(line[metaIndexes[3]]);	
	}
	public pachman_metaData(int id,int speed,int radius) {
		this.id=id;
		this.speed=speed;
		this.radius=radius;
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
