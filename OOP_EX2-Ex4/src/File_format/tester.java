package File_format;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class tester {

	@Test
	void test() {
		String s = "C:\\Users\\danie\\git\\gps2\\OOP_EX2-Ex4\\src\\File_format\\WigleWifi_20171201110209.csv";
		csv2mat c2m = new csv2mat(s);
		mat2layer m2l = new mat2layer(c2m);
		String loc = "C:\\Users\\danie\\git\\gps2\\OOP_EX2-Ex4\\src\\File_format\\testsubject.kml";
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

}
