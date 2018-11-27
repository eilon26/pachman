package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import GIS.element;
import GIS.metaData;
import Geom.geom;
//ArrayList<String[]> a = l.e
public class layer2kml {
	
	public layer2kml(mat2layer gl, String output) {
    ArrayList<String> content = new ArrayList<String>();
    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
    content.add(kmlstart);

    String kmlend = "</kml>";
    try{
        FileWriter fw = new FileWriter(output);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 2; i < gl.getL().getEl().size(); i++) {
            element CurrElement = new element(gl.getL().getEl().get(i));
            String kmlelement =
            	"<Element>\n" +
            		"<geom>\n" +
            			"<lat>"+((geom)(CurrElement.getGeom())).getP().x()+"</lat>\n" +
            			"<lon>"+((geom)(CurrElement.getGeom())).getP().y()+"</lon>\n" +
                    	"<alt>"+((geom)(CurrElement.getGeom())).getP().z()+"</alt>" +
                    "</geom>\n" +
            		"<metaData>\n" +
            			"<name>"+((metaData)(CurrElement.getData())).getName()+"</name>\n" +
            			"<rssi>"+((metaData)(CurrElement.getData())).getRssi()+"</rssi>\n" +
            			"<utc>"+((metaData)(CurrElement.getData())).getUTC()+"</utc>" +
            		"</metaData>\n" +
                "</Element>";
            content.add(kmlelement);
        }
        content.add(kmlend);
        String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
        bw.write(csv);
        bw.close();
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
	}
}
