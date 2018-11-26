package Coords;

import Geom.Point3D;


public class MyCoords implements coords_converter {
	public static final int earthR = 6371000;
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double LonNorm = Math.cos(Math.toRadians(gps.x()));
		double y1 = Math.toDegrees(Math.asin(local_vector_in_meter.y()/(earthR*LonNorm)))+gps.y();
		double x1 = Math.toDegrees(Math.asin(local_vector_in_meter.x()/(earthR)))+gps.x();
		double z1 = local_vector_in_meter.z()+gps.z();
		return new Point3D(x1,y1,z1);
}
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double LonNorm = Math.cos(Math.toRadians(gps0.x()));
		double meterX = earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double meterY = LonNorm*earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		return Math.sqrt((meterX*meterX)+(meterY*meterY));
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double meterY = earthR*Math.sin(Math.toRadians(gps1.y()-gps0.y()));
		double meterX = earthR*Math.sin(Math.toRadians(gps1.x()-gps0.x()));
		double LonNorm = Math.cos(Math.toRadians(gps0.x()));
		meterY *= LonNorm;
		double meterZ = gps1.z()-gps0.z();
		return new Point3D(meterX,meterY,meterZ);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] polarVec = new double[3];
		if (isValid_GPS_Point(gps0)&&isValid_GPS_Point(gps1)&&(distance3d(gps0,gps1)<=100000)) {
			Point3D vec = vector3D(gps0,gps1);
			polarVec[2] = distance3d(gps0,gps1);
			polarVec[0] = Math.toDegrees(Math.atan(Math.abs((vec.y())/(vec.x()))));
			if ((vec.x()<0)&&(vec.y()>0)) polarVec[0]=180-polarVec[0];
			if ((vec.y()<0)&&(vec.x()>0)) polarVec[0]=360-polarVec[0];
			if ((vec.x()<0)&&(vec.y()<0)) polarVec[0]=180+polarVec[0];
			if (polarVec[0]<0) polarVec[0]+=360;
			if (polarVec[0]>360) polarVec[0]-=360;
			
			polarVec[1] = Math.toDegrees(Math.asin(vec.z()/polarVec[2]));
			
			return polarVec;
		} else {
			try {
				throw new Exception("illigal value");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if (p.y()>=-180 && p.y()<=180) {
			if (p.x()>=-90 && p.x()<=90) {
				if (p.z()>=-450) {
					return true;
				}
			}
		}
		return false;
	}

}
