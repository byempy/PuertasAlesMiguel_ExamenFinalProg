package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaCochesOrdenada<T> extends ArrayList<T>{
	
	private Comparator comp;

	public ListaCochesOrdenada(Comparator comp){
		this.comp = comp;
		
	}

	@Override
	public boolean add(T arg0){
		boolean result = super.add(arg0);
		Collections.sort(this, comp);
		return result;
	}
	
	@Override
	public boolean remove(Object arg0){
		boolean result = super.remove(arg0);
		Collections.sort(this, comp);
		return result;
	}
}
