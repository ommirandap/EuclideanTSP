package cc4102.tarea3.experiment;


import cc4102.tarea3.algorithm.TSPAlgorithm;
import cc4102.tarea3.geo.Country;
import cc4102.tarea3.io.DataReader;

public class TSPExperiment extends Experiment {
	private TSPAlgorithm tspAlgorithm;
	private Country country;
	
	public TSPExperiment(TSPAlgorithm tspAlgorithm, Country country) {
		this.tspAlgorithm = tspAlgorithm;
		this.country = country;
	}
	
	public void runExperiment() {
		DataReader dataReader = new DataReader(country);
		dataReader.getData();
		tspAlgorithm.run(dataReader.data);
	}
	
	protected void onPostExperiment() {
	}
	
	public TSPAlgorithm getTspAlgorithm() {
		return tspAlgorithm;
	}
	
	public double getPerformance() {
		return 1.0*getTimeTaken()/country.getKnownBest();
	}

	@Override
	protected void onPreExperiment() {
	}
	
}