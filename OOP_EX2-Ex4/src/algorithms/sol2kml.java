package algorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;

import GIS.*;
import Geom.geom;
/**
 * the class is responsible to convert from the object ShortestPathAlgo to kml file
 * @author EILON
 *
 */
public class sol2kml {
	/**
	 * the construction is responsible to convert from the object ShortestPathAlgo to kml file and save it to the path that it got
	 * @param sol the ShortestPathAlgo object
	 * @param output a string object that contain the path to save the output file
	 */
	public sol2kml(ShortestPathAlgo sol,String output) {
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
	    		"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"popeye\"><IconStyle><Icon>"
	    		+ "<href>https://cdn.iconscout.com/icon/premium/png-256-thumb/popeye-4-611225.png</href></Icon>"
	    		+ "<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style><Style id=\"spinach\">"
	    		+ "<IconStyle><Icon><href>https://clipartix.com/wp-content/uploads/2018/03/spinach-clipart-2018-24.png</href>"
	    		+ "</Icon><hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style><Style id=\"no more\">"
	    		+ "<IconStyle><Icon><href>https://www.iconspng.com/images/nosign-x/nosign-x.jpg</href></Icon>"
	    		+ "<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style>"
	    		+ "    <Style id=\"yellow\">\r\n" + 
	    		"      <LineStyle>\r\n" + 
	    		"        <color>7f00ffff</color>\r\n" + 
	    		"        <width>4</width>\r\n" + 
	    		"      </LineStyle>\r\n" + 
	    		"      <PolyStyle>\r\n" + 
	    		"        <color>7f00ff00</color>\r\n" + 
	    		"      </PolyStyle>\r\n" + 
	    		"    </Style>\r\n";
	    
	    StringBuilder content = new StringBuilder(kmlstart);
	    String kmlend = "</Document></kml>";
	    try{
	        FileWriter fw = new FileWriter(output);
	        BufferedWriter bw = new BufferedWriter(fw);
	        Iterator<GIS_layer> iterPath =sol.getPathes().iterator();
	        while (iterPath.hasNext()) {
		    	StringBuilder coordinates = new StringBuilder();
	            pachman_path curr_path = (pachman_path) iterPath.next();
	            Iterator<LocByTime> iterLocByTime = ((pachman_metaData)curr_path.getPach().getData()).getLoc_by_time().iterator();
	            while (iterLocByTime.hasNext()) {
	            	LocByTime curr_LocByTime = iterLocByTime.next(); 
	            	String kmlelement =
	            		"<Placemark>\r\n" + 
	            			  "<description><![CDATA[id: <b>"+((pachman_metaData)curr_path.getPach().getData()).getId()+
	            			  "</b><br/>speed: <b>"+((pachman_metaData)curr_path.getPach().getData()).getSpeed()+
	            			  "</b><br/>radius: <b>"+((pachman_metaData)curr_path.getPach().getData()).getRadius()+
	            			  "</b>]]></description>"+
	            		      "<TimeStamp>\r\n" + 
	            		      		"<when>"+curr_LocByTime.getTime()+"</when>\r\n" + 
	            		      "</TimeStamp>\r\n" + 
	            		      "<styleUrl>#popeye</styleUrl>\r\n" + 
	            		      "<Point>\r\n" + 
	            		      		"<coordinates>"+curr_LocByTime.getLocation().x()+","+curr_LocByTime.getLocation().y()+
	            		      		","+curr_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
	            		      "</Point>\r\n" + 
	            		"</Placemark>";
	            	content.append(kmlelement);
	            	coordinates.append(""+curr_LocByTime.getLocation().x()+","+curr_LocByTime.getLocation().y()+
	            		      		","+curr_LocByTime.getLocation().z()+"\r\n");
	            }
	            String coords = coordinates.toString();
	            String route = " <Placemark>\r\n" + 
	            		"      <styleUrl>#yellow</styleUrl>\r\n" + 
	            		"      <LineString>\r\n" + 
	            		"        <extrude>1</extrude>\r\n" + 
	            		"        <tessellate>1</tessellate>\r\n" +  
	            		"        <coordinates> \r\n" + coords +"\r\n"+
	            		"        </coordinates>\r\n" + 
	            		"      </LineString>\r\n" + 
	            		"    </Placemark>";
	            content.append(route);
	            
	            Iterator<GIS_element> iterFruit = curr_path.iterator();
	            while (iterFruit.hasNext()) {
	            	fruit curr_fruit = (fruit)iterFruit.next();
	            	LocByTime fruit_LocByTime = ((fruit_metaData)curr_fruit.getData()).getLoc_by_time()[0];
	            	String kmlelement =
		            		"<Placemark>\r\n" + 
		            			  "<description><![CDATA[id: <b>"+((fruit_metaData)curr_fruit.getData()).getId()+"</b><br/>weight: <b>"+
		            				((fruit_metaData)curr_fruit.getData()).getweight()+"</b>]]></description>"+
		            		      "<TimeStamp>\r\n" + 
		            		      		"<when>"+fruit_LocByTime.getTime()+"</when>\r\n" + 
		            		      "</TimeStamp>\r\n" + 
		            		      "<styleUrl>#spinach</styleUrl>\r\n" + 
		            		      "<Point>\r\n" + 
		            		      		"<coordinates>"+fruit_LocByTime.getLocation().x()+","+fruit_LocByTime.getLocation().y()+
		            		      		","+fruit_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
		            		      "</Point>\r\n" + 
		            		"</Placemark>";
	            	content.append(kmlelement);
	            	fruit_LocByTime = ((fruit_metaData)curr_fruit.getData()).getLoc_by_time()[1];
	            	kmlelement =
		            		"<Placemark>\r\n" + 
		            			  "<description><![CDATA[id: <b>"+((fruit_metaData)curr_fruit.getData()).getId()+"</b><br/>weight: <b>"+
		            				((fruit_metaData)curr_fruit.getData()).getweight()+"</b>]]></description>"+
		            		      "<TimeStamp>\r\n" + 
		            		      		"<when>"+fruit_LocByTime.getTime()+"</when>\r\n" + 
		            		      "</TimeStamp>\r\n" + 
		            		      "<styleUrl>#no more</styleUrl>\r\n" + 
		            		      "<Point>\r\n" + 
		            		      		"<coordinates>"+fruit_LocByTime.getLocation().x()+","+fruit_LocByTime.getLocation().y()+","+
		            		      fruit_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
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
