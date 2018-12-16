package GUI;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GIS.*;
import Geom.*;
import algorithms.*;
import File_format.*;

import java.awt.Color;
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
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFileChooser;
import java.io.File; 
import javax.imageio.ImageIO;


public class MyFrame extends JFrame implements MouseListener
{
	int S = 20;//init pachman speed
	int R = 1;//init pachman radius
	long wait_for_next_paint = 1500;
	public int counter=0;
	public BufferedImage myImage;
	private ariel_map m = null;
	private GameBoard GB; 
	private GameBoard GB_copy_csv = null; //copy for save as csv 
	private ShortestPathAlgo sol=null;
	private BufferedImage imgP = null;
	private BufferedImage imgF = null;
	
	public MyFrame() 
	{
		this.GB = new GameBoard();
		this.GB_copy_csv = new GameBoard();
		initGUI();		
		this.addMouseListener(this); 
		try {
			imgP = ImageIO.read(new File("C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\GUI\\popeye.png"));
		} catch (IOException e) {}
		try{ imgF = ImageIO.read(new File("C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\GUI\\spinach.jpg"));
		} catch (IOException ex) {
		}
	}
	
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("file");
		Menu add = new Menu("add");
		Menu start = new Menu("start");
		Menu clear = new Menu("clear");
		Menu set = new Menu("set defaults");
		
