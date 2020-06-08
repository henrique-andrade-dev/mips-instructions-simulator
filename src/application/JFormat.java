package application;

import java.util.HashMap;

public class JFormat {
	private static final JFormat INSTANCE = new JFormat();
	private HashMap<String, String> _binaries;

    public JFormat() {
    	initializeJFormat();
    }
    
    public static JFormat getInstance() {
    	return INSTANCE;
    }

    private void initializeJFormat() {
    	this._binaries = new HashMap<>();
        this._binaries.put("j", "000010");
        this._binaries.put("jal", "000011");
    }
    
    public String get(String funct) {
        return this._binaries.get(funct);
    }
}
