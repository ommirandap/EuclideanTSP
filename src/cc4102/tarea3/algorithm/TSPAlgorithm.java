package cc4102.tarea3.algorithm;

import java.util.List;

import cc4102.tarea3.geom.Point;

public interface TSPAlgorithm {
	
	public TSPAlgorithmResults run(Point[] pointList);

	public String getName();
	
	public class TSPAlgorithmResults {
		public List<Point> circuit;
		public double length;
	}
	
}
