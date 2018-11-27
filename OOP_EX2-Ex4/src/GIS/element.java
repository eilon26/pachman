package GIS;

import java.sql.Time;

import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;

public class element implements GIS_element {
	geom ge; 
	metaData md;
	
	public element(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(line, geomIndexes);
		this.md= new metaData(line, metaIndexes);
		
	}
	public element(element other) {
		this.ge=new geom(other.ge);
		this.md=new metaData(other.md);
	}
	@Override
	public Geom_element getGeom() {
		return ge;
	}
	@Override
	public Meta_data getData() {
		return md;
	}
	@Override
	public void translate(Point3D vec) {
		
	}


}
