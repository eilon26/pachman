package GIS;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Coords.MyCoords;
import GUI.MyFrame;
import Geom.Point3D;

public class map {
	private BufferedImage ArielPic;
	private Point3D UpRightP;
	private Point3D DownLeftP;
	private JFrame frame;
	
	public map(JFrame frame) {
		this.frame = frame;
	try {
		this.ArielPic = ImageIO.read(new File("C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\GUI\\Ariel1.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.DownLeftP = new Point3D(32.101915,35.202457,0);
	this.UpRightP = new Point3D(32.105412,35.211975,0);
	
	}
	
	public Point3D global2pixel(Point3D Global) {
		double RatioGlobalX = (Global.x()-DownLeftP.x())/getGlobalDiffX();
		double RatioGlobalY = (Global.y()-DownLeftP.y())/getGlobalDiffY();
		double pixelX = frame.getWidth()*RatioGlobalX;
		double pixelY = frame.getHeight()*RatioGlobalY;
		return new Point3D(pixelX,frame.getHeight()-pixelY,0);
	}
	public Point3D pixel2global(Point3D pixel) {
		double RatioPixelX = pixel.x()/frame.getWidth();
		double RatioPixelY = (frame.getHeight()-pixel.y())/frame.getHeight();
		double GlobalX = DownLeftP.x()+(getGlobalDiffX()*RatioPixelX);
		double GlobalY = DownLeftP.y()+(getGlobalDiffY()*RatioPixelY);
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
	
//	public static void main(String[] args) {
//		GameBoard GB= new GameBoard();
//		user_panel window = new user_panel();
//		window.setVisible(true);
//		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	JFileChooser fileChooser = new JFileChooser();
//    	//fileChooser.setCurrentDirectory(new File(System.getProperty("C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\GUI")));
//    	int result = fileChooser.showOpenDialog((JFrame)window);
//    	if (result == JFileChooser.APPROVE_OPTION) {
//    		File selectedFile = fileChooser.getSelectedFile();
//    		csv2mat c2m = new csv2mat(selectedFile.getAbsolutePath());
//    		mat2gameBoard m2g = new mat2gameBoard(c2m);
//    		GB.addAll(m2g.getGB().getElement_Set());
//    		GB.setMd((gameBoard_metaData) m2g.getGB().get_Meta_data());
//    		GB.getElement_Set().get(2)
//    	}
		
		
//		user_panel us = new user_panel();
//		us.setVisible(true);
//		us.setSize(us.myImage.getWidth(),us.myImage.getHeight());
//		//Point3D x = new Point3D(32.10451344,35.21019738,0);
//		Point3D x = new Point3D(1029.8649814117127,119.90250472826938,0);
//		map m = new map(us);
//		System.out.println(m.pixel2global(x));
//	}
}
