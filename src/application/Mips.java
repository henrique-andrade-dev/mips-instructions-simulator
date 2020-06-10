package application;

public class Mips {
	private String[] _registers;

	public Mips() {
		this._registers = new String[32];
	}
	

	// example.entrySet().forEach(entry->{
	// 	System.out.println(entry.getKey() + " " + entry.getValue());  
	//  });
	
	public String[] getRegisters() {
		return this._registers;
	}
	
}
