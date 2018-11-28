package GIS;

import java.sql.Time;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;

public class element implements GIS_element {
	private geom ge; 
	private element_metaData md;
	
	public element(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(line, geomIndexes);
		this.md= new element_metaData(line, metaIndexes);
		
	}
	public element(GIS_element other) {
		this.ge=new geom(((element)other).ge);
		this.md=new element_metaData(((element)other).md);
	}
	@Override
	public Geom_element getGeom() {
		return ge;
	}

	@Override
	public Meta_Data getData() {
		return md;
	}
	@Override
	public void translate(Point3D vec) {
		MyCoords MC = new MyCoords();
		this.ge.setP(MC.add(this.ge.getP(), vec));
	}


}
