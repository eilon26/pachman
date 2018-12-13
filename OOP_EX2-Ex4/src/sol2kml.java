

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;

import GIS.*;
import Geom.geom;

public class sol2kml {
	
	public sol2kml(ShortestPathAlgo sol,String output) {
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
	    		"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"popeye\"><IconStyle><Icon><href>https://cdn.iconscout.com/icon/premium/png-256-thumb/popeye-4-611225.png</href></Icon><hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style><Style id=\"spinach\"><IconStyle><Icon><href>https://clipartix.com/wp-content/uploads/2018/03/spinach-clipart-2018-24.png</href></Icon><hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style><Style id=\"no more\"><IconStyle><Icon><href>https://www.iconspng.com/images/nosign-x/nosign-x.jpg</href></Icon><hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style>\r\n";
	    
	    StringBuilder content = new StringBuilder(kmlstart);
	    String kmlend = "</Document></kml>";
	    try{
	        FileWriter fw = new FileWriter(output);
	        BufferedWriter bw = new BufferedWriter(fw);
	        Iterator<GIS_layer> iterPath =sol.getPathes().iterator();
	        while (iterPath.hasNext()) {
	            pachman_path curr_path = (pachman_path) iterPath.next();
	            Iterator<LocByTime> iterLocByTime = ((pachman_metaData)curr_path.getPach().getData()).getLoc_by_time().iterator();
	            while (iterLocByTime.hasNext()) {
	            	LocByTime curr_LocByTime = iterLocByTime.next(); 
	            	String kmlelement =
	            		"<Placemark>\r\n" + 
	            			  "<description><![CDATA[id: <b>"+((pachman_metaData)curr_path.getPach().getData()).getId()+"</b><br/>speed: <b>"+((pachman_metaData)curr_path.getPach().getData()).getSpeed()+"</b><br/>radius: <b>"+((pachman_metaData)curr_path.getPach().getData()).getRadius()+"</b>]]></description>"+
	            		      "<TimeStamp>\r\n" + 
	            		      		"<when>"+curr_LocByTime.getTime()+"</when>\r\n" + 
	            		      "</TimeStamp>\r\n" + 
	            		      "<styleUrl>#popeye</styleUrl>\r\n" + 
	            		      "<Point>\r\n" + 
	            		      		"<coordinates>"+curr_LocByTime.getLocation().x()+","+curr_LocByTime.getLocation().y()+","+curr_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
	            		      "</Point>\r\n" + 
	            		"</Placemark>";
	            	content.append(kmlelement);
	            }
	            Iterator<GIS_element> iterFruit = curr_path.iterator();
	            while (iterFruit.hasNext()) {
	            	fruit curr_fruit = (fruit)iterFruit.next();
	            	LocByTime fruit_LocByTime = ((fruit_metaData)curr_fruit.getData()).getLoc_by_time()[0];
	            	String kmlelement =
		            		"<Placemark>\r\n" + 
		            			  "<description><![CDATA[id: <b>"+((fruit_metaData)curr_fruit.getData()).getId()+"</b><br/>weight: <b>"+((fruit_metaData)curr_fruit.getData()).getweight()+"</b>]]></description>"+
		            		      "<TimeStamp>\r\n" + 
		            		      		"<when>"+fruit_LocByTime.getTime()+"</when>\r\n" + 
		            		      "</TimeStamp>\r\n" + 
		            		      "<styleUrl>#spinach</styleUrl>\r\n" + 
		            		      "<Point>\r\n" + 
		            		      		"<coordinates>"+fruit_LocByTime.getLocation().x()+","+fruit_LocByTime.getLocation().y()+","+fruit_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
		            		      "</Point>\r\n" + 
		            		"</Placemark>";
	            	content.append(kmlelement);
	            	fruit_LocByTime = ((fruit_metaData)curr_fruit.getData()).getLoc_by_time()[1];
	            	kmlelement =
		            		"<Placemark>\r\n" + 
		            			  "<description><![CDATA[id: <b>"+((fruit_metaData)curr_fruit.getData()).getId()+"</b><br/>weight: <b>"+((fruit_metaData)curr_fruit.getData()).getweight()+"</b>]]></description>"+
		            		      "<TimeStamp>\r\n" + 
		            		      		"<when>"+fruit_LocByTime.getTime()+"</when>\r\n" + 
		            		      "</TimeStamp>\r\n" + 
		            		      "<styleUrl>#no more</styleUrl>\r\n" + 
		            		      "<Point>\r\n" + 
		            		      		"<coordinates>"+fruit_LocByTime.getLocation().x()+","+fruit_LocByTime.getLocation().y()+","+fruit_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
		            		      "</Point>\r\n" + 
		            		"</Placemark>";
	            	content.append(kmlelement);
	            }
	        }
	        content.append(kmlend);
	        bw.write(content.toString());
	        bw.close();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
