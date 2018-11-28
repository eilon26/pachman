package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class CoordsTest {

	/**
	 * this test tests if we get the correct transformed point.
	 * @param p - a new point
	 * @param x - a new MyCoords variable.
	 * @param vector - a new vector.
	 * @param expected - the expected transformed point.
	 */
	@Test
	void AddTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
		Point3D vector = new Point3D(337.698992,-359.249206,-20);
		Point3D expected = new Point3D(32.10635199999945,35.20522500000997,650.0);
		assertEquals(expected.toString(),x.add(p, vector).toString());
		
	}
	/**
	 * this test tests if we get the correct 3d vector.
	 * @param p - a new point
	 * @param p2 - another new point
	 * @param x - a new MyCoords variable.
	 */
	@Test
	void DistanceTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		Point3D p2 = new Point3D(32.106352,35.205225,650);
		MyCoords x = new MyCoords();
		assertEquals(493, (int)(x.distance3d(p, p2)));
	}
	
	/**
	 * this function checks if the creation of a vector using 2 gps points is made properly.
	 * @param p - a new point
	 * @param p2 - another new point
	 * @param x - a new MyCoords variable.
	 * @param expected - the expected vector.
	 * @param actual - the actual vector we get from the function.
	 */
	@Test
	void VectorTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
		Point3D p2 = new Point3D(32.106352,35.205225,650);
		Point3D expected = new Point3D(337.698992,-359.249206,-20);
		Point3D actual = x.vector3D(p, p2);
		double actualX= (int)(actual.x()*1000000);
		double actualY= (int)(actual.y()*1000000);
		double actualZ= (int)(actual.z()*1000000);
		actualX/=1000000;
		actualY/=1000000;
		actualZ/=1000000;
		assertEquals(expected.toString(), new Point3D(actualX,actualY,actualZ).toString());
	}

	/**
	 * this function checks if the point is a gps point.
	 * @param p - a new gps point.
	 * @param x - a new MyCoords variable.
	 */
	@Test
	void isValidTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
		assertTrue(x.isValid_GPS_Point(p));
	}
	
	/**
	 * this function checks if the azimuth function is made properly.
	 * @param p - a new point
	 * @param p2 - another new point
	 * @param x - a new MyCoords variable.
	 */
	@Test
	void azimuth_elevation_dist_Test() {
		MyCoords x = new MyCoords();
		double[] polarVec;
		Point3D p1 = new Point3D(33.112109,35.789906,989);
		Point3D p2 = new Point3D(33.115083,35.719633,788);
		
		polarVec = x.azimuth_elevation_dist(p1,p2);
		assertEquals(272,(int)(polarVec[0]));
		assertEquals(-1.7,(double)((int)(polarVec[1]*10))/10);
		assertEquals(655,(int)(polarVec[2]/10));
	}
	
	




}
