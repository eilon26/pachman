package File_format;

import GIS.Layers;

public class mat2layer {
	private Layers l; 
	
	public Layers getL() {
		return l;
	}

	public mat2layer(csv2mat c2g) {
		this.l = new Layers(c2g.getG());
	}
	
}
