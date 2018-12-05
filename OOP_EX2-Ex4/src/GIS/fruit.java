package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;

public class fruit implements GIS_element {

	private geom ge;
	private fruit_metaData md;

public fruit(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(line, geomIndexes);
		this.md= new fruit_metaData(line, metaIndexes);
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
