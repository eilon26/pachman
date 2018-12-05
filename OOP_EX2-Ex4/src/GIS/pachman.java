package GIS;

import Coords.MyCoords;
import GIS.gpsPoint.element_metaData;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;

public class pachman implements GIS_element {

	private geom ge;
	private pachman_metaData md;

	public pachman(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(line, geomIndexes);
		this.md= new pachman_metaData(line, metaIndexes);
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
