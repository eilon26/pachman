package Geom;

public class geom implements Geom_element{


	private Point3D p;
	
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distance2D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}
	public Point3D getP() {
		return p;
	}
}
