package GIS;

import Geom.Point3D;

public class project_metaData implements Meta_Data{
	private String time;
	private long utc;
	
	public project_metaData(String time,long utc) {
		this.time=time;
		this.utc=utc;
	}
	@Override
	public long getUTC() {
		return utc;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	@Override
	public String toString() {
		return "the time of the first record: "+time+"\n the creation of the file in Coordinated Universal Time: "+utc;
	}
	

}
