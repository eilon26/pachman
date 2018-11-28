package File_format;

import GIS.Layer;

/**
 * this class adds the arraylist it gets into the ultimate layer.
 * @author Daniel Ventura
 * l - the layer.
 */
public class mat2layer {
	private Layer l; 
	/**
	 * @return l.
	 */
	public Layer getL() {
		return l;
	}

	/**
	 * adds arraylist to layer.
	 * @param c2g - the arraylist.
	 */
	public mat2layer(csv2mat c2g) {
		this.l = new Layer(c2g.getG());
		
	}
	
}
