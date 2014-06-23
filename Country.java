public enum Country{

	CANADA("CANADA"), 
	DJIBOUTI("DJIBOUTI"),
	FINLAND("FINLAND"),
	GREECE("GREECE"),
	ITALY("ITALY"), 
	JAPAN("JAPAN"), 
	OMAN("OMAN"), 
	QATAR("QATAR"), 
	SWEDEN("SWEDEN"),
	URUGUAY("URUGUAY"), 
	VIETNAM("VIETNAM"), 
	WESTERNSAHARA("WESTERNSAHARA"), 
	ZIMBAWE("ZIMBAWE"),
	;

	private final String name;

	private Country(String name){
		this.name = name;
	}

	public String getName(){
		return  this.name;
	}

	static public void main(String[] args){
		for(Country c : Country.values()){
			System.out.println("ble " + c.getName());
		}
	}
}