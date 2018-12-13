package GIS;

import GIS.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import File_format.csv2mat;
/**
 * this class handles the arraylists and put them under one layer.
 * this class implements gis_layer.
 * @author Daniel Ventura and Eilon tsadok
 *
 */
public class GameBoard implements GIS_layer  {
	private ArrayList<GIS_element> element_set;
	private gameBoard_metaData md;
	/**
	 * this class gets an array list and creates a new element (point 
	 * element) and an element set to contain all of the elements.
	 * @param g - the array list.
	 */
	public GameBoard() {
		this.element_set=new ArrayList<GIS_element>();
		this.md=null;
	}
	
	public GameBoard(GameBoard other) {
		this.element_set = new ArrayList<GIS_element>();
		Iterator<GIS_element>  IterE = other.iterator();
		while (IterE.hasNext()) {
			GIS_element curr = IterE.next();
			if (curr instanceof pachman)
				this.element_set.add(new pachman((pachman)curr));
			else this.element_set.add(new fruit((fruit)curr));
		}
	}
	public void setMd(gameBoard_metaData md) {
		this.md = md;
	}

	public GameBoard(csv2mat c2m){
		element_set = new ArrayList<GIS_element>();
		int[] geomIndexes = geomIndexes(c2m.getG().get(0));
		int[] metaIndexes = metaIndexes(c2m.getG().get(0));
		for (int i=1;i<c2m.getG().size();i++) {
			String[] line = c2m.getG().get(i);
			if (line[metaIndexes[0]].contains("P")) {
				pachman pach = new pachman(line,geomIndexes,metaIndexes);
				element_set.add(pach);
			}
			else {
				fruit fru = new fruit(line,geomIndexes,metaIndexes);
				element_set.add(fru); 
			}
			
		}
		long convert_time = new Date().getTime();
		this.md = new gameBoard_metaData(c2m.getFile().getName(),convert_time);
	}

	public ArrayList<GIS_element> getElement_Set(){
		return element_set;
	}
	/**
	 * this function finds the indexes of the gps point.
	 * @param line - the current line.
	 * @return the indexes of x,y and z in an int array.
	 */
	public int[] geomIndexes(String[] line) {
		int[] GI = new int[3];
		for(int i=0;i<line.length;i++) {
			if (line[i].contains("Lat")) {
				GI[0]=i;
			}
			else if (line[i].contains("Lon")) {
				GI[1]=i;
			}
			else if (line[i].contains("Alt")) {
				GI[2]=i;
			}
		}
		return GI;
	}
	/**
	 * this function finds the indexes of the rest of the subjects.
	 * @param line - the current line
	 * @return an int array with the indexes of each subject.
	 */
	public int[] metaIndexes(String[] line) {
		int[] MI = new int[4];
		for(int i=0;i<line.length;i++) {
		if (line[i].contains("Type")) {
				MI[0]=i;
			}
		else if (line[i].contains("id")) {
				MI[1]=i;
			}
			else if (line[i].contains("Speed")) {
			    MI[2]=i;
			}
			else if (line[i].contains("Radius")) {
			    MI[3]=i;
			}
		}
		return MI;
	}
	/**
	 * @return ture if add a specific element from the set.
	 */
	@Override
	public synchronized boolean add(GIS_element arg0) {
		return element_set.add((arg0));
	}
	/**
	 * @return true if add a set of elements to the current set.
	 */
	@Override
	public synchronized boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return element_set.addAll(arg0);
	}
	/**
	 * clear the set
	 */
	@Override
	public void clear() {
		element_set.clear();
		
	}
	/**
	 * @return true if the object exists in the set.
	 */
	@Override
	public boolean contains(Object arg0) {
		return element_set.contains(arg0);//add element.equals()
	}
	/**
	 * @return true if the collection is contained in the set.
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return element_set.containsAll(arg0);
	}
	/**
	 * @return true if the set is empty.
	 */
	@Override
	public boolean isEmpty() {
		return element_set.isEmpty();
	}
	/**
	 * @return the iterator.
	 */
	@Override
	public synchronized  Iterator<GIS_element> iterator() {
		return element_set.iterator();
	}
	/**
	 * @return true if we could remove the object arg0.
	 */
	@Override
	public synchronized boolean remove(Object arg0) {
		return element_set.remove(arg0);
	}
	/**
	 * @return true if we could remove collecti
	 * on of objects arg0.
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return element_set.removeAll(arg0);
	}
	/**
	 * @return true if its retained.
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return element_set.retainAll(arg0);
	}
	/**
	 * @return the size of the set.
	 */
	@Override
	public int size() {
		return element_set.size();
	}
	/**
	 * @return an object array of the set.
	 */
	@Override
	public Object[] toArray() {
		return element_set.toArray();
	}
	/**
	 * @return a T array of the set. 
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return element_set.toArray(arg0);
	}
	/**
	 * @return the meta data. 
	 */
	@Override
	public Meta_Data get_Meta_data() {
		return md;
	}
}
