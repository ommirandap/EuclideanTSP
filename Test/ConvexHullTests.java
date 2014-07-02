import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cc4102.tarea3.algorithm.ConvexHullCalculator;
import cc4102.tarea3.geom.Point;


public class ConvexHullTests {

	@Test
	public void testBuild() {
		Point[] points = new Point[]{
				new Point(0,0),
				new Point(1,1),
				new Point(0,1),
				new Point(0.7,0.1),
				new Point(1,0),
				new Point(0.5,0.5)
		};
		ConvexHullCalculator convexHull = new ConvexHullCalculator();
		List<Point> hull = convexHull.buildHull(points);
		List<Point> expectedHull = new ArrayList<Point>();
		expectedHull.add(new Point(0,0));
		expectedHull.add(new Point(1,0));
		expectedHull.add(new Point(1,1));
		expectedHull.add(new Point(0,1));
		assertEquals(hull, expectedHull);
	}

}