		MenuItem load = new MenuItem("load csv file");
		load.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	load(e);
	                }
	            }
	        );
		MenuItem csv = new MenuItem("save to csv file");
		csv.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	save_to_csv(e);
	                }
	            }
	        );
		MenuItem start_game = new MenuItem("start game");
		start.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	start(e);
	                }
	            }
	        );
		MenuItem gameBord2kml = new MenuItem("convert to kml");
		gameBord2kml.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	convert(e);
	                }
	            }
	        );
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
		MenuItem clear_board = new MenuItem("clear board");
		clear_board.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	clear(e);
	                }
	            }
	        );

		MenuItem set_radius = new MenuItem("pachman radius");
		set_radius.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	setRadius(e);
	                }
	            }
	        );
		MenuItem set_speed = new MenuItem("pachman speed");
		set_speed.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	setSpeed(e);
	                }
	            }
	        );
		menuBar.add(menu);
		menuBar.add(add);
		menuBar.add(start);
		menuBar.add(clear);
		menuBar.add(set);
		menu.add(load);
		menu.add(gameBord2kml);
		menu.add(csv);
		add.add(addP);
		add.add(addF);
		start.add(start_game);
		clear.add(clear_board);
		set.add(set_speed);
		set.add(set_radius);
		this.setMenuBar(menuBar);
		//add the pic
		this.m = new ariel_map(this);
		
	}

	int x = -1;
	int y = -1;
	char type = 'N'; 
	
	public synchronized void paint(Graphics g)
	{
		super.paint(g);
		int rP = 25;
		int rF = 15;
		g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
		if(x!=-1 && y!=-1 && type!='N')
		{
			
			if (type=='P') {
				GB.add(createPach(x,y));
				GB_copy_csv.add(createPach(x,y));
			}
			else {
				GB.add(createFruit(x,y));
				GB_copy_csv.add(createFruit(x,y));
			}
			x=y=-1;
		}
		Iterator<GIS_element> IterElement = GB.iterator();
		while (IterElement.hasNext()) {
			GIS_element curr = null;
			try {curr = IterElement.next();}
			catch (java.util.ConcurrentModificationException e) {}

			
			if (curr instanceof pachman) {
				Point3D curr_pixel_point = m.global2pixel(((geom)((pachman)curr).getGeom()).getP());
				g.drawImage(imgP,(int)(curr_pixel_point.x()- (rP / 2)),(int) (curr_pixel_point.y()- (rP / 2)), rP, rP, null);
			}else {
					Point3D curr_pixel_point = null;
					curr_pixel_point = m.global2pixel(((geom)((fruit)curr).getGeom()).getP());
				
					if (((fruit)curr).isAlive()) {
						g.drawImage(imgF,(int)(curr_pixel_point.x()- (rF / 2)),(int) (curr_pixel_point.y()- (rF / 2)), rF, rF,null);
					}
			}
		}
		
		if (sol!=null) {
			Iterator<GIS_layer> IterPathes = sol.getPathes().iterator();
			Point3D prevPoint=null;
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
				g.drawImage(imgP,(int)(curr_pixel_point.x()- (rP / 2)),(int) (curr_pixel_point.y()- (rP / 2)), rP, rP, null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		if (type !='N') {
			x = arg.getX();
			y = arg.getY();
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

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
		if (type=='S') {
			clear(e);
			type='N';
		}
    	JFileChooser fileChooser = new JFileChooser();
    	int result = fileChooser.showOpenDialog((JFrame)this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		csv2mat c2m = new csv2mat(selectedFile.getAbsolutePath());
    		mat2gameBoard m2g = new mat2gameBoard(c2m);
    		int old_size = GB.size();
    		GB.addAll(m2g.getGB().getElement_Set());
    		counter+=m2g.getGB().size();
    		changeFirstsID(old_size,GB);
    		GB_copy_csv.addAll(m2g.getGB().getElement_Set());
    		changeFirstsID(old_size,GB_copy_csv);
    		GB.setMd((gameBoard_metaData) m2g.getGB().get_Meta_data());
       		repaint();
    	}
	}
	
	
	private void changeFirstsID(int size,GameBoard GB) {
		Iterator<GIS_element> IterE = GB.iterator();
		int i=0;
		while (IterE.hasNext()&&(i<size)) {
			i++;
			GIS_element curr = IterE.next();
			if (curr instanceof pachman) {
				((pachman_metaData)((pachman)curr).getData()).setId(counter++);
			}else ((fruit_metaData)((fruit)curr).getData()).setId(counter++);
		}
	}
	public void addPachman(ActionEvent e) {
		if (type=='S') clear(e); 
		type='P';
	}
	
	public void addFruit(ActionEvent e) {
		if (type=='S') clear(e); 
		type='F';
	}
	
	public void start(ActionEvent e) {
		this.sol = new ShortestPathAlgo(this.GB);
		sol.calculate();
		drawRealTime(sol);
		StringBuilder content = new StringBuilder("time to eat all spinach: "+sol.getGeneraleTime()+" sec\n");
		content.append("total grade: "+sol.getGeneralGrade()+"\n");
		Iterator <GIS_layer> IterPath = sol.getPathes().iterator();
		while(IterPath.hasNext()) {
			pachman_path curr = (pachman_path)IterPath.next();
			content.append("popeye "+((pachman_metaData)curr.getPach().getData()).getId()+" grade is "+curr.getGrade()+" and his route time is "+curr.getTime()+"\n");
		}
		JOptionPane.showMessageDialog(null, content.toString());
		type = 'S';
	}
	
	public void convert(ActionEvent e) {
		if (this.sol==null) {
			this.sol = new ShortestPathAlgo(this.GB);
			sol.calculate();
		}
		JOptionPane.showMessageDialog(null, "file converted");
		JFileChooser fileChooser = new JFileChooser();
    	int result = fileChooser.showOpenDialog((JFrame)this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		new sol2kml(this.sol,selectedFile.getAbsolutePath());
    	}
	}
	public void clear(ActionEvent e) {
		this.counter=0;
		this.GB = new GameBoard();
		this.GB_copy_csv =  new GameBoard();
		this.sol = null;
		repaint();
	}
	
	public void save_to_csv(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
    	int result = fileChooser.showOpenDialog((JFrame)this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		new GB2csv(GB_copy_csv,selectedFile.getAbsolutePath());
    	}
    	JOptionPane.showMessageDialog(null, "file saved as csv!");
	}
	
	public void set_wait_repaint(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "enter sec between the shows:");
	    this.wait_for_next_paint = (long)(1000*Double.parseDouble(input));
	}
	public void setSpeed(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "enter popeye speed in meter/sec:");
	    this.S = Integer.parseInt(input);
	}
	public void setRadius(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "enter popeye radius in meter");
		this.R = Integer.parseInt(input);
	}
	public GameBoard getGB() {
		return GB;
	}

	private pachman createPach(int x,int y) {
		Point3D newPoint = m.pixel2global(new Point3D(x,y,0));
		pachman_metaData newMd = new pachman_metaData(counter++,S,R);//pachman_metaData by default
		return new pachman(newPoint,newMd);
	}
	
	private fruit createFruit(int x,int y) {
		Point3D newPoint = m.pixel2global(new Point3D(x,y,0));
		fruit_metaData newMd = new fruit_metaData(counter++,1);//pachman_metaData by default
		return new fruit(newPoint,newMd);
	}
	
	private void drawRealTime(ShortestPathAlgo sol) {
		Iterator<GIS_layer> IterS = sol.getPathes().iterator();
		while (IterS.hasNext()) {
			pachman_path curr = (pachman_path)IterS.next();
			new draw_thread(this,curr).start();
		}
	}
	
	public ShortestPathAlgo getSol() {
		return sol;
	}

	public void setMyImage(BufferedImage myImage) {
		this.myImage = myImage;
	}

	public static void main(String[] args)
	{
		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	


}