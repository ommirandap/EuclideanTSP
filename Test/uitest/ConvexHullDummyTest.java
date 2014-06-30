package uitest;

import cc4102.tarea3.adt.Circuit;
import cc4102.tarea3.algorithm.ConvexHullCalculator;
import cc4102.tarea3.algorithm.TSPAlgorithm;
import cc4102.tarea3.geom.Point;

public class ConvexHullDummyTest implements TSPAlgorithm {

	@Override
	public TSPAlgorithmResults run(Point[] pointList) {
		ConvexHullCalculator convexHullCalculator = new ConvexHullCalculator();
		Circuit currentCircuit = convexHullCalculator.buildHull(pointList);
		TSPAlgorithmResults result = new TSPAlgorithmResults();
		result.circuit = currentCircuit;
		return result;
	}

	@Override
	public String getName() {
		return "Dummy";
	}

}
