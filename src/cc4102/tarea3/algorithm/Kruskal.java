package cc4102.tarea3.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cc4102.tarea3.algorithm.QuickSort.CustomComparator;
import cc4102.tarea3.geom.Arc;
import cc4102.tarea3.geom.Point;

public class Kruskal {
	public List<Arc> computeMST(final List<Point> points) {
		long[] pairs = new long[points.size()*(points.size()-1)/2];
		
		double lastPerc = -1;
		int k = 0;
		for(int i=0;i<points.size();i++) {
			for(int j=i+1;j<points.size();j++) {
				pairs[k++] = ((long)((((long)i)<<32)+j));
			}
			double perc = Math.ceil(100.*i/points.size());
			if(perc > lastPerc) {
				lastPerc = perc;
				System.out.println(perc+"% completed");
			}
		}
		System.out.println("sorting");
		
		new QuickSort().sort(pairs, new MyCustomComparator(points));
		System.out.println("sorted");
		int[] groups = new int[points.size()];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = i;
		}
		List<Long> resultRaw = new ArrayList<Long>(); 
		for(long l : pairs) {
			int i1 = (int)(l>>32);
			int i2 = (int)(l&0xFFFF);
			int repr1 = getRepr(groups,i1);
			groups[i1] = repr1;
			int repr2 = getRepr(groups,i2);
			groups[i2] = repr2;
			if(repr1 != repr2) {
				resultRaw.add(l);
				groups[repr1] = repr2;
			}
		}
		pairs = null;
		groups = null;
		List<Arc> arcs = new ArrayList<>();
		for (Long l : resultRaw) {
			int i1 = (int)(l>>32);
			int i2 = (int)(l&0xFFFF);
			arcs.add(new Arc(points.get(i1), points.get(i2)));
		}
		return arcs;
	}
	
	private int getRepr(int[] group, int a) {
		int repr = group[a];
		while(repr != group[repr]) {
			repr = group[repr];
		}
		return repr;
	}
	
	class MyCustomComparator implements CustomComparator {
		List<Point> points;
		public MyCustomComparator(List<Point> points) {
			this.points = points;
		}
		@Override
		public boolean compare(long l1, long l2) {
			// Some hacking skills
			int i1 = (int)(l1>>32);
			int i2 = (int)(l1&0xFFFF);
			int j1 = (int)(l2>>32);
			int j2 = (int)(l2&0xFFFF);
			/*System.out.println("points["+i1+"]="+points.get(i1)+" points["+i2+"]="+points.get(i2)+" distance:"+points.get(i1).distance2(points.get(i2)));
			System.out.println("points["+j1+"]="+points.get(j1)+" points["+j2+"]="+points.get(j2)+" distance:"+points.get(j1).distance2(points.get(j2)));
			System.out.println("");*/
			if(points.get(i1).distance2(points.get(i2)) >
					points.get(j1).distance2(points.get(j2))){
				return true;
			} else {
				return false;
			}
		}
	}
}
