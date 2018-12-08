package GUI;
//scaling שינוי גודל חלון

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import GIS.*;
import Geom.*;
import File_format.*;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File; 
import javax.imageio.ImageIO;


public class user_panel extends JFrame implements MouseListener
{
	public int counter=0;
	public BufferedImage myImage;
	public ArrayList <Point> p = new ArrayList<Point>();
	public user_panel() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}
	
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem load = new MenuItem("load csv file");
		load.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	load(e);
	                }
	            }
	        );
		MenuItem start = new MenuItem("start game");
		MenuItem gameBord2kml = new MenuItem("convert to xml");
		menuBar.add(menu);
		menu.add(load);
		menu.add(start);
		menu.add(gameBord2kml);
		this.setMenuBar(menuBar);
		//add the pic
		try {
			 myImage = ImageIO.read(new File("C:\\Users\\danie\\git\\gps2\\OOP_EX2-Ex4\\src\\GUI\\Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//add resize listener
		
		
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{
		
		int r = 10;
		g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
		if(x!=-1 && y!=-1)
		{
			x = x - (r / 2);
			y = y - (r / 2);
			p.add(new Point(x,y));
			//g.fillOval(x, y, r, r);
		}
		Iterator <Point> IterP = p.iterator();
		while (IterP.hasNext()) {
			Point currP = IterP.next();
			g.fillOval(currP.getX(), currP.getY(), r, r);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void load(ActionEvent e) {
    	JFileChooser fileChooser = new JFileChooser();
    	//fileChooser.setCurrentDirectory(new File(System.getProperty("C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\GUI")));
    	int result = fileChooser.showOpenDialog((JFrame)this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		csv2mat c2m = new csv2mat(selectedFile.getAbsolutePath());
    		mat2gameBoard m2g = new mat2gameBoard(c2m);
    		solution sol = new solution(m2g.getGB());
    	}
	}

	public static void main(String[] args)
		{
		user_panel window = new user_panel();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}


}
