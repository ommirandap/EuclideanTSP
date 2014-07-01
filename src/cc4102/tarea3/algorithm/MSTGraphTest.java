package cc4102.tarea3.algorithm;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cc4102.tarea3.geom.Point;
import cc4102.tarea3.algorithm.*;

public class MSTGraphTest {

	static public void main(String []args){
		UndirectedGraph<Point> G = new UndirectedGraph<Point>();
		
		Point []p = { new Point(0.0,0.0), new Point(1.0,0.0),
				new Point(0.0,1.0), new Point(0.0, -1.0),
				new Point(-1.0, 0.0)
		};
		
		for(int i =0; i < p.length; i++){
			for(int j = i+1; j < p.length; j++){
				Point p1 = p[i];
				Point p2 = p[j];
				G.addNode(p1);
				G.addNode(p2);
				G.addEdge(p1, p2, p1.distance(p2));
			}
		}
		
		UndirectedGraph<Point> res = Prim.mst(G);
		Iterator<Point> it = res.iterator();
		
		while(it.hasNext()){
			Point aux = it.next();
			System.out.println(aux.toString());
			Map<Point, Double> hm = res.edgesFrom(aux);
			Set<Point> ble = hm.keySet();
			Iterator it2 = ble.iterator();
			while(it2.hasNext()){
				System.out.println("\t"+it2.next());
			}
		}
		
	}
	
}
