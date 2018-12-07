package File_format;

import GIS.GameBoard;

public class mat2gameBoard {
	private GameBoard GB;
	
	public mat2gameBoard(csv2mat c2m) {
		this.GB = new GameBoard(c2m);
	}
	
	public GameBoard getGB() {
		return GB;
	}
}
