package Coords;

import Geom.Point3D;


public class MyCoords implements coords_converter {
	public static final int earthR = 6371000;
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double x1 = Math.toDegrees(Math.asin(local_vector_in_meter.x()/earthR))+gps.x();
		double y1 = Math.toDegrees(Math.asin(local_vector_in_meter.y()/(earthR*gps.x())))+gps.y();
		double z1 = local_vector_in_meter.z()+gps.z();
		return new Point3D(x1,y1,z1);
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double meterX = earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double LonNorm = Math.cos(Math.toRadians(gps0.x()));
		double meterY = LonNorm*earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		return Math.sqrt((meterX*meterX)+(meterY*meterY));
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		return null;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		return false;
	}

}
