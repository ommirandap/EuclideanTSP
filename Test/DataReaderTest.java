import static org.junit.Assert.*;

import org.junit.Test;

import cc4102.tarea3.geo.Country;
import cc4102.tarea3.geom.Point;
import cc4102.tarea3.io.DataReader;


public class DataReaderTest {

	@Test
	public void test() {
		DataReader dataReader = new DataReader();
		Point[] data = dataReader.getData(Country.TEST);
		assertEquals(data.length, 3);
		assertEquals(data[0], new Point(0,0));
		assertEquals(data[1], new Point(3,0));
		assertEquals(data[2], new Point(3,4));
	}

}
