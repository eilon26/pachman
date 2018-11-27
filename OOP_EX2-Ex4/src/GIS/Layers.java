package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Layers implements GIS_layer  {
	private metaData md;
	private ArrayList<GIS_element> e;
	
	public Layers(ArrayList<String[]> g){
		e = new ArrayList<GIS_element>();
		int[] geomIndexes = geomIndexes(g.get(1));
		int[] metaIndexes = metaIndexes(g.get(1));
		for (int i=2;i<g.size();i++) {
			String[] line = g.get(i);
			element PointElement = new element(line,geomIndexes,metaIndexes);
			e.add(PointElement); 
		}
		
		
	}
	public ArrayList<GIS_element> getEl(){
		return e;
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
		return e.add((element)(arg0));
	}
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return e.add((element) arg0);
	}
	@Override
	public void clear() {
		this.e = new ArrayList<GIS_element>();
		
	}
	@Override
	public boolean contains(Object arg0) {
		return e.contains((element)arg0);//add element.equals()
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return e.containsAll(arg0);
	}
	@Override
	public boolean isEmpty() {
		if(e.size()>0) return false;
		return true;
	}
	@Override
	public Iterator<GIS_element> iterator() {
		return e.iterator();
	}
	@Override
	public boolean remove(Object arg0) {
		
		return e.remove((element)arg0);
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		
		return e.removeAll(arg0);
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return e.retainAll(arg0);
	}
	@Override
	public int size() {
		return e.size();
	}
	@Override
	public Object[] toArray() {
		return e.toArray();
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		return e.toArray(arg0);
	}
	@Override
	public Meta_data get_Meta_data() {
		return md;
	}
}
