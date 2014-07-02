package cc4102.tarea3.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import cc4102.tarea3.adt.Circuit;
import cc4102.tarea3.geom.Point;

public class ConvexHullCalculator {

	// builds a convex hull around the given points
	// using the Graham scan algorithm
	public Circuit buildHull(Point[] points) {
		if (points.length < 3)
			return null;

		// Find the point with the least y, then x coordinate
		Point p0 = null;
		for (int i = 0; i < points.length; ++i) {
			Point curr = points[i];
			if (p0 == null || curr.getY() < p0.getY() || (curr.getY() == p0.getY() && curr.getX() < p0.getX()))
				p0 = curr;
		}

		// Sort the points by angle around p0
		class PointAngleComparator implements Comparator<Point> {
			private Point p0;

			public PointAngleComparator(Point p0) {
				this.p0 = p0;
			}

			private double angle(Point pt) {
				return Math.atan2(pt.getY() - p0.getY(), pt.getX() - p0.getX());
			}

			public int compare(Point p1, Point p2) {
				double a1 = angle(p1), a2 = angle(p2);
				if (a1 > a2)
					return 1;
				if (a1 < a2)
					return -1;
				return Double.compare(p0.distance2(p1),
						p0.distance2(p2));
			}
		}
		Arrays.sort(points, 0, points.length, new PointAngleComparator(p0));

		// build the hull
		
		Stack<Point> hull = new Stack<Point>();
		hull.push(points[0]);
		hull.push(points[1]);
		hull.add(points[2]);
		for (int i = 3; i < points.length; ++i) {
			Point cur = points[i];
			while (hull.size() >= 3) {
				Point snd = hull.get(hull.size() - 2);
				Point top = hull.peek();
				double crossproduct = top.substract(snd).crossProduct(cur.substract(snd));
				if (crossproduct > 0)
					break;
				hull.pop();
			}
			hull.push(cur);
		}
		
		Circuit circuit = new Circuit(hull);
		circuit.calculateDistances();
		return circuit;
	}

}