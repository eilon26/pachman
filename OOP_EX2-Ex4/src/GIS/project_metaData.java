package GIS;

import Geom.Point3D;
/**
 * this class is responsible for the time (creation and universal).
 * @author Daniel Ventura
 *
 */
public class project_metaData implements Meta_Data{
	private String time;
	private long utc;
	/**
	 * the constructor.
	 * @param time - creation time.
	 * @param utc - universal time.
	 */
	public project_metaData(String time,long utc) {
		this.time=time;
		this.utc=utc;
	}
	/**
	 * @return utc.
	 */
	@Override
	public long getUTC() {
		return utc;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	/**
	 * @return time created.
	 */
	@Override
	public String toString() {
		return time;
	}
	

}
