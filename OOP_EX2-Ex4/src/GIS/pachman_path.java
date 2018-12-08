package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Geom.*;


public class pachman_path implements GIS_layer{
	pachman pach;
	int grade;
	private ArrayList<GIS_element> my_fruit;
	Point3D start_point;
	private pachman_metaData md;
	
	public pachman_path(pachman pach) {
		this.pach=pach;
		this.my_fruit = new ArrayList<GIS_element>();
		this.start_point = ((geom)pach.getGeom()).getP();
		this.md = (pachman_metaData) pach.getData();
		this.grade = 0;
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
