package GIS;

import java.sql.Date;
import java.sql.Time;

import Geom.Point3D;

public class metaData implements Meta_data{
	private long utc;
	//double Orientation;
	private String name;
	private int rssi;
	public metaData(String[] line,int[] metaIndexes) {
		utc = Date.parse(line[metaIndexes[0]]);
		rssi = Integer.parseInt(line[metaIndexes[1]]);
		name = line[metaIndexes[2]];	
	}
	
	public long getUtc() {
		return utc;
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
