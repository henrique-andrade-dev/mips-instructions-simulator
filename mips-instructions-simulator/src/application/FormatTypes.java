package application;

import java.util.HashMap;

public class FormatTypes {
	HashMap<String, String> funct = new HashMap<>();

    public FormatTypes() {
        funct.put("add", "r");
        funct.put("addi", "i");
        funct.put("sub", "r");
        funct.put("mult", "r");
        funct.put("div", "r");
        funct.put("neg", "i");
        funct.put("and", "r");
        funct.put("andi", "i");
        funct.put("or", "r");
        funct.put("ori", "i");
        funct.put("xor", "r");
        funct.put("nor", "r");
        funct.put("slt", "r");
        funct.put("slti", "i");
        funct.put("sll", "r");
        funct.put("srl", "r");
        funct.put("lw", "i");
        funct.put("sw", "i");
        funct.put("beq", "i");
        funct.put("bne", "i");
        funct.put("jr", "r");
        funct.put("j", "j");
        funct.put("jal", "j");
        funct.put("nop", "i");
    }

    public String get(String param) {
        return funct.get(param);
    }
}
