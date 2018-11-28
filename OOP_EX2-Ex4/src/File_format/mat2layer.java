package File_format;

import GIS.Layer;

public class mat2layer {
	private Layer l; 
	
	public Layer getL() {
		return l;
	}

	public mat2layer(csv2mat c2g) {
		this.l = new Layer(c2g.getG());
	}
	
}
