package GIS;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import File_format.csv2mat;
import File_format.layer2kml;
import File_format.mat2layer;

public class project implements GIS_project {
	private ArrayList<GIS_layer> layer_set;
	private project_metaData md;
	
	public project(String file_path) {
		layer_set = new ArrayList<GIS_layer>();
		file_path_to_layer_set(file_path);
		
		String first_layer_time = ((Layer_metaData)((layer_set.get(0)).get_Meta_data())).getTime();
		long convert_time = new Date().getTime();
		md = new project_metaData(first_layer_time,convert_time);
	}
	
	private void file_path_to_layer_set(String file_path){
		File currFile = new File(file_path);
		String currFileName = currFile.getName();
		
		if (!currFile.isDirectory()) {
			if (currFileName.substring(currFileName.length()-4,currFileName.length()).equals(".csv")) {
				csv2mat c2m = new csv2mat(file_path);
				mat2layer m2l = new mat2layer(c2m);
				layer_set.add(m2l.getL());
			}
		}else {
			File[] files_arr = currFile.listFiles();
			for (int fileIndex=0;fileIndex<files_arr.length;fileIndex++) {
				file_path_to_layer_set(files_arr[fileIndex].getAbsolutePath());
			}
		}
	}

	@Override
	public boolean add(GIS_layer e) {
		return layer_set.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return layer_set.addAll(c);
	}

	@Override
	public void clear() {
		layer_set.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return layer_set.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layer_set.containsAll(c);
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
	public boolean remove(Object o) {
		return remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return layer_set.retainAll(c);
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
	public <T> T[] toArray(T[] a) {
		return layer_set.toArray(a);
	}

	@Override
	public Meta_Data get_Meta_data() {
		return md;
	}

	


}
