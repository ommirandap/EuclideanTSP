import java.io.*;

public class DataReader{
	
	private String path;
	public Point [] data;

	public DataReader(Country country){
		this.path = "Data/DATA-"+country.getName()+".tsp";
	}

	public Point getData(){
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

		String line = br.readLine();
		int nPoints = Integer.parseInt(line);
		this.data = new Point[nPoints];

		for(int i = 0; ((line = br.readLine())!=null); i++){
			data[i] = Double.parseDouble(line);
		}
	}

	public void printData(){
		for(int i = 0; i < data.length; i++){
			System.out.print(i + "->" + data[i]);
		}
	}

}