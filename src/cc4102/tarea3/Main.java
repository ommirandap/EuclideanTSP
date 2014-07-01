package cc4102.tarea3;

import cc4102.tarea3.algorithm.ConvexHullAlgorithm;
import cc4102.tarea3.algorithm.MSTAlgorithm;
import cc4102.tarea3.algorithm.NearestPointAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.experiment.Experiment;
import cc4102.tarea3.experiment.TSPExperiment;
import cc4102.tarea3.geo.Country;
import cc4102.tarea3.io.DataReader;
import cc4102.tarea3.io.log.TSPExperimentLogger;

public class Main {
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		TSPExperimentLogger tspLogger = new TSPExperimentLogger();
		tspLogger.startLogging();
		TSPAlgorithm[] algorithms = new TSPAlgorithm[] {
				/*new ConvexHullAlgorithm(), new MSTAlgorithm(), */new NearestPointAlgorithm()
		}; 
		
		for(Country country : Country.values()) {
			System.out.println("\n===COUNTRY: "+country.getName());
			for(TSPAlgorithm algorithm : algorithms) {
				System.out.println("\n\tAlgorithm "+algorithm.getName());
				Experiment experiment = new TSPExperiment(algorithm, country);
				experiment.run();
				tspLogger.logResults(experiment);
			}
		}
		System.out.println("FINISHED!");
	}
}
