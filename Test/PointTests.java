

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cc4102.tarea3.geom.Point;

public class PointTests {

	@Test
	public void basicPointTest() {
		Point p = new Point(23, 4);
		assertEquals(23, p.getX(), 0);
		assertEquals(4, p.getY(), 0);
		
		p = new Point(6, 6);
		assertEquals(6, p.getX(), 0);
		assertEquals(6, p.getY(), 0);
	}

	@Test
	public void equalPoint() {
		Point p = new Point(23, 4);
		Point q = new Point(23, 4);
		assertEquals(p, q);
	}

	@Test
	public void notEqualPoint() {
		Point p = new Point(23, 4);
		Point q = new Point(6, 6);
		assertNotEquals(p, q);
	}
	
	@Test
	public void distances() {
		Point p = new Point(5,2);
		assertEquals(5d, p.distance(new Point(2,-2)),0);
	}
	
	@Test
	public void circuitLength() {
		List<Point> path = new ArrayList<Point>();
		path.add(new Point(0,0));
		path.add(new Point(-1,0));
		path.add(new Point(-1,-1));
		path.add(new Point(0,-1));
		assertEquals(4, Point.getCircuitLength(path), 0);
	}


}
