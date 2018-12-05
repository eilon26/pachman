package GIS;

import java.util.Date;

import Geom.Point3D;

public class fruit_metaData implements Meta_Data {
	private int id;
	private int weight;
	
	public fruit_metaData(String[] line,int[] metaIndexes) {
		id = Integer.parseInt(line[metaIndexes[1]]);
		weight = Integer.parseInt(line[metaIndexes[2]]);	
	}

	public int getId() {
		return id;
	}

	public int getweight() {
		return weight;
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