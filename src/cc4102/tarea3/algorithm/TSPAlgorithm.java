package cc4102.tarea3.algorithm;

import cc4102.tarea3.geom.Point;

public interface TSPAlgorithm {
	
	public double run(Point[] pointList);

	public String getName();
	
}
