package File_format;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import algorithms.MultiCSV;

class tester {

	@Test
	void testOneCsvFile() {
		String s = "C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\File_format\\WigleWifi_20171201110209.csv";
		csv2mat c2m = new csv2mat(s);
		mat2layer m2l = new mat2layer(c2m);
		String loc = "C:\\Users\\EILON\\Documents\\studies 2.1\\eclipse files\\OOP_EX2-EX4\\OOP_EX2-Ex4\\src\\File_format\\testsubject.kml";
		layer2kml l2k = new layer2kml(m2l,loc);
		try {
			BufferedReader br = new BufferedReader(new FileReader(loc));
			try {
				assertTrue(br.readLine() != null);
			} catch (IOException e) {
				
			}
		} catch (FileNotFoundException e) {
		
		}

	}
	@Test
	void testMultyCsvFiles() {
		new MultiCSV("C:\\Users\\EILON\\Desktop\\Ex2\\data\\start").project2kml( "C:\\Users\\eilon\\Desktop\\Ex2\\data\\output4.kml");
		File Loc = new File("C:\\Users\\eilon\\Desktop\\Ex2\\data\\output5.kml");
		try {
			BufferedReader br = new BufferedReader(new FileReader(Loc));
			try {
				assertTrue(br.readLine() != null);
			} catch (IOException e) {
				
			}
		} catch (FileNotFoundException e) {
		
		}
	}
}
