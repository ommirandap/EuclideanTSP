import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cc4102.tarea3.algorithm.NearestPointAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.geom.Point;

public class NearestPointTest {

	NearestPointAlgorithm NPA;
	TSPAlgorithmResults results;
	Point []points;
	
	@Before 
	public void initialize(){
		NPA = new NearestPointAlgorithm();
	}
	
	@Test
	public void testUnitSquare() {
		points = new Point[] { 
				new Point(0, 0), new Point(1, 1),
				new Point(0, 1), new Point(1, 0) 
				};
		results = NPA.run(points);
		assertEquals(4, results.length, 0);
	}
	
	@Test
	public void testBigSquare() {
		NPA = new NearestPointAlgorithm();
		int n = 10;
		Point[] points = new Point[2*n];
		// TODO Agregar los lados del cuadrado
		for(int i = 0; i < n; i++){
			points[i] = new Point(0, i);
			points[i + n] = new Point(n-1, i);
		}
		results = NPA.run(points);
		assertEquals(4*(n-1), results.length, 0);
	}

	@Test
	public void testLine() {
		NPA = new NearestPointAlgorithm();
		points = new Point[] { 
				new Point(0, 0), new Point(0, 1),
				new Point(0, 2), new Point(0, 3), 
				new Point(0, 4), new Point(0, 5) 
				};
		results = NPA.run(points);
		assertEquals(10, results.length, 0.001);
	}
	
	@Test
	public void testRectangle() {
		NPA = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0, 1),
				new Point(0, 2), new Point(1, 2), 
				new Point(2, 2), new Point(3, 2),
				new Point(3, 1), new Point(3, 0),
				new Point(2, 0), new Point(1, 0)
				};
		TSPAlgorithmResults results = NPA.run(points);
		assertEquals(10, results.length, 0.0);
	}
	
}
