package GIS;

import GIS.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
/**
 * this class handles the arraylists and put them under one layer.
 * this class implements gis_layer.
 * @author Daniel Ventura
 *
 */
public class Layer implements GIS_layer  {
	private ArrayList<GIS_element> element_set;
	private Layer_metaData md;
	/**
	 * this class gets an array list and creates a new element (point 
	 * element) and an element set to contain all of the elements.
	 * @param g - the array list.
	 */
	public Layer(ArrayList<String[]> g){
		element_set = new ArrayList<GIS_element>();
		int[] geomIndexes = geomIndexes(g.get(1));
		int[] metaIndexes = metaIndexes(g.get(1));
		for (int i=2;i<g.size();i++) {
			String[] line = g.get(i);
			element PointElement = new element(line,geomIndexes,metaIndexes);
			element_set.add(PointElement); 
		}
		String strat = ((element_metaData)(element_set.get(0).getData())).getName();
		String finnish = ((element_metaData)(element_set.get(element_set.size()-1).getData())).getName();
		String StartFinnishPlace = "start location: "+strat+"\n finnish location: "+finnish+"\n";
		long convert_time = new Date().getTime();
		this.md = new Layer_metaData(StartFinnishPlace,((element_metaData)(element_set.get(0).getData())).getTime(),convert_time);
	}
	/**
	 * @return the layer.
	 */
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
			if (line[i].contains("Latitude")) {
				GI[0]=i;
			}
			else if (line[i].contains("Longitude")) {
				GI[1]=i;
			}
			else if (line[i].contains("Altitude")) {
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
		int[] MI = new int[8];
		for(int i=0;i<line.length;i++) {
			if (line[i].contains("FirstSeen")) {
				MI[0]=i;
			}
			else if (line[i].contains("RSSI")) {
				MI[1]=i;
			}
			else if (line[i].contains("SSID")) {
			    MI[2]=i;
			}
			else if (line[i].contains("MAC")) {
			    MI[3]=i;
			}
			else if (line[i].contains("AuthMode")) {
			    MI[4]=i;
			}
			else if (line[i].contains("Channel")) {
			    MI[5]=i;
			}
			else if (line[i].contains("AccuracyMeters")) {
			    MI[6]=i;
			}
			else if (line[i].contains("Type")) {
			    MI[7]=i;
			}
		}
		return MI;
	}
	/**
	 * @return ture if add a specific element from the set.
	 */
	@Override
	public boolean add(GIS_element arg0) {
		return element_set.add((element)(arg0));
	}
	/**
	 * @return true if add a set of elements to the current set.
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return element_set.add((element) arg0);
	}
	/**
	 * @return true if the set is not cleared.
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
		return element_set.contains((element)arg0);//add element.equals()
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
	public Iterator<GIS_element> iterator() {
		return element_set.iterator();
	}
	/**
	 * @return true if we could remove the object arg0.
	 */
	@Override
	public boolean remove(Object arg0) {
		return element_set.remove((element)arg0);
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
