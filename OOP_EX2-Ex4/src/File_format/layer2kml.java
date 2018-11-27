package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
//ArrayList<String[]> a = l.e
public class layer2kml {
	
	public layer2kml(geo2layer l, String output) {
    ArrayList<String> content = new ArrayList<String>();
    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
    content.add(kmlstart);

    String kmlend = "</kml>";
    try{
        FileWriter fw = new FileWriter(output);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 2; i < a.size(); i++) {
            String[] s = a.get(i);
            String kmlelement ="<Document>" +
            		"<Placemark>\n" +
                    "<name>"+s[0]+"</name>\n" +
                    "<description>"+s[1]+"</description>\n" +
                    "<Point>\n" +
                    "<coordinates>"+s[2]+","+s[2]+"</coordinates>" +
                    "</Point>\n" +
                    "</Placemark>\n" +
                    "</Document>";
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
