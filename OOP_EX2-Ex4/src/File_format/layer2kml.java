package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.element;
import GIS.metaData;
import Geom.geom;

/**
 * this class is the last in the translation of a csv file to kml, it
 * takes the hole layer that contains a lot of arraylists and turns it into a large kml file.
 * @author Daniel Ventura
 */
public class layer2kml {
	/**
	 * this function takes each line in each arraylist and turns it into a kml point.
	 * @param m2l - the layer.
	 * @param output - the location to create the new kml file.
	 */
	public layer2kml(mat2layer m2l, String output) {
    
    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
    		"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n";
    String content = kmlstart;
    
    String kmlend = "</Folder>\r\n" + "</Document></kml>";
    try{
        FileWriter fw = new FileWriter(output);
        BufferedWriter bw = new BufferedWriter(fw);
        Iterator<GIS_element> iterL = m2l.getL().iterator();
        while (iterL.hasNext()) {
            element CurrElement = (element) iterL.next();//new element(m2l.getL().getElement_Set().get(i));
            String kmlelement =
                    "<Placemark>\r\n"+
                            "<name><![CDATA["+((metaData)(CurrElement.getData())).getName()+"]]></name>\r\n"+
                            "<description><![CDATA[BSSID: <b>"+((metaData)(CurrElement.getData())).getMac()+"</b><br/>Channel: <b>"+((metaData)(CurrElement.getData())).getChannel()+"</b><br/>Type: <b>"+((metaData)(CurrElement.getData())).getType()+"</b><br/>AccuracyMeters: <b>"+((metaData)(CurrElement.getData())).getAccuracyMeters()+"</b><br/>Capabilities: <b>"+((metaData)(CurrElement.getData())).getAuthMode()+"</b><br/>Frequency: <b>"+((metaData)(CurrElement.getData())).getRssi()+"</b><br/>Timestamp: <b>"+((metaData)(CurrElement.getData())).getUTC()+"</b><br/>Date: <b>"+((metaData)(CurrElement.getData())).getTime()+"</b>]]></description><styleUrl>#"+((metaData)(CurrElement.getData())).getColor()+"</styleUrl>\r\n" +
                            "<Point>\r\n"+
                            "<coordinates>"+((geom)(CurrElement.getGeom())).getP().y()+","+((geom)(CurrElement.getGeom())).getP().x()+","+((geom)(CurrElement.getGeom())).getP().z()+"</coordinates></Point>\r\n"+
                     "</Placemark>\r\n";
            

            content+=kmlelement;
        }
        content+=kmlend;
        bw.write(content);
        bw.close();
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
	}
}

