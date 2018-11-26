package GIS;

import java.sql.Time;

import Geom.Point3D;

public class metaData implements Meta_data{
	String utc;
	double Orientation;
	String name;
	int rssi;
	public metaData(String[] line,int[] metaIndexes) {
		utc = line[metaIndexes[0]];
		rssi = Integer.parseInt(line[metaIndexes[1]]);
		name = line[metaIndexes[2]];	
	}
	
	@Override
	public long getUTC() {
		
		return utc;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
