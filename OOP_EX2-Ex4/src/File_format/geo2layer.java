package File_format;

import GIS.Layers;

public class geo2layer {
	Layers l; 
	
	public geo2layer(csv2geo c2g) {
		this.l = new Layers(c2g.g);
	}
	
}
