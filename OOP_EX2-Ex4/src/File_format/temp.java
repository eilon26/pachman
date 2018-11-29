package File_format;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import algorithms.MultiCSV;

public class temp {
public static void main(String[] args) throws ParseException{
//	csv2mat a = new csv2mat("C:\\Users\\eilon\\Desktop\\Ex2\\data\\WigleWifi_20171203085618.csv\\");
//	mat2layer b = new mat2layer(a);//	layer2kml c = new layer2kml(b, "C:\\Users\\eilon\\Desktop\\Ex2\\data\\output9.kml");
	new MultiCSV("C:\\Users\\EILON\\Desktop\\Ex2\\data\\start").project2kml( "C:\\Users\\eilon\\Desktop\\Ex2\\data\\output4.kml");
	
}
}

