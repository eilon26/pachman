package GIS.gpsPoint;

import GIS.Meta_Data;
import Geom.Point3D;
/**
 * this class is responsible for the time (creation and universal).
 * @author Daniel Ventura
 *
 */
public class project_metaData implements Meta_Data{
	private String folderName;
	private long utc;
	/**
	 * the constructor.
	 * @param time - creation time.
	 * @param utc - universal time.
	 */
	public project_metaData(String folderName,long utc) {
		this.folderName=folderName;
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
		return "the name of the source folder(or file) is: "+folderName+"\n the creation of the file in Coordinated Universal Time: "+utc;
	}
	

}
