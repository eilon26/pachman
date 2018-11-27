package GIS;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import Geom.Point3D;

public class metaData implements Meta_data{
	private String time;
	//double Orientation;
	private String name;//ssid
	private int rssi;
	//start adding new here.
	private String mac;
	private String AuthMode;
	private int Channel;
	private int AccuracyMeters;
	private String Type;
	private String color;
	
	//done adding new here.
	public metaData(String[] line,int[] metaIndexes) {
		time = line[metaIndexes[0]];
		rssi = Integer.parseInt(line[metaIndexes[1]]);
		name = line[metaIndexes[2]];	
		mac = line[metaIndexes[3]];
		AuthMode = line[metaIndexes[4]];
		Channel = Integer.parseInt(line[metaIndexes[5]]);
		AccuracyMeters = Integer.parseInt(line[metaIndexes[6]]);
		Type = line[metaIndexes[7]];
		if (rssi<-90) color="red";
		else color = "green";
	}
	

	public metaData(metaData other) {
		this.time = other.time;
		this.name=new String(other.name);
		this.rssi=other.rssi;
		this.mac = other.mac;
		this.AuthMode = other.AuthMode;
		this.Channel = other.Channel;
		this.AccuracyMeters = other.AccuracyMeters;
		this.Type = other.Type;
	}
	@Override
	public String toString() {
		return "name: "+this.name+", time: "+this.time+", rssi: "+this.rssi+", mac:"+this.mac+", authMode: "+this.AuthMode+", Channel: " + this.Channel +", AccuracyMeters:" +this.AccuracyMeters+", type: "+this.Type;
	}
	public String getColor() {
		return color;
	}
	public String getTime() {
		return time;
	}
	public String getMac() {
		return mac;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public int getChannel() {
		return Channel;
	}

	public int getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return Type;
	}

	@Override
	public long getUTC() {
		SimpleDateFormat pattern = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date d = null;
			try {
				d = pattern.parse(time);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return d.getTime();
	}

	public String getName() {
		return name;
	}

	public int getRssi() {
		return rssi;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}