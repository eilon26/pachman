package GIS;

import GIS.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Layer extends ArrayList<GIS_element> implements GIS_layer  {
	private ArrayList<GIS_element> element_set;
	private metaData md;
	
	public Layer(ArrayList<String[]> g){
		element_set = new ArrayList<GIS_element>();
		int[] geomIndexes = geomIndexes(g.get(1));
		int[] metaIndexes = metaIndexes(g.get(1));
		for (int i=2;i<g.size();i++) {
			String[] line = g.get(i);
			element PointElement = new element(line,geomIndexes,metaIndexes);
			element_set.add(PointElement); 
		}
		
		String StartFinnishPlace = "start at: "+((metaData)(element_set.get(0).getData())).getName()+"\n finnish at: "+((metaData)(element_set.get(element_set.size()-1).
		long RecordTime = ((metaData)(element_set.get(0).getData())).getUTC();
		
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
	public Meta_data get_Meta_data() {
		return md;
	}
}
