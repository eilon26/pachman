package GIS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import GUI.MyFrame;
import Geom.Point3D;
/**
 *this class is extends from map and responsible to represent the map of Ariel
 * @author EILON
 *
 */
public class ariel_map extends map {

/**
 * ariel_map Constructor that get frame and initialize the empty fields in map class by "Ariel" pic
 * @param frame Myframe object
 */
	public ariel_map(MyFrame frame) {
		super(frame);
		DownLeftP = new Point3D(35.202435,32.101940,0);
		UpRightP = new Point3D(35.212400,32.105400,0);
		try {
		this.BackgroundPic = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		frame.setMyImage(this.BackgroundPic);
	}

}
