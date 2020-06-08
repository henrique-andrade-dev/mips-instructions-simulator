package application;

import java.util.HashMap;

public class IFormat {
	private static final IFormat INSTANCE = new IFormat();
	private HashMap<String, String> binaries;

    public IFormat() {
    	this.initializeIFormat();
    }
    
    public static IFormat getInstance() {
    	return INSTANCE;
    }
    
    private void initializeIFormat() {
    	this.binaries = new HashMap<>();
        this.binaries.put("addi", "001000");
        this.binaries.put("neg", "000111");
        this.binaries.put("andi", "001100");
        this.binaries.put("ori", "001101");
        this.binaries.put("slti", "001010");
        this.binaries.put("lw", "100011");
        this.binaries.put("sw", "101011");
        this.binaries.put("beq", "000100");
        this.binaries.put("bne", "000101");
        this.binaries.put("nop", "000000");
    }

    public String get(String funct) {
        return this.binaries.get(funct);
    }
}
