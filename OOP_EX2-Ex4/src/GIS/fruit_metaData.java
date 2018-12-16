package GIS;

import java.util.ArrayList;
import java.util.Date;

import Geom.Point3D;

/**
 * the class is responsible to represent fruit 
 * @author EILON
 *
 */
public class fruit_metaData implements Meta_Data {
	private int id;
	private int weight;
	private LocByTime[] loc_by_time;
	/**
	 * the constructor get Array of strings that represent the fruit and get array of indexes. 
	 * by the array of indexes it know to put the information in the suitable field
	 * @param line the Array of strings 
	 * @param metaIndexes the array of indexes
	 */
	public fruit_metaData(String[] line,int[] metaIndexes) {
		id = Integer.parseInt(line[metaIndexes[1]]);
		weight = Integer.parseInt(line[metaIndexes[2]]);	
		this.loc_by_time = new LocByTime[2];
	}
	public fruit_metaData(int id,int weight) {
		this.id=id;
		this.weight=weight;
		this.loc_by_time = new LocByTime[2];
	}
	public fruit_metaData(fruit_metaData other) {
		this.id = other.getId();
		this.weight = other.getweight();
		this.loc_by_time = new LocByTime[2];
	}

	public LocByTime[] getLoc_by_time() {
		return loc_by_time;
	}
	public int getId() {
		return id;
	}

	public int getweight() {
		return weight;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "fruit [id=" + id + ", weight=" + weight + "]";
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