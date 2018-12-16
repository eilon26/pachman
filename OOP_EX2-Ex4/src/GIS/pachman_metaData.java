package GIS;

import java.util.ArrayList;
import java.util.Date;

import Geom.Point3D;
/**
 * the class is responsible to represent pachman 
 * @author EILON
 *
 */
public class pachman_metaData implements Meta_Data {
	private int id;
	private int speed;
	private int radius;
	private ArrayList<LocByTime> loc_by_time;
	/**
	 * the constructor get Array of strings that represent the pachman and get array of indexes. 
	 * by the array of indexes it know to put the information in the suitable field
	 * @param line the Array of strings 
	 * @param metaIndexes the array of indexes
	 */
	public pachman_metaData(String[] line,int[] metaIndexes) {
		this.id = Integer.parseInt(line[metaIndexes[1]]);
		this.speed = Integer.parseInt(line[metaIndexes[2]]);
		this.radius = Integer.parseInt(line[metaIndexes[3]]);
		this.loc_by_time = new ArrayList<LocByTime>();
	}
	/**
	 * the constructor of pachman meta data that get id, speed and radius
	 * @param id int
	 * @param speed int
	 * @param radius int
	 */
	public pachman_metaData(int id,int speed,int radius) {
		this.id=id;
		this.speed=speed;
		this.radius=radius;
		this.loc_by_time = new ArrayList<LocByTime>();
	}
	/**
	 * the copy constructor
	 * @param md pachman_metaData
	 */
	public pachman_metaData(pachman_metaData md) {
		this.id=md.id;
		this.speed=md.speed;
		this.radius=md.radius;
		this.loc_by_time = new ArrayList<LocByTime>();
	}
	/**
	 * 
	 * @return loc_by_time
	 */
	public ArrayList<LocByTime> getLoc_by_time() {
		return loc_by_time;
	}
	/**
	 *  
	 * @return pachman id
	 */
	public int getId() {
		return id;
	}
	/**
	 *  
	 * @return pachman speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * set pachman id by id
	 * @param id int
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "pachman [id=" + id + ", speed=" + speed + ", radius=" + radius + "]";
	}
	/**
	 * 
	 * @return pachman radius
	 */
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
