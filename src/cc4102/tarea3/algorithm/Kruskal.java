package cc4102.tarea3.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cc4102.tarea3.geom.Arc;
import cc4102.tarea3.geom.Point;

public class Kruskal {
	public List<Arc> computeMST(final List<Point> points) {
		List<Long> pairs = new ArrayList<Long>();
		for(int i=0;i<points.size();i++) {
			for(int j=i+1;j<points.size();j++) {
				pairs.add((long)((((long)i)<<32)+j));
			}
		}
		Collections.sort(pairs, new Comparator<Long>() {
			public int compare(Long l1, Long l2) {
				// Some hacking skills
				int i1 = (int)(l1>>32);
				int i2 = (int)(l1&0xFFFF);
				int j1 = (int)(l2>>32);
				int j2 = (int)(l2&0xFFFF);
				if(points.get(i1).distance2(points.get(i2)) >
						points.get(j1).distance2(points.get(j2))){
					return 1;
				} else {
					return -1;
				}
			}
		});
		int[] groups = new int[points.size()];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = i;
		}
		List<Long> resultRaw = new ArrayList<Long>(); 
		for(long l : pairs) {
			int i1 = (int)(l>>32);
			int i2 = (int)(l&0xFFFF);
			int repr1 = getRepr(groups,i1);
			int repr2 = getRepr(groups,i2);
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
}
