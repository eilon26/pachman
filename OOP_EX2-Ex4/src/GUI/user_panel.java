package GUI;
//scaling שינוי גודל חלון

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class user_panel extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	
	public user_panel() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}
	
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("menu item 1");
		MenuItem item2 = new MenuItem("menu item 2");
		
		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		this.setMenuBar(menuBar);
		
		try {
			 myImage = ImageIO.read(new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0, this);
	
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
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

	public static void main(String[] args)
		{
		user_panel window = new user_panel();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}


}
