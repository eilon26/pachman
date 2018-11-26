package Geom;

public class geom {
	private Point3D p;
	
	public geom(String[] line,int[] geomIndexes) {
		double xp = Double.parseDouble(line[geomIndexes[0]]);
		double yp = Double.parseDouble(line[geomIndexes[1]]);
		double zp = Double.parseDouble(line[geomIndexes[2]]);
		p = new Point3D(xp,yp,zp);
	}
}
