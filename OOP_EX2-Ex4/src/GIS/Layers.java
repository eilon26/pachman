package GIS;

import java.util.ArrayList;

public class Layers {
	//private ArrayList<String[]> g;
	private ArrayList<element> e;
	
	public Layers(ArrayList<String[]> g){
		e = new ArrayList<element>();
		int[] geomIndexes = geomIndexes(g.get(1));
		int[] metaIndexes = metaIndexes(g.get(1));
		for (int i=2;i<g.size();i++) {
			String[] line = g.get(i);
			element PointElement = new element(line,geomIndexes,metaIndexes);
			e.add(PointElement); 
		}
		
		
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
		int[] MI = new int[3];
		for(int i=0;i<line.length;i++) {
			if (line[i].contains("FirstSeen")) {
				MI[0]=i;
			}
			else if (line[i].contains("RSSI")) {
				MI[1]=i;
			}
			else if (line[i].contains("ssid")) {
			    MI[2]=i;
			}
		}
		return MI;
	}
}
