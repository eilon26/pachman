package algorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
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
		String[] colors = {"yellow","blue","red","green","black","gray","pink","orange","brown","white","perpel","darkRed","darkGreen"};
		String[] colorNum = {"7f00ffff","50780000","501400FF","5014F000","50000000","50969696"
				,"507814F0","501478FF","50143CAA","50FFFFFF","50780078","50140082","50005A14"};
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
	    		"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\r\n"
	    		+ "<Document>\r\n"
	    		+ 	"<Style id=\"popeye\">\r\n"
	    		+ 		"<IconStyle>\r\n"
	    		+ 		"<Icon>\r\n"
	    		+ 			"<href>https://cdn.iconscout.com/icon/premium/png-256-thumb/popeye-4-611225.png</href>\r\n"
	    		+ 		"</Icon>\r\n"
	    		+ 		"<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n"
	    		+ 		"</IconStyle>\r\n"
	    		+ 	"</Style>\r\n"
	    		+ 	"<Style id=\"spinach\">\r\n"
	    		+ 		"<IconStyle>\r\n"
	    		+ 			"<Icon>\r\n"
	    		+ 				"<href>https://clipartix.com/wp-content/uploads/2018/03/spinach-clipart-2018-24.png</href>\r\n"
	    		+ 			"</Icon>\r\n"
	    		+ 			"<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n"
	    		+ 		"</IconStyle>\r\n"
	    		+ 	"</Style>\r\n";

	    StringBuilder content = new StringBuilder(kmlstart);
	    for (int i=0;i<sol.getPathes().size()&&i<13;i++) {
	    kmlstart = 
	    		"	 <Style id=\""+colors[i]+"\">\r\n"+
	    		"      <LineStyle>\r\n" + 
	    		"        <color>"+colorNum[i]+"</color>\r\n" + 
	    		"        <width>4</width>\r\n" + 
	    		"      </LineStyle>\r\n" + 
	    		"    </Style>\r\n";
	    
	    content.append(kmlstart);
	    }
	    String kmlend = "</Document>\r\n</kml>";
	    try{
	    	int colorIndex=-1;
	        FileWriter fw = new FileWriter(output);
	        BufferedWriter bw = new BufferedWriter(fw);
	        Iterator<GIS_layer> iterPath =sol.getPathes().iterator();
	        while (iterPath.hasNext()) {
	        	colorIndex++;
		    	StringBuilder coordinates = new StringBuilder();
	            pachman_path curr_path = (pachman_path) iterPath.next();
	            Iterator<LocByTime> iterLocByTime = ((pachman_metaData)curr_path.getPach().getData()).getLoc_by_time().iterator();
	            if (iterLocByTime.hasNext()) {
	            	LocByTime next_LocByTime = iterLocByTime.next();
	            
	              while (next_LocByTime.getLocation()!=null){
	            	LocByTime curr_LocByTime = next_LocByTime; 
	            	if (iterLocByTime.hasNext()) {
	            		next_LocByTime = iterLocByTime.next();
	            	}
	            	else {
	            		iterLocByTime = ((pachman_metaData)curr_path.getPach().getData()).getLoc_by_time().iterator();
	            		next_LocByTime = new LocByTime(null,(long)(iterLocByTime.next().getUtc()+sol.getGeneraleTime()*1000+1000));
	            	}
	            	String kmlelement =
	            		"<Placemark>\r\n" + 
	            			  "<description><![CDATA[id: <b>"+((pachman_metaData)curr_path.getPach().getData()).getId()+
	            			  "</b><br/>speed: <b>"+((pachman_metaData)curr_path.getPach().getData()).getSpeed()+
	            			  "</b><br/>radius: <b>"+((pachman_metaData)curr_path.getPach().getData()).getRadius()+
	            			  "</b>]]></description>\r\n"+
	            		      "<TimeSpan>\r\n" + 
	            		      		"<begin>"+curr_LocByTime.getTime()+"</begin>\r\n" +
	            		      		"<end>"+next_LocByTime.getTime()+"</end>\r\n" + 
	            		      "</TimeSpan>\r\n" + 
	            		      "<styleUrl>#popeye</styleUrl>\r\n" + 
	            		      "<Point>\r\n" + 
	            		      		"<coordinates>"+curr_LocByTime.getLocation().x()+","+curr_LocByTime.getLocation().y()+
	            		      		","+curr_LocByTime.getLocation().z()+"</coordinates>\r\n" + 
	            		      "</Point>\r\n" + 
	            		"</Placemark>\r\n";
	            	content.append(kmlelement);
	            	coordinates.append(""+curr_LocByTime.getLocation().x()+","+curr_LocByTime.getLocation().y()+
	            		      		","+curr_LocByTime.getLocation().z()+"\r\n");
	            }
	          }
	            //the pachman path
	            String coords = coordinates.toString();
	            String route = " <Placemark>\r\n" + 
	            		"      <styleUrl>#"+colors[colorIndex%13]+"</styleUrl>\r\n" + 
	            		"      <LineString>\r\n" + 
	            		"        <extrude>1</extrude>\r\n" + 
	            		"        <tessellate>1</tessellate>\r\n" +  
	            		"        <coordinates> \r\n" + coords +"\r\n"+
	            		"        </coordinates>\r\n" + 
	            		"      </LineString>\r\n" + 
	            		"    </Placemark>\r\n";
	            content.append(route);
	            
	            Iterator<GIS_element> iterFruit = curr_path.iterator();
	            while (iterFruit.hasNext()) {
	            	fruit curr_fruit = (fruit)iterFruit.next();
	            	LocByTime fruit_LocByTime0 = ((fruit_metaData)curr_fruit.getData()).getLoc_by_time()[0];
	            	LocByTime fruit_LocByTime1 = ((fruit_metaData)curr_fruit.getData()).getLoc_by_time()[1];
	            	String kmlelement =
		            		"<Placemark>\r\n" + 
		            			  "<description><![CDATA[id: <b>"+((fruit_metaData)curr_fruit.getData()).getId()+"</b><br/>weight: <b>"+
		            				((fruit_metaData)curr_fruit.getData()).getweight()+"</b>]]></description>"+
		            		      "<TimeSpan>\r\n" + 
		            		      		"<begin>"+fruit_LocByTime0.getTime()+"</begin>\r\n" +
		            		      		"<end>"+fruit_LocByTime1.getTime()+"</end>\r\n" +
		            		      "</TimeSpan>\r\n" + 
		            		      "<styleUrl>#spinach</styleUrl>\r\n" + 
		            		      "<Point>\r\n" + 
		            		      		"<coordinates>"+fruit_LocByTime0.getLocation().x()+","+fruit_LocByTime0.getLocation().y()+
		            		      		","+fruit_LocByTime0.getLocation().z()+"</coordinates>\r\n" + 
		            		      "</Point>\r\n" + 
		            		"</Placemark>\r\n";
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
