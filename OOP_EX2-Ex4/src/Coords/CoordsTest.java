package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class CoordsTest {

	@Test
	void AddTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
	}
	@Test
	void DistanceTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
	}
	@Test
	void VectorTest() {
		Point3D p = new Point3D(35.209039,32.103315,670);
		MyCoords x = new MyCoords();
		Point3D p2 = new Point3D(35.205225,32.106352,650);
		Point3D expected = new Point3D(-359.249206,337.698992,-20);
		Point3D actual = x.vector3D(p, p2);
		double actualX= (int)(actual.x()*1000000);
		double actualY= (int)(actual.y()*1000000);
		double actualZ= (int)(actual.z()*1000000);
		actualX/=1000000;
		actualY/=1000000;
		actualZ/=1000000;
		assertEquals(expected.toString(), new Point3D(actualX,actualY,actualZ).toString());
	}
	@Test
	void AzimuthTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
	}
	@Test
	void isValidTest() {
		Point3D p = new Point3D(32.103315,35.209039,670);
		MyCoords x = new MyCoords();
		assertTrue(x.isValid_GPS_Point(p));
	}





}