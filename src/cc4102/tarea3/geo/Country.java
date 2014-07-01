package cc4102.tarea3.geo;
public enum Country{
	OMAN("OMAN", 86891), 
	DJIBOUTI("DJIBOUTI", 6656),
	QATAR("QATAR", 9352), 
	URUGUAY("URUGUAY", 79114), 
	FINLAND("FINLAND", 520527),
	GREECE("GREECE", 300899),
	SWEDEN("SWEDEN", 855597),
	VIETNAM("VIETNAM", 569288), 
	WESTERNSAHARA("WESTERNSAHARA", 27603),
	ZIMBAWE("ZIMBAWE", 95345),
	ITALY("ITALY", 557315), 
	JAPAN("JAPAN", 491924), 
	CANADA("CANADA", 1290319), 
	TEST("TEST", 16)
	;

	private final String name;
	private final double knownBest;

	private Country(String name, double knownBest){
		this.name = name;
		this.knownBest = knownBest;
	}

	public String getName(){
		return this.name;
	}

	public double getKnownBest() {
		return knownBest;
	}
}