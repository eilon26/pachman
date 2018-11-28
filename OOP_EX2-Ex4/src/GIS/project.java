package GIS;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import File_format.csv2mat;
import File_format.layer2kml;
import File_format.mat2layer;

public class project implements GIS_project {
	private ArrayList<GIS_layer> layer_set;
	private metaData md;
	
	public project(String file_path) {
		layer_set = new ArrayList<GIS_layer>();
		file_path_to_layer_set(file_path);
		md = null;
	}
	private void file_path_to_layer_set(String file_path){
		File currFile = new File(file_path);
		String currFileName = currFile.getName();
		
		if (!currFile.isDirectory()) {
			if (currFileName.substring(currFileName.length()-4,currFileName.length()).equals(".csv")) {
				csv2mat c2m = new csv2mat(file_path);
				mat2layer m2l = new mat2layer(c2m);
				layer_set.add(m2l.getL());
				//layer2kml l2k = new layer2kml(m2l, "C:\\Users\\eilon\\Desktop\\Ex2\\data\\output"+indexOut+".kml");
				//indexOut++;
			}
		}else {
			File[] files_arr = currFile.listFiles();
			for (int fileIndex=0;fileIndex<files_arr.length;fileIndex++) {
				file_path_to_layer_set(files_arr[fileIndex].getAbsolutePath());
			}
		}
	}
	@Override
	public boolean add(GIS_layer arg0) {
		return layer_set.add((Layer)arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		return layer_set.addAll(arg0);
	}

	@Override
	public void clear() {
		layer_set.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return layer_set.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return layer_set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return layer_set.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return layer_set.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return layer_set.remove((element)arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return layer_set.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return layer_set.retainAll(arg0);
	}

	@Override
	public int size() {
		return layer_set.size();
	}

	@Override
	public Object[] toArray() {
		return layer_set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return layer_set.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		return md;
	}

}
