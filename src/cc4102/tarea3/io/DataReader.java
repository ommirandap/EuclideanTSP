package cc4102.tarea3.io;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import cc4102.tarea3.geo.Country;
import cc4102.tarea3.geom.Point;

public class DataReader {

	public Point[] getData(Country country){
		String path = "./Data/DATA-" + country.getName() + ".tsp";
		Point[] data = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {

			String line = br.readLine();
			int nPoints = Integer.parseInt(line);
			data = new Point[nPoints];

			for(int i = 0; ((line = br.readLine())!=null); i++){
				String [] aux = line.split(" ");
				int index = Integer.parseInt(aux[0]) - 1;
				double xCoord = Double.parseDouble(aux[1]);
				double yCoord = Double.parseDouble(aux[2]);
				data[index] = new Point(xCoord, yCoord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void printData(Point[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(i + "->" + data[i].toString());
		}
	}

}