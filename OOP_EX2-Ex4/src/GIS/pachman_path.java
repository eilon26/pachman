package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.*;


public class pachman_path implements GIS_layer{
	pachman pach;
	int grade;
	private ArrayList<GIS_element> my_fruit;
	private ArrayList<Point3D> draw_point;
	Point3D start_point;
	double time;
	private pachman_metaData md;
	
	public pachman_path(pachman pach) {
		this.pach=pach;
		this.my_fruit = new ArrayList<GIS_element>();
		this.draw_point = new ArrayList<Point3D>();
		this.start_point = ((geom)pach.getGeom()).getP();
		this.md = (pachman_metaData) pach.getData();
		this.grade = 0;
		this.time=0;
	}
	public double path_length() {
		double sum =0;
		MyCoords x = new MyCoords();
		Point3D prevP = ((geom)pach.getGeom()).getP();
		Iterator<GIS_element> IterF = my_fruit.iterator();
		while (IterF.hasNext()) {
			fruit currF = (fruit) IterF.next();
			Point3D currP = ((geom)currF.getGeom()).getP();
			sum+=x.distance3d(prevP, currP);
			prevP=currP;
		}
		return sum;
	}
	
	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public pachman getPach() {
		return pach;
	}

	public ArrayList<GIS_element> getMy_fruit() {
		return my_fruit;
	}

	public Point3D getStart_point() {
		return start_point;
	}
	
	@Override
	public boolean add(GIS_element arg0) {
		return my_fruit.add(arg0);
	}
	
	public boolean add_to_draw_point(Point3D arg0) {
		return draw_point.add(arg0);
	}
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return my_fruit.addAll(arg0);
	}
	@Override
	public void clear() {
		my_fruit.clear();
		
	}
	@Override
	public boolean contains(Object arg0) {
		return my_fruit.contains(arg0);	
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return  my_fruit.containsAll(arg0);
	}
	@Override
	public boolean isEmpty() {
		return my_fruit.isEmpty();
	}
	@Override
	public Iterator<GIS_element> iterator() {
		return my_fruit.iterator();
	}
	public Iterator<Point3D> iterator_Points() {
		return draw_point.iterator();
	}
	public ArrayList<Point3D> getDraw_point() {
		return draw_point;
	}


	public void setDraw_point(ArrayList<Point3D> draw_point) {
		this.draw_point = draw_point;
	}


	public void setPachGeom(Point3D p) {
		this.pach.setGe(new geom(p));
	}


	@Override
	public boolean remove(Object arg0) {
		return my_fruit.remove(arg0);
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return my_fruit.removeAll(arg0);
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return my_fruit.retainAll(arg0);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return my_fruit.size();
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return my_fruit.toArray();
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return my_fruit.toArray(arg0);
	}
	@Override
	public Meta_Data get_Meta_data() {
		// TODO Auto-generated method stub
		return md;
	}
}
