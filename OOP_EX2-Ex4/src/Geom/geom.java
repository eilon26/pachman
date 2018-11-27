package Geom;

import Coords.MyCoords;

public class geom implements Geom_element{
	private Point3D p;
	private static final int earthR = 6371000;
	
	public geom(String[] line,int[] geomIndexes) {
		double xp = Double.parseDouble(line[geomIndexes[0]]);
		double yp = Double.parseDouble(line[geomIndexes[1]]);
		double zp = Double.parseDouble(line[geomIndexes[2]]);
		p = new Point3D(xp,yp,zp);
	}
	
	public geom(geom ge) {
		this.p=new Point3D(ge.p);
	}

	@Override
	public double distance3D(Point3D p) {
		MyCoords x = new MyCoords();
		return x.distance3d(this.p,p);
	}

	@Override
	public double distance2D(Point3D p) {
		double LonNorm = Math.cos(Math.toRadians(p.x()));
		double meterX = earthR*Math.sin(Math.toRadians(this.p.x()-p.x()));
		double meterY = LonNorm*earthR*Math.sin(Math.toRadians(this.p.y()-p.y()));
		double Dis2D = Math.sqrt((meterX*meterX)+(meterY*meterY));
		if (Dis2D>100000)
			return Double.NaN;
		return Dis2D;
	}
	public Point3D getP() {
		return p;
	}
}