package cc4102.tarea3.io.log;

import cc4102.tarea3.experiment.Experiment;
import cc4102.tarea3.experiment.TSPExperiment;

public class TSPExperimentLogger extends Logger {
	
	public String[] getColumns() {
		return new String[]{"Country", "Algorithm", "Start time", "End time", "Time taken", "Length", "Performance", "Cities"};
	}
	
	@Override
	public String getLogBasename() {
		return "tsp-";
	}
	
	@Override
	public String[] getFields(Experiment experiment) {
		if(!(experiment instanceof TSPExperiment))
			throw new RuntimeException("Instance of experiment not valid: " + experiment.getClass().getSimpleName());
		TSPExperiment bExperiment = (TSPExperiment)experiment;
		return new String[] {
				bExperiment.getCountry().getName(),
				bExperiment.getTspAlgorithm().getName(),
				""+bExperiment.getInitTime(),
				""+bExperiment.getEndTime(),
				""+bExperiment.getTimeTaken(),
				""+bExperiment.getResults().length,
				""+bExperiment.getPerformance(),
				""+bExperiment.getCitiesCount()
		};
	}
}
