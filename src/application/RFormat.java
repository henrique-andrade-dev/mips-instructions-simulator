package application;

import java.util.HashMap;

public class RFormat {
	private static final RFormat INSTANCE = new RFormat();
	private HashMap<String, String> _binaries;

	private RFormat() {
		this.initializeRFormat();
	}
	
	public static RFormat getInstance() {
		return INSTANCE;
	}
	
	private void initializeRFormat() {
		this._binaries = new HashMap<>();
		this._binaries.put("add", "100000");
		this._binaries.put("sub", "100010");
		this._binaries.put("mult", "011000");
		this._binaries.put("div", "011010");
		this._binaries.put("and", "100100");
		this._binaries.put("or", "100101");
		this._binaries.put("xor", "100110");
		this._binaries.put("nor", "100111");
		this._binaries.put("slt", "101010");
		this._binaries.put("sll", "000000");
		this._binaries.put("srl", "000010");
		this._binaries.put("jr", "001000");
	}

	public String get(String funct) {
		return this._binaries.get(funct);
	}
}
