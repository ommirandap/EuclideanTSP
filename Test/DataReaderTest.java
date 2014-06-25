import static org.junit.Assert.*;

import org.junit.Test;

import cc4102.tarea3.geo.Country;
import cc4102.tarea3.geom.Point;
import cc4102.tarea3.io.DataReader;


public class DataReaderTest {

	@Test
	public void test() {
		DataReader dataReader = new DataReader(Country.TEST);
		dataReader.getData();
		assertEquals(dataReader.data.length, 3);
		assertEquals(dataReader.data[0], new Point(0,0));
		assertEquals(dataReader.data[1], new Point(3,0));
		assertEquals(dataReader.data[2], new Point(3,4));
	}

}
