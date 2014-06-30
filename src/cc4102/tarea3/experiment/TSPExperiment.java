package cc4102.tarea3.experiment;


import cc4102.tarea3.algorithm.TSPAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.geo.Country;
import cc4102.tarea3.geom.Point;
import cc4102.tarea3.io.DataReader;

public class TSPExperiment extends Experiment {
	private TSPAlgorithm tspAlgorithm;
	private Country country;
	private TSPAlgorithmResults results;
	private int citiesCount;
	
	public TSPExperiment(TSPAlgorithm tspAlgorithm, Country country) {
		this.tspAlgorithm = tspAlgorithm;
		this.country = country;
	}
	
	public void runExperiment() {
		DataReader dataReader = new DataReader();
		Point[] data = dataReader.getData(country);
		citiesCount = data.length;
		results = tspAlgorithm.run(data);
	}
	
	public TSPAlgorithm getTspAlgorithm() {
		return tspAlgorithm;
	}
	
	public double getPerformance() {
		return 1.0*getResults().length/country.getKnownBest();
	}
	
	public Country getCountry() {
		return country;
	}

	@Override
	protected void onPreExperiment() {
	}
	
	protected void onPostExperiment() {
	}

	public TSPAlgorithmResults getResults() {
		return results;
	}
	
	public int getCitiesCount() {
		return citiesCount;
	}
}