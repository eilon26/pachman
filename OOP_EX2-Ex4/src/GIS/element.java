package GIS;

import java.sql.Time;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;
/**
 * this class takes the values of x,y and z and creates a geom 
 * @author Daniel Ventura
 *
 */
public class element implements GIS_element {
	private geom ge; 
	private element_metaData md;
	/**
	 * create a geom and a metadata element.
	 * @param line - the line of the file
	 * @param geomIndexes - the indexes of the information.
	 * @param metaIndexes - the indexes of the information.
	 */
	public element(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(line, geomIndexes);
		this.md= new element_metaData(line, metaIndexes);
		
	}
	/**
	 * get a different gis element and changes it in geon and metadata elemnent
	 * @param other - the new gis element.
	 */
	public element(GIS_element other) {
		this.ge=new geom(((element)other).ge);
		this.md=new element_metaData(((element)other).md);
	}
	/**
	 * @return geom element.
	 */
	@Override
	public Geom_element getGeom() {
		return ge;
	}
	/**
	 * return meta data.
	 */
	@Override
	public Meta_Data getData() {
		return md;
	}
	/**
	 * adds the vector to the point in ge.
	 */
	@Override
	public void translate(Point3D vec) {
		MyCoords MC = new MyCoords();
		this.ge.setP(MC.add(this.ge.getP(), vec));
	}


}
