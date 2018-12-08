package GIS;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Coords.MyCoords;
import Geom.Point3D;

public class map {
	private BufferedImage ArielPic;
	private Point3D UpRightP;
	private Point3D DownLeftP;
	private JFrame frame;
	
	public map(JFrame frame) {
		this.frame = frame;
	try {
		this.ArielPic = ImageIO.read(new File("C:\\Users\\Ariel1.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.DownLeftP = new Point3D(32.101915,35.202457,0);
	this.UpRightP = new Point3D(32.105412,35.211975,0);
	
	}
	
	public Point3D global2pixel(Point3D Global) {
		double RatioGlobalX = Global.x()/getGlobalDiffX();
		double RatioGlobalY = (getGlobalDiffY()-Global.y())/getGlobalDiffY();
		double pixelX = frame.getWidth()*RatioGlobalX;
		double pixelY = frame.getHeight()*RatioGlobalY;
		return new Point3D(pixelX,pixelY,0);
	}
	public Point3D pixel2global(Point3D pixel) {
		double RatioPixelX = pixel.x()/frame.getWidth();
		double RatioPixelY = (frame.getHeight()-pixel.y())/frame.getHeight();
		double GlobalX = getGlobalDiffX()*RatioPixelX;
		double GlobalY = getGlobalDiffY()*RatioPixelY;
		return new Point3D(GlobalX,GlobalY,0);
	}
	
	public double[] pixelsDistanceAzimuth(Point3D pixel0, Point3D pixel1) {//in meter
		Point3D p0 = pixel2global(pixel0);
		Point3D p1 = pixel2global(pixel1);
		MyCoords x = new MyCoords();
		return x.azimuth_elevation_dist(p0, p1);
		
	}
	private double getGlobalDiffX() {
		return UpRightP.x()-DownLeftP.x();
	}
	private double getGlobalDiffY() {
		return UpRightP.y()-DownLeftP.y();
	}
}
