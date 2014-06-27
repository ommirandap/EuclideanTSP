package cc4102.tarea3.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cc4102.tarea3.geom.Point;

public class ConvexHullAlgorithm implements TSPAlgorithm {

	@Override
	public TSPAlgorithmResults run(Point[] pointList) {
		ConvexHullCalculator convexHullCalculator = new ConvexHullCalculator();
		List<Point> currentCircuit = convexHullCalculator.buildHull(pointList);
		
		Set<Point> pointsOutsideCircuit = new HashSet<Point>(Arrays.asList(pointList));
		pointsOutsideCircuit.removeAll(currentCircuit);
		
		while(!pointsOutsideCircuit.isEmpty()) {
			step(pointsOutsideCircuit, currentCircuit);
		}
		
		TSPAlgorithmResults results = new TSPAlgorithmResults();
		results.circuit = currentCircuit;
		results.length = Point.getCircuitLength(currentCircuit);
		return results;
	}
	
	private void step(Set<Point> pointsOutsideCircuit, List<Point> currentCircuit) {
		List<PointToArc> pointToArcList = new ArrayList<PointToArc>();
		for(Point pOutsideHull : pointsOutsideCircuit) {
			PointToArc pta = new PointToArc();
			pointToArcList.add(pta);
			pta.p = pOutsideHull;
			for(int i = 0; i < currentCircuit.size(); i++) {
				Point p1 = currentCircuit.get(i);
				Point p2 = currentCircuit.get((i+1)%currentCircuit.size());
				pta.testPair(p1, p2);
			}
		}
		double minRatio = 0;
		PointToArc minPta = null;
		for(PointToArc pta : pointToArcList) {
			double ratio = 1.0 * ( pta.dist1 + pta.dist2 ) / pta.dist12;
			if(minPta == null || ratio < minRatio) {
				minRatio = ratio;
				minPta = pta;
			}
		}
		currentCircuit.add(currentCircuit.indexOf(minPta.p1)+1, minPta.p);
		pointsOutsideCircuit.remove(minPta.p);
	}

	@Override
	public String getName() {
		return "ConvexHull";
	}
	
	class PointToArc {
		public Point p, p1, p2;
		public double dist1, dist2, dist12;
		double minExpr;
		
		public void testPair(Point p1, Point p2) {
			double dist1 = p1.distance(p);
			double dist2 = p2.distance(p);
			double dist12 = p2.distance(p1);
			double expr = dist1 + dist2 - dist12;
			if(this.p1 == null || expr < minExpr) {
				this.p1 = p1;
				this.p2 = p2;
				this.dist1 = dist1;
				this.dist2 = dist2;
				this.dist12 = dist12;
				minExpr = expr;
			}
		}
	}

}
