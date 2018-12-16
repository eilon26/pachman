package GIS;

import GIS.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import File_format.csv2mat;
/**
 * the class is contain all the fruit and the pachman objects in the game
 * @author Daniel Ventura and Eilon tsadok
 *
 */
public class GameBoard implements GIS_layer  {
	private ArrayList<GIS_element> element_set;
	private gameBoard_metaData md;
	/**
	 * the constructor
	 */
	public GameBoard() {
		this.element_set=new ArrayList<GIS_element>();
		this.md=null;
	}
	/**
	 * the copy constactor
	 * @param other GameBoard parameter
	 */
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

/**
 * constractor that buid GameBoard from mat(after taking the csv file and convert it to mat)
 * @param c2m csv2mat parameter
 */
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
	/**
	 * set md
	 * @param md gameBoard_metaData parameter
	 */
	public void setMd(gameBoard_metaData md) {
		this.md = md;
	}
	/**
	 * 
	 * @return element_set
	 */
	public ArrayList<GIS_element> getElement_Set(){
		return element_set;
	}
	/**
	 * this function finds the indexes of the global point.
	 * @param line the current line
	 * @return  the indexes of x,y and z in an int array.
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

	@Override
	public synchronized boolean add(GIS_element arg0) {
		return element_set.add((arg0));
	}

	@Override
	public synchronized boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return element_set.addAll(arg0);
	}

	@Override
	public void clear() {
		element_set.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return element_set.contains(arg0);//add element.equals()
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return element_set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return element_set.isEmpty();
	}

	@Override
	public synchronized  Iterator<GIS_element> iterator() {
		return element_set.iterator();
	}

	@Override
	public synchronized boolean remove(Object arg0) {
		return element_set.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return element_set.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return element_set.retainAll(arg0);
	}

	@Override
	public int size() {
		return element_set.size();
	}

	@Override
	public Object[] toArray() {
		return element_set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return element_set.toArray(arg0);
	}

	@Override
	public Meta_Data get_Meta_data() {
		return md;
	}
}
