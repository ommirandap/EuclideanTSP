package cc4102.tarea3.log;

import cc4102.tarea3.experiment.Experiment;
import cc4102.tarea3.experiment.TSPExperiment;

public class TSPExperimentLogger extends Logger {
	
	public String[] getColumns() {
		return new String[]{"Mem type", "Start time", "End time", "Time taken", "Nodes", "Leaves", "Exponent", "Height", "P generator", "Strategy"};
	}
	
	@Override
	public String getLogBasename() {
		return "build-";
	}
	
	@Override
	public String[] getFields(Experiment experiment) {
		if(!(experiment instanceof TSPExperiment))
			throw new RuntimeException("Instance of experiment not valid: " + experiment.getClass().getSimpleName());
		TSPExperiment bExperiment = (TSPExperiment)experiment;
		return new String[] {
				"PRIMARIA",
				""+bExperiment.getInitTime(),
				""+bExperiment.getEndTime(),
				""+bExperiment.getTimeTaken(),
				""+bExperiment.getPerformance(),
				""+bExperiment.getTspAlgorithm().getName()
		};
	}
}
