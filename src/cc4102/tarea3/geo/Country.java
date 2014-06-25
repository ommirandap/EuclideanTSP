package cc4102.tarea3.geo;
public enum Country{
	CANADA("CANADA", 1290319), 
	DJIBOUTI("DJIBOUTI", 6656),
	FINLAND("FINLAND", 520527),
	GREECE("GREECE", 300899),
	ITALY("ITALY", 557315), 
	JAPAN("JAPAN", 491924), 
	OMAN("OMAN", 86891), 
	QATAR("QATAR", 9352), 
	SWEDEN("SWEDEN", 855597),
	URUGUAY("URUGUAY", 79114), 
	VIETNAM("VIETNAM", 569288), 
	WESTERNSAHARA("WESTERNSAHARA", 27603), 
	ZIMBAWE("ZIMBAWE", 95345),
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

	static public void main(String[] args){
		for(Country c : Country.values()){
			System.out.println("ble " + c.getName());
		}
	}

	public double getKnownBest() {
		return knownBest;
	}
}