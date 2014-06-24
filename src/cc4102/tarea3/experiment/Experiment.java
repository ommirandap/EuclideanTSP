package cc4102.tarea3.experiment;



public abstract class Experiment {
	private long startTime;
	private long endTime;
	
	public final void run() {
		onPreExperiment();
		startTime = System.nanoTime();
		runExperiment();
		endTime = System.nanoTime();
		
		onPostExperiment();
	}
	
	protected abstract void onPreExperiment();
	
	protected abstract void onPostExperiment();
	
	protected abstract void runExperiment();
	
	public long getInitTime() {
		return startTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public long getTimeTaken() {
		return endTime-startTime;
	}

}
