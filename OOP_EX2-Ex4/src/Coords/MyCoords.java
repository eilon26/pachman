package Coords;

import Geom.Point3D;


public class MyCoords implements coords_converter {
	public static final int earthR = 6371000;
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double LonNorm = Math.cos(Math.toRadians(gps.y()));
		double y1 = Math.toDegrees(Math.asin(local_vector_in_meter.y()/earthR))+gps.y();
		double x1 = Math.toDegrees(Math.asin(local_vector_in_meter.x()/(earthR*LonNorm)))+gps.x();
		double z1 = local_vector_in_meter.z()+gps.z();
		return new Point3D(x1,y1,z1);
}
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double LonNorm = Math.cos(Math.toRadians(gps0.y()));
		double meterX = LonNorm*earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double meterY = earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		return Math.sqrt((meterX*meterX)+(meterY*meterY));
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double meterY = earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		double meterX = earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double LonNorm = Math.cos(Math.toRadians(gps0.y()));
		meterX *= LonNorm;
		double meterZ = gps1.z()-gps0.z();
		return new Point3D(meterX,meterY,meterZ);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double polarVec[] = new double[3];
		double dY = gps1.y()-gps0.y();
		double dX = gps1.x()-gps0.x();
		double dZ = gps1.z()-gps0.z();
		polarVec[0] = Math.atan(Math.abs((dY)/(dX)));
		if ((dX<0)&&(dY<0)) polarVec[0]+=180; 
		if ((dX<0)&&(dY>0)) polarVec[0]=180-polarVec[0]; 
		if ((dX>0)&&(dY<0)) polarVec[0]=360-polarVec[0];
		polarVec[1] = Math.acos(dZ);
		if (polarVec[1]>180) polarVec[1]=360-polarVec[1];
		polarVec[2] = distance3d(gps0,gps1);
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if (p.x()>=-180 && p.x()<=180) {
			if (p.y()>=-90 && p.y()<=90) {
				if (p.z()>=-450) {
					return true;
				}
			}
		}
		return false;
	}

}
