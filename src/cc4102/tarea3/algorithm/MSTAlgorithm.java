package cc4102.tarea3.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cc4102.tarea3.algorithm.mst.Kruskal;
import cc4102.tarea3.geom.Point;

public class MSTAlgorithm implements TSPAlgorithm {
	
	@Override
	public TSPAlgorithmResults run(Point[] pointList) {
		Kruskal kruskal = new Kruskal();
		Map<Point, List<Point>> neighbors = kruskal.computeMST(Arrays.asList(pointList));
		// Escogemos el primer nodo que salga de la iteración en el conjunto de llaves del mapa como raíz.
		Node root = new Node(neighbors.keySet().iterator().next());
		addChildrenTo(root, neighbors);
		List<Point> circuit = new ArrayList<>(); 
		preorderValues(root, circuit);
		TSPAlgorithmResults results = new TSPAlgorithmResults();
		results.circuit = circuit;
		results.length = Point.getCircuitLength(circuit);
		return results;
	}
	
	private void preorderValues(Node n, List<Point> values) {
		values.add(n.getPoint());
		for(Node child : n.getChildren()) {
			preorderValues(child, values);
		}
	}
	
	private void addChildrenTo(Node n, Map<Point, List<Point>> neighbors) {
		for(Point p : neighbors.get(n.getPoint())) {
			if(n.getParent() != null && p == n.getParent().getPoint())
				continue;
			Node child = new Node(p);
			n.addChild(child);
			addChildrenTo(child, neighbors);
		}
	}

	@Override
	public String getName() {
		return "MST";
	}
	
	class Node {
		private final Point p;
		private List<Node> children;
		private Node parent; 
		
		public Node(Point p) {
			this.p = p;
			children = new ArrayList<Node>();
		}
		
		public Node getParent() {
			return parent;
		}
		
		public void addChild(Node n) {
			children.add(n);
			n.parent = this;
		}
		
		public List<Node> getChildren() {
			return children;
		}
		
		public Point getPoint() {
			return p;
		}
	}

}
