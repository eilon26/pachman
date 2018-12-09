package GUI;
//scaling ����� ���� ����

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import GIS.*;
import Geom.*;
import algorithms.ShortestPathAlgo;
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


public class MyFrame extends JFrame implements MouseListener
{
	public int counter=0;
	public BufferedImage myImage;
	public GameBoard GB; 
	ShortestPathAlgo sol=null;
	public MyFrame() 
	{
		GB = new GameBoard();
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
		start.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	start(e);
	                }
	            }
	        );
		MenuItem gameBord2kml = new MenuItem("convert to xml");
		MenuItem addP = new MenuItem("add pachman");
		addP.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	addPachman(e);
	                }
	            }
	        );
		MenuItem addF = new MenuItem("add fruit");
		addF.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	addFruit(e);
	                }
	            }
	        );
		menuBar.add(menu);
		menu.add(load);
		menu.add(start);
		menu.add(gameBord2kml);
		menu.add(addP);
		menu.add(addF);
		this.setMenuBar(menuBar);
		//add the pic
		try {
			 myImage = ImageIO.read(new File("C:\\Users\\Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	int x = -1;
	int y = -1;
	char type = 'N'; 
	
	public void paint(Graphics g)
	{
		int rP = 25;
		int rF = 15;
		g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
		if(x!=-1 && y!=-1 && type!='N')
		{	
			

			
			if (type=='P') {
				x = x - (rP / 2);
				y = y - (rP / 2);
				GB.add(createPach(x,y));
			}
			else {
				x = x - (rF / 2);
				y = y - (rF / 2);
				GB.add(createFruit(x,y));
			}
			x=y=-1;
		}
		Iterator<GIS_element> IterElement = GB.iterator();
		while (IterElement.hasNext()) {
			map m = new map(this);
			BufferedImage img=null;
			GIS_element curr = IterElement.next();
			if (curr instanceof pachman) {
				Point3D curr_pixel_point = m.global2pixel(((geom)((pachman)curr).getGeom()).getP());
				
				try {
					img = ImageIO.read(new File("C:\\Users\\popeye.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img,(int)curr_pixel_point.x(),(int) curr_pixel_point.y(), rP, rP, null);
			}else {
				Point3D curr_pixel_point = m.global2pixel(((geom)((fruit)curr).getGeom()).getP());
				try {
					img = ImageIO.read(new File("C:\\Users\\spinach.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img,(int)curr_pixel_point.x(),(int) curr_pixel_point.y(), rF, rF,null);
			}
		}
		
		if (sol!=null) {
			Iterator<GIS_layer> IterPathes = sol.getPathes().iterator();
			Point3D prevPoint=null;
			map m = new map(this);
			while (IterPathes.hasNext()) {
				pachman_path currPath = (pachman_path)IterPathes.next();
				Iterator<Point3D> IterPoints = currPath.iterator_Points();
				if (IterPoints.hasNext()) {
					prevPoint = m.global2pixel(IterPoints.next());
				}
				while (IterPoints.hasNext()) {
					Point3D currPoint = m.global2pixel(IterPoints.next());
					g.drawLine((int)prevPoint.x(),(int) prevPoint.y(), (int)currPoint.x(), (int)currPoint.y());
					prevPoint = currPoint;
				}
				
				Point3D curr_pixel_point = m.global2pixel(((geom)currPath.getPach().getGeom()).getP());
				BufferedImage img=null;
				try {
					img = ImageIO.read(new File("C:\\Users\\popeye.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(img,(int)curr_pixel_point.x(),(int) curr_pixel_point.y(), rP, rP, null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		if (type !='N') {
			System.out.println("mouse Clicked");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			x = arg.getX();
			y = arg.getY();
			repaint();
		}
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
    		GB.addAll(m2g.getGB().getElement_Set());
    		GB.setMd((gameBoard_metaData) m2g.getGB().get_Meta_data());
       		repaint();
    	}
	}
	
	public void addPachman(ActionEvent e) {
		type='P';
	}
	
	public void addFruit(ActionEvent e) {
		type='F';
	}
	
	public void start(ActionEvent e) {
		this.sol = new ShortestPathAlgo(this.GB);
		sol.calculate();
		drawRealTime(sol);

	}
	public GameBoard getGB() {
		return GB;
	}

	private pachman createPach(int x,int y) {
		map m = new map(this);
		Point3D newPoint = m.pixel2global(new Point3D(x,y,0));
		pachman_metaData newMd = new pachman_metaData(next_pach_id(),1,1);//pachman_metaData by default
		return new pachman(newPoint,newMd);
	}
	
	private int next_pach_id() {
		if (GB.isEmpty()) return 0;
		else {
			int max=-1;
			Iterator<GIS_element> Iter = GB.iterator();
			while (Iter.hasNext()) {
				GIS_element curr = Iter.next();
				if (curr instanceof pachman) 
					max= Math.max(((pachman_metaData)((pachman)curr).getData()).getId(),max);
			}
			return max+1;
		}
	}
	
	private fruit createFruit(int x,int y) {
		map m = new map(this);
		Point3D newPoint = m.pixel2global(new Point3D(x,y,0));
		fruit_metaData newMd = new fruit_metaData(next_fruit_id(),1);//pachman_metaData by default
		return new fruit(newPoint,newMd);
	}
	
	private int next_fruit_id() {
		if (GB.isEmpty()) return 0;
		else {
			int max=-1;
			Iterator<GIS_element> Iter = GB.iterator();
			while (Iter.hasNext()) {
				GIS_element curr = Iter.next();
				if (curr instanceof fruit) 
					max= Math.max(((fruit_metaData)((fruit)curr).getData()).getId(),max);
			}
			return max+1;
		}
	}
	
	private void drawRealTime(ShortestPathAlgo sol) {
		Iterator<GIS_layer> IterS = sol.getPathes().iterator();
		while (IterS.hasNext()) {
			pachman_path curr = (pachman_path)IterS.next();
			new draw_thread(this,curr).start();
		}
	}
	public static void main(String[] args)
	{
		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	


}
