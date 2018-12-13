package algorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;

import GIS.*;
import Geom.geom;

public class GB2csv {
		
		public GB2csv(GameBoard GB,String output) {
		    String csvStart = "Type,id,Lat,Lon,Alt,Speed/Weight,Radius,3,21\r\n";
		    StringBuilder content = new StringBuilder(csvStart);
		    try{
		        FileWriter fw = new FileWriter(output);
		        BufferedWriter bw = new BufferedWriter(fw);
		        Iterator<GIS_element> iterE =GB.iterator();
		        while (iterE.hasNext()) {
		        	String csvElement;
		        	GIS_element curr = iterE.next();
		        	if (curr instanceof pachman) {
		        		curr = (pachman) curr;
		        		csvElement = "P,"+((pachman_metaData)curr.getData()).getId()+","+((geom)curr.getGeom()).getP().y()+","+((geom)curr.getGeom()).getP().x()+","+((geom)curr.getGeom()).getP().z()+","+((pachman_metaData)curr.getData()).getSpeed()+","+((pachman_metaData)curr.getData()).getRadius()+"\r\n";
		        	}
		        	else {
		        		curr = (fruit) curr;
		        		csvElement = "F,"+((fruit_metaData)curr.getData()).getId()+","+((geom)curr.getGeom()).getP().y()+","+((geom)curr.getGeom()).getP().x()+","+((geom)curr.getGeom()).getP().z()+","+((fruit_metaData)curr.getData()).getweight()+"\r\n";
		        	}
		            content.append(csvElement);
		        }
		        bw.write(content.toString());
		        bw.close();
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	

}
