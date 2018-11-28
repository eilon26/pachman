package GIS;

import Geom.Point3D;

public class Layer_metaData implements Meta_Data{
	private String time,location;
	private long utc;

	public Layer_metaData(String location,String time,long utc) {
		this.time = time;
		this.location = location;
		this.utc = utc;
	}

	public String getTime() {
		return time;
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
	@Override
	public String toString() {
		return location+"start record time: "+time+"\n Coordinated Universal Time: "+utc;
		
	}
	
	
}
