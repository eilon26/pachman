package GIS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import GUI.MyFrame;
import Geom.Point3D;

public class ariel_map extends map {


	public ariel_map(MyFrame frame) {
		super(frame);
		DownLeftP = new Point3D(35.202435,32.101940,0);
		UpRightP = new Point3D(35.212400,32.105400,0);
		try {
		this.BackgroundPic = ImageIO.read(new File("C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\GUI\\Ariel1.png"));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		frame.setMyImage(this.BackgroundPic);
	}

}