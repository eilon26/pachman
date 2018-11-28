package GIS;

import GIS.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Layer implements GIS_layer  {
	private ArrayList<GIS_element> element_set;
	private Layer_metaData md;
	
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
		long First_RecordTime = ((element_metaData)(element_set.get(0).getData())).getUTC();
		this.md = new Layer_metaData(StartFinnishPlace,((element_metaData)(element_set.get(0).getData())).getTime(),First_RecordTime);
	}
	public ArrayList<GIS_element> getElement_Set(){
		return element_set;
	}
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
	@Override
	public boolean add(GIS_element arg0) {
		return element_set.add((element)(arg0));
	}
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return element_set.add((element) arg0);
	}
	@Override
	public void clear() {
		element_set.clear();
		
	}
	@Override
	public boolean contains(Object arg0) {
		return element_set.contains((element)arg0);//add element.equals()
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
	public Iterator<GIS_element> iterator() {
		return element_set.iterator();
	}
	@Override
	public boolean remove(Object arg0) {
		return element_set.remove((element)arg0);
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
