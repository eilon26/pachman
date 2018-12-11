package GIS;


import java.sql.Timestamp;
import Geom.Point3D;

public class LocByTime {
	private Point3D location;
	private String time;
	
	public LocByTime() {
		this.location = null;
		this.time = null;
	}

	public LocByTime(Point3D location, long time) {
		this.location = location;
		this.time = new Timestamp(time).toString();
		this.time = this.time.substring(0, 10)+"T"+this.time.substring(11, 19)+"Z";
//		String month = (d.getMonth()+1)
//		this.time = ""+(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDay())+"T0"+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+"Z";
	}

	public Point3D getLocation() {
		return location;
	}

	public String getTime() {
		return time;
	}
}

