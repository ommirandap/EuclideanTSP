

import cc4102.tarea3.algorithm.mst.Prim;
import cc4102.tarea3.algorithm.mst.UndirectedGraph;
import cc4102.tarea3.geom.Point;

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
		
		for(Point aux : res){
			System.out.println(aux.toString());
			for(Point point : res.edgesFrom(aux).keySet()) {
				System.out.println("\t"+point);
			}
		}
		
	}
	
}
