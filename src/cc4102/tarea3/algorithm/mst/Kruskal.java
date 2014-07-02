package cc4102.tarea3.algorithm.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc4102.tarea3.algorithm.mst.QuickSort.CustomComparator;
import cc4102.tarea3.geo.Country;
import cc4102.tarea3.geom.Point;
import cc4102.tarea3.io.DataReader;

public class Kruskal {
	public Map<Point, List<Point>> computeMST(final List<Point> points) {
		long[] pairs = new long[points.size()*(points.size()-1)/2];
		
		//double lastPerc = -1;
		int k = 0;
		for(int i=0;i<points.size();i++) {
			for(int j=i+1;j<points.size();j++) {
				pairs[k++] = ((long)((((long)i)<<32)+j));
			}
			/*double perc = Math.ceil(100.*i/points.size());
			if(perc > lastPerc) {
				lastPerc = perc;
				System.out.println(perc+"% completed");
			}*/
		}
		//System.out.println("sorting");
		
		new QuickSort().sort(pairs, new MyCustomComparator(points));
		//System.out.println("sorted");
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
		Map<Point, List<Point>> neighbors = new HashMap<Point, List<Point>>();
		for(Long l : resultRaw) {
			int i1 = (int)(l>>32);
			int i2 = (int)(l&0xFFFF);
			if(!neighbors.containsKey(points.get(i1)))
				neighbors.put(points.get(i1), new ArrayList<Point>());
			if(!neighbors.containsKey(points.get(i2)))
				neighbors.put(points.get(i2), new ArrayList<Point>());
			neighbors.get(points.get(i1)).add(points.get(i2));
			neighbors.get(points.get(i2)).add(points.get(i1));
		}
		/*List<Arc> arcs = new ArrayList<>();
		for (Long l : resultRaw) {
			int i1 = (int)(l>>32);
			int i2 = (int)(l&0xFFFF);
			arcs.add(new Arc(points.get(i1), points.get(i2)));
		}*/
		return neighbors;
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
		public int compare(long l1, long l2) {
			// Some hacking skills
			int i1 = (int)(l1>>32);
			int i2 = (int)(l1&0xFFFF);
			int j1 = (int)(l2>>32);
			int j2 = (int)(l2&0xFFFF);
			/*System.out.println("points["+i1+"]="+points.get(i1)+" points["+i2+"]="+points.get(i2)+" distance:"+points.get(i1).distance2(points.get(i2)));
			System.out.println("points["+j1+"]="+points.get(j1)+" points["+j2+"]="+points.get(j2)+" distance:"+points.get(j1).distance2(points.get(j2)));
			System.out.println("");*/
			double dist1 = points.get(i1).distance2(points.get(i2));
			double dist2 = points.get(j1).distance2(points.get(j2));
			if(dist1 > dist2){
				return 1;
			} else if(dist1 == dist2){
				return 0;
			} else return -1;
		}
	}
	
	public static void main(String[] args) {
		DataReader dr = new DataReader();
		Point[] points = dr.getData(Country.SWEDEN);
		Kruskal kruskal = new Kruskal();
		kruskal.computeMST(Arrays.asList(points));
		System.out.println("finished");
	}
}
