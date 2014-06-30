package cc4102.tarea3.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import cc4102.tarea3.adt.Circuit;
import cc4102.tarea3.geom.Point;

public class ConvexHullAlgorithm implements TSPAlgorithm {
	
	private long timeTotal = 0, timePoint1 = 0, timePoint2 = 0;

	@Override
	public TSPAlgorithmResults run(Point[] pointList) {
		System.out.println("Calculating Hull");
		ConvexHullCalculator convexHullCalculator = new ConvexHullCalculator();
		Circuit currentCircuit = convexHullCalculator.buildHull(pointList);
		
		System.out.println("Calculating points outside hull");
		Set<Point> pointsOutsideCircuit = new HashSet<Point>(Arrays.asList(pointList));
		pointsOutsideCircuit.removeAll(currentCircuit);
		
		System.out.println("Calculating points outside hull");
		Set<PointToArc> pointToArcSet = new HashSet<PointToArc>();
		for(Point p : pointsOutsideCircuit) {
			PointToArc pta = new PointToArc();
			pta.p = p;
			pointToArcSet.add(pta);
		}
		
		System.out.println("Adding points to circuit");
		timeTotal = System.nanoTime();
		double lastPerc = -1;
		int lastInsertIndex = -1;
		while(!pointToArcSet.isEmpty()) {
			double perc = Math.ceil(100.*currentCircuit.size()/pointList.length);
			if(perc > lastPerc) {
				lastPerc = perc;
				System.out.println(perc+"% completed");
				/*if(perc == 11) {
					timeTotal = System.nanoTime()-timeTotal;
					System.out.println("Step to 5% took "+String.format("%,6.3f", timeTotal/1000000000.0)+"s");
					System.out.println("Point1: "+String.format("%,6.3f", timePoint1/1000000000.0)+"("+(100.0*timePoint1/timeTotal)+"%)");
					System.out.println("Point2: "+String.format("%,6.3f", timePoint2/1000000000.0)+"("+(100.0*timePoint2/timeTotal)+"%)");
					System.exit(0);
				}*/
			}
			if(lastInsertIndex == -1) {
				evaluateDistances(pointToArcSet, currentCircuit);
				lastInsertIndex = choosePointAndAdd(pointToArcSet, currentCircuit);
			} else {
				evaluateDistances(pointToArcSet, currentCircuit, lastInsertIndex);
				lastInsertIndex = choosePointAndAdd(pointToArcSet, currentCircuit);
			}
		}
		
		TSPAlgorithmResults results = new TSPAlgorithmResults();
		results.circuit = currentCircuit;
		results.length = Point.getCircuitLength(currentCircuit);
		return results;
	}
	
	private final void evaluateDistances(Set<PointToArc> pointsOutsideCircuit, Circuit currentCircuit) {
		for(PointToArc pOutsideHull : pointsOutsideCircuit) {
			evaluateDistances(pOutsideHull, currentCircuit);
		}
	}
	
	private final void evaluateDistances(PointToArc pta, Circuit currentCircuit) {
		pta.p1 = null;
		Iterator<Point> iterator = currentCircuit.listIterator();
		Iterator<Double> iteratorDistances = currentCircuit.getDistancesIterator();
		Point p1;
		Point p2 = iterator.next();
		while(iterator.hasNext()) {
			p1 = p2;
			p2 = iterator.next();
			pta.testPair(p1, p2, iteratorDistances.next());
		}
		//Compare last to first
		p1 = p2;
		p2 = currentCircuit.get(0);
		pta.testPair(p1, p2, iteratorDistances.next());
	}
	
	private final void evaluateDistances(Set<PointToArc> pointsOutsideCircuit, Circuit currentCircuit, int lastUpdateIndex) {
		for(PointToArc pOutsideHull : pointsOutsideCircuit) {
			Iterator<Point> iterator = currentCircuit.listIterator(lastUpdateIndex - 1);
			Iterator<Double> iteratorDistances = currentCircuit.getDistancesIterator(lastUpdateIndex - 1);
			Point p1 = iterator.next();
			Point p2 = iterator.next();
			Point p3;
			if(!iterator.hasNext())
				p3 = currentCircuit.get(0);
			else
				p3 = iterator.next();
			// Check if the node was candidate to a segment that was split in the last step
			if(pOutsideHull.p1 == p1 && pOutsideHull.p2 == p3) {
				// In this case, we have to re-evaluate distances to all segments of the circuit.
				//evaluateDistances(pOutsideHull, currentCircuit);
				pOutsideHull.p1 = null;
				pOutsideHull.testPair(p1, p2, iteratorDistances.next());
				pOutsideHull.testPair(p2, p3, iteratorDistances.next());
			} else {
				pOutsideHull.testPair(p1, p2, iteratorDistances.next());
				pOutsideHull.testPair(p2, p3, iteratorDistances.next());
			}
		}
	}
	
	private final int choosePointAndAdd(Set<PointToArc> pointsOutsideCircuit, Circuit currentCircuit) {
		PointToArc minPta = null;
		double minRatio = 0;
		for(PointToArc pta : pointsOutsideCircuit) {
			double ratio = 1.0 * ( pta.dist1 + pta.dist2 ) / pta.dist12;
			if(minPta == null || ratio < minRatio) {
				minRatio = ratio;
				minPta = pta;
			}
		}
		int insertIndex = currentCircuit.indexOf(minPta.p1)+1;
		currentCircuit.add(insertIndex, minPta.p);
		pointsOutsideCircuit.remove(minPta);
		return insertIndex;
	}

	@Override
	public String getName() {
		return "ConvexHull";
	}
	
	class PointToArc {
		public Point p, p1, p2;
		public double dist1, dist2, dist12;
		double minExpr;
		
		public void testPair(Point p1, Point p2, double dist12) {
			double dist1 = p1.distance(p);
			double dist2 = p2.distance(p);
			//double dist12 = p2.distance(p1);
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
		
		@Override
		protected Object clone() {
			PointToArc clone = new PointToArc();
			clone.p = p;
			clone.p1 = p1;
			clone.p2 = p2;
			clone.dist1 = dist1;
			clone.dist2 = dist2;
			clone.dist12 = dist12;
			clone.minExpr = minExpr;
			return clone;
		}
	}

}
