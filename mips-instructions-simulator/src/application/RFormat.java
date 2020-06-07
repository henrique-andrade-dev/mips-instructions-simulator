package application;

import java.util.HashMap;

public class RFormat {
	HashMap<String, String> funct = new HashMap<>();

	public RFormat() {
		funct.put("add", "100000");
		funct.put("sub", "100010");
		funct.put("mult", "011000");
		funct.put("div", "011010");
		funct.put("and", "100100");
		funct.put("or", "100101");
		funct.put("xor", "100110");
		funct.put("nor", "100111");
		funct.put("slt", "101010");
		funct.put("sll", "000000");
		funct.put("srl", "000010");
		funct.put("jr", "001000");
	}

	public String get(String param) {
		return funct.get(param);
	}
}
