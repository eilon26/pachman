package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.*;
import algorithms.ShortestPathAlgo;

public class solution {
	ShortestPathAlgo s;
	
	public solution(GameBoard GB) {
		s=new ShortestPathAlgo(GB);
	}
	
}
