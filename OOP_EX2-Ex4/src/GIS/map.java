package GIS;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Coords.MyCoords;
import GUI.MyFrame;
import Geom.Point3D;
/**
 * this class is resposible to create the background image of the GUI and for all the 
 * convertations between pixel to global and the opposite during the program is running
 * @author EILON
 *
 */
public abstract class map {
	protected BufferedImage BackgroundPic;
	protected Point3D UpRightP;
	protected Point3D DownLeftP;
	private JFrame frame;
	/**
	 * map constructor
	 * @param frame
	 */
	public map(MyFrame frame) {
		this.frame = frame;
	}
	/**
	 * get global point and convert it to pixel point
	 * @param Global a Point3D object that represent global point 
	 * @return pixel point
	 */
	public Point3D global2pixel(Point3D Global) {
		double RatioGlobalX = (Global.x()-DownLeftP.x())/getGlobalDiffX();
		double RatioGlobalY = (UpRightP.y()-Global.y())/getGlobalDiffY();
		double pixelX = frame.getWidth()*RatioGlobalX;
		double pixelY = frame.getHeight()*RatioGlobalY;
		return new Point3D(pixelX,pixelY,0);
	}
	/**
	 * get pixel point and convert it to global point
	 * @param pixel a Point3D object that represent pixel point 
	 * @return global point
	 */
	public Point3D pixel2global(Point3D pixel) {
		double RatioPixelX = pixel.x()/frame.getWidth();
		double RatioPixelY = (pixel.y())/frame.getHeight();
		double GlobalX = DownLeftP.x()+(getGlobalDiffX()*RatioPixelX);
		double GlobalY = UpRightP.y()-(getGlobalDiffY()*RatioPixelY);
		return new Point3D(GlobalX,GlobalY,0);
	}
	/**
	 * find the azimuth and with the distance between pixel0 to pixel1
	 * @param pixel0 Point3D that represent the first pixel
	 * @param pixel1 Point3D that represent the second pixel
	 * @return array with the azimuth and with the distance between pixel0 to pixel1
	 */
	public double[] pixelsDistanceAzimuth(Point3D pixel0, Point3D pixel1) {//in meter
		Point3D p0 = pixel2global(pixel0);
		Point3D p1 = pixel2global(pixel1);
		MyCoords x = new MyCoords();
		return x.azimuth_elevation_dist(p0, p1);
		
	}
	/** 
	 * @return the width of the pic in degree 
	 */
	public double getGlobalDiffX() {
		return UpRightP.x()-DownLeftP.x();
	}
	/** 
	 * @return the hight of the pic in degree 
	 */
	public double getGlobalDiffY() {
		return UpRightP.y()-DownLeftP.y();
	}
}
