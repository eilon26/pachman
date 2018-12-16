package GIS;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import GIS.*;
import GUI.MyFrame;
import Geom.Point3D;

class test_map {
	static MyFrame window;
	static ariel_map m;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		window = new MyFrame();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m = new ariel_map(window);
	}

	@Test
	void test_global2pixel() {
		assertEquals(new Point3D(0.0,window.getHeight(),0.0).toString(),m.global2pixel(new Point3D(35.202435,32.101940,0)).toString());
	}
	@Test
	void test_pixel2global() {
		assertEquals(new Point3D(35.202435,32.101940,0).toString(),m.pixel2global(new Point3D(0.0,window.getHeight(),0.0)).toString());
	}
	@Test
	void test_pixelsDistanceAzimuth() {
		MyCoords x = new MyCoords();
		Point3D a =new Point3D(35.202435,32.101940,0);
		Point3D b = new Point3D(35.212400,32.105400,0);
		double[] AED = x.azimuth_elevation_dist(a, b);
		double[] pixelsDistanceAzimuth = m.pixelsDistanceAzimuth(m.global2pixel(a),m.global2pixel(b));
		assertEquals(AED[0],pixelsDistanceAzimuth[0]);
		assertEquals(AED[1],pixelsDistanceAzimuth[1]);
	}

}
