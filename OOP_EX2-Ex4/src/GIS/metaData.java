package GIS;

import java.sql.Date;
import java.sql.Time;

import Geom.Point3D;

public class metaData implements Meta_data{
	long utc;
	//double Orientation;
	String name;
	int rssi;
	public metaData(String[] line,int[] metaIndexes) {
		utc = Date.parse(line[metaIndexes[0]]);
		rssi = Integer.parseInt(line[metaIndexes[1]]);
		name = line[metaIndexes[2]];	
	}
	
	public metaData(metaData other) {
		this.utc = other.utc;
		this.name=new String(other.name);
		this.rssi=other.rssi;
	}
	
	@Override
	public long getUTC() {
		return utc;
	}

	public String getName() {
		return name;
	}

	public int getRssi() {
		return rssi;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
