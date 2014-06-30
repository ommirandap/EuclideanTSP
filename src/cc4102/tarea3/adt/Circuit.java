package cc4102.tarea3.adt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cc4102.tarea3.geom.Point;

public class Circuit extends LinkedList<Point> {
	List<Double> distances;

	public Circuit() {
		super();
	}

	public Circuit(Collection<? extends Point> c) {
		super(c);
	}
	
	@Override
	public void add(int index, Point element) {
		super.add(index, element);
		updateDistanceAt(index);
		//calculateDistances(index-1, index);
	}
	
	
	
	@Override
	public boolean add(Point element) {
		super.add(element);
		updateDistanceAt(size()-1);
		//calculateDistances(size()-2, size()-1);
		return true;
	}
	
	private void updateDistanceAt(int index) {
		distances.remove(index-1);
		distances.add(index-1, get(index-1).distance(get(index)));
		distances.add(index, get(index).distance(get((index+1) % size())));
		
	}
	
	public void calculateDistances() {
		if(distances == null) {
			distances = new ArrayList<Double>();
		}
		for(int i=0;i<size();i++) {
			distances.add(i, get(i).distance(get((i+1) % size())));
		}
	}
	
	/**
	 * Returns a precalculated distance between a consecutive pair of nodes in the path.
	 * @param nodeIndex Node index of the first node in the path.
	 * @return Distance between the node distance from the node located at nodeIndex to the next node in the path.
	 */
	public Double distanceAt(int nodeIndex) {
		if(distances != null && distances.size() > nodeIndex) {
			return distances.get(nodeIndex);
		}
		return null;
	}

	public Iterator<Double> getDistancesIterator() {
		return distances.iterator();
	}
	
	public Iterator<Double> getDistancesIterator(int index) {
		return distances.listIterator(index);
	}
	
	
}
