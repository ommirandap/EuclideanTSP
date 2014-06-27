import static org.junit.Assert.*;

import org.junit.Test;

import cc4102.tarea3.algorithm.ConvexHullAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.geom.Point;


public class ConvexHullAlgorithmTests {

	@Test
	public void test() {
		ConvexHullAlgorithm cha = new ConvexHullAlgorithm();
		Point[] points = new Point[] {new Point(0,0), new Point(1,1), new Point(0,1), new Point(1,0)};
		TSPAlgorithmResults results = cha.run(points);
		assertEquals(4, results.length, 0);
		
		points = new Point[] {new Point(0,0), new Point(0.5,0.3), new Point(1,-0.3), new Point(-0.3,-2)};
		results = cha.run(points);
		assertEquals(5.5266, results.length, 0.001);
		
		points = new Point[] {new Point(0,0), new Point(0.25,0), new Point(0.5,0.3), new Point(1,-0.3), new Point(-0.3,-2)};
		results = cha.run(points);
		assertEquals(5.584, results.length, 0.001);
		
		points = new Point[] {new Point(0,0), new Point(0.25,0), new Point(0.5,0.3), new Point(1,-0.3), new Point(-0.3,-2), new Point(-0.5,-0.5)};
		results = cha.run(points);
		assertEquals(5.782, results.length, 0.001);
		
		points = new Point[] {new Point(0,0), new Point(0.25,0), new Point(0.5,0.3), new Point(1,-0.3), new Point(-0.3,-2), new Point(0,-0.5)};
		results = cha.run(points);
		assertEquals(5.5913, results.length, 0.001);
	}

}
