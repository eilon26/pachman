package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import GIS.element;
import GIS.metaData;
import Geom.geom;
//ArrayList<String[]> a = l.e
public class layer2kml {
	
	public layer2kml(mat2layer m2l, String output) {
    
    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
    		"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n";
    String content = kmlstart;
    
    String kmlend = "</Folder>\r\n" + "</Document></kml>";
    try{
        FileWriter fw = new FileWriter(output);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 2; i < m2l.getL().getEl().size(); i++) {
            element CurrElement = new element(m2l.getL().getEl().get(i));
            String color = "";
            if (((metaData)(CurrElement.getData())).getRssi()<-90) color="<styleUrl>#green</styleUrl>";
            else color="<styleUrl>#red</styleUrl>";
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
        //String csv = content.toString().replaceFirst(", <Placemark>\\r\\n", "<Placemark>\\r\\n");//.replaceAll(",", "").replace("[", "").replace("]", "");
        bw.write(content);
        bw.close();
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
	}
}

