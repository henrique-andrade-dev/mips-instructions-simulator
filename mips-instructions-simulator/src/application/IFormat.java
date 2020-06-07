package application;

import java.util.HashMap;

public class IFormat {
	HashMap<String, String> funct = new HashMap<>();

    public IFormat() {
        funct.put("addi", "001000");
        funct.put("neg", "000111");
        funct.put("andi", "001100");
        funct.put("ori", "001101");
        funct.put("slti", "001010");
        funct.put("lw", "100011");
        funct.put("sw", "101011");
        funct.put("beq", "000100");
        funct.put("bne", "000101");
        funct.put("nop", "000000");
    }

    public String get(String param) {
        return funct.get(param);
    }
}
