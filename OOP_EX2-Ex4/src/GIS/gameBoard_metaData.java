package GIS;

import GIS.Meta_Data;
import Geom.Point3D;
/**
 * this class implements meta_Data, it is responsible for handling the time of creation
 * universal time and location.
 * @author Daniel Ventura and Eilon tsadok
 *
 */
public class gameBoard_metaData implements Meta_Data{
	private String fileName;
	private long utc;
	/**
	 * the constructor.
	 * @param location - the location (gps).
	 * @param time - the start time recorded.
	 * @param utc - the universal time.
	 */
	public gameBoard_metaData(String fileName,long utc) {
		this.fileName = fileName;
	
		this.utc = utc;
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
		return "the file name is: " +fileName+"\n the creation of the file in Coordinated Universal Time: "+utc;
		
	}
	
	
}
