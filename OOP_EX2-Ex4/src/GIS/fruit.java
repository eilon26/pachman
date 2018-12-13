package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geom;

public class fruit implements GIS_element {
	private geom ge;
	private fruit_metaData md;
	private boolean alive;

public fruit(String[] line,int[] geomIndexes,int[] metaIndexes) {
		this.ge = new geom(line, geomIndexes);
		this.md= new fruit_metaData(line, metaIndexes);
		this.alive =true;
}
public fruit(Point3D p,fruit_metaData md) {
	this.ge = new geom(p);
	this.md = md;
	this.alive =true;
}
public fruit (fruit other) {
	this.ge= new geom((geom) other.getGeom());
	this.md = new fruit_metaData((fruit_metaData) other.getData());
	this.alive =true;
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
public boolean isAlive() {
	return this.alive;
}
public void setAlive(boolean alive) {
	this.alive = alive;
}
}
