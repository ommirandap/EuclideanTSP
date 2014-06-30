import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cc4102.tarea3.algorithm.NearestPointAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.geom.Point;

public class NearestPointTest {

	// public static void main(String[] args) {
	// NearestPointAlgorithm npa = new NearestPointAlgorithm();
	// Point[] points = new Point[] { new Point(0, 0), new Point(1, 1),
	// new Point(0, 1), new Point(1, 0) };
	// TSPAlgorithmResults results = npa.run(points);
	// // assertEquals(4, results.length, 0);
	// for (Point p : results.circuit) {
	// System.out.print(p.toString());
	// }
	// }

	@Test
	public void testUnitSquare() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(1, 1),
				new Point(0, 1), new Point(1, 0) 
				};
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(4, results.length, 0);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();
	}
	
	@Test
	public void testBigSquare() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		int n = 100;
		Point[] points = new Point[2*n];
		for(int i = 0; i < n; i++){
			points[i] = new Point(0, i);
			points[i + n] = new Point(n-1, i);
		}
		
		TSPAlgorithmResults results = npa.run(points);
		
		System.out.println("BigSquare");
		for (Point p : results.circuit)
			System.out.print(p.toString());
		System.out.println();
		
		assertEquals(4*(n-1), results.length, 0);
	}

	@Test
	public void test1() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0.5, 0.3),
				new Point(1, -0.3), new Point(-0.3, -2) 
				};
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(5.5266, results.length, 0.001);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();

	}

	@Test
	public void test2() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0.25, 0),
				new Point(0.5, 0.3), new Point(1, -0.3), 
				new Point(-0.3, -2) 
				};
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(5.584, results.length, 0.001);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();

	}

	@Test
	public void test3() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0.25, 0),
				new Point(0.5, 0.3), new Point(1, -0.3), 
				new Point(-0.3, -2),
				new Point(-0.5, -0.5) };
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(5.782, results.length, 0.001);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();

	}

	@Test
	public void test4() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0.25, 0),
				new Point(0.5, 0.3), new Point(1, -0.3), 
				new Point(-0.3, -2), new Point(0, -0.5) 
				};
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(5.5913, results.length, 0.001);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();

	}

	@Test
	public void testLine() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0, 1),
				new Point(0, 2), new Point(0, 3), 
				new Point(0, 4), new Point(0, 5) 
				};
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(10, results.length, 0.001);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();

	}
	
	@Test
	public void testRectangle() {
		NearestPointAlgorithm npa = new NearestPointAlgorithm();
		Point[] points = new Point[] { 
				new Point(0, 0), new Point(0, 1),
				new Point(0, 2), new Point(1, 2), 
				new Point(2, 2), new Point(3, 2),
				new Point(3, 1), new Point(3, 0),
				new Point(2, 0), new Point(1, 0)
				};
		TSPAlgorithmResults results = npa.run(points);
		assertEquals(10, results.length, 0.0);
		for (Point p : results.circuit) {
			System.out.print(p.toString());
		}
		System.out.println();

	}
	
}
