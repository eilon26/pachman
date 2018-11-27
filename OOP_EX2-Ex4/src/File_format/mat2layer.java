package File_format;

import GIS.Layers;

public class mat2layer {
	Layers l; 
	
	public mat2layer(csv2mat c2g) {
		this.l = new Layers(c2g.g);
	}
	
}
