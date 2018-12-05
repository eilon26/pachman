package GIS.gpsPoint;

import GIS.Meta_Data;
import Geom.Point3D;
/**
 * this class implements meta_Data, it is responsible for handling the time of creation
 * universal time and location.
 * @author Daniel Ventura and Eilon tsadok
 *
 */
public class Layer_metaData implements Meta_Data{
	private String time,location;
	private long utc;
	/**
	 * the constructor.
	 * @param location - the location (gps).
	 * @param time - the start time recorded.
	 * @param utc - the universal time.
	 */
	public Layer_metaData(String location,String time,long utc) {
		this.time = time;
		this.location = location;
		this.utc = utc;
	}
	/**
	 * @return time.
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @return utc.
	 */
	@Override
	public long getUTC() {
		return utc;
	}
	/**
	 * @return null;
	 */
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return a String that describes the class.
	 */
	@Override
	public String toString() {
		return "the record took in the area of: " +location+"\n record time: "+time+"\n the creation of the file in Coordinated Universal Time: "+utc;
		
	}
	
	
}
