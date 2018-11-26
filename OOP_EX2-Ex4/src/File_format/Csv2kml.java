package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Csv2kml {
	public static void main(String[] args) {
		String csvFile = "C:\\Users\\danie\\Desktop\\Ex2\\data\\WigleWifi_20171203085618.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
            while ((line = br.readLine()) != null) 
            {
                String[] userInfo = line.split(cvsSplitBy);

//                for (int i = 0; i < userInfo.length; i++) {
//					System.out.print(userInfo[i] + " ");
//				}
//                System.out.println();
                //System.out.println("ID: " + userInfo[0] + " , Name: " + userInfo[1]);
            }

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	
	static void writeFileKML(ArrayList<String[]> a, String output) {
	    ArrayList<String> content = new ArrayList<String>();
	    String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
	    content.add(kmlstart);

	    String kmlend = "</kml>";
	    try{
	        FileWriter fw = new FileWriter(output);
	        BufferedWriter bw = new BufferedWriter(fw);
	        for (int i = 1; i < a.size(); i++) {
	            String[] s = a.get(i);
	            String kmlelement ="<Document>" +
	            		"<Placemark>\n" +
	                    "<name>"+s[6]+"</name>\n" +
	                    "<description>"+s[6]+"</description>\n" +
	                    "<Point>\n" +
	                    "<coordinates>"+s[3]+","+s[2]+"</coordinates>" +
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
