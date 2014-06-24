import java.io.*;

public class DataReader {
	
	private String path;
	public Point [] data;

	public DataReader(Country country){
		this.path = "./Data/DATA-"+country.getName()+".tsp";
		System.out.println(path);
	}

	public void getData(){
		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

			String line = br.readLine();
			int nPoints = Integer.parseInt(line);
			this.data = new Point[nPoints];

			for(int i = 0; ((line = br.readLine())!=null); i++){
				String [] aux = line.split(" ");
				int index = Integer.parseInt(aux[0]) - 1;
				double xCoord = Double.parseDouble(aux[1]);
				double yCoord = Double.parseDouble(aux[2]);
				this.data[index] = new Point(xCoord, yCoord);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public void printData(){
		for(int i = 0; i < data.length; i++){
			System.out.println(i + "->" + data[i].toString());
		}
	}

	static public void main(String []args){

		DataReader dr = new DataReader(Country.SWEDEN);
		dr.getData();
		dr.printData();

	}

}