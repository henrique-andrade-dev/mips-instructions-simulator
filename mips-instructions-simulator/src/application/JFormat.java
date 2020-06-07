package application;

import java.util.HashMap;

public class JFormat {
	HashMap<String, String> funct = new HashMap<>();

    public JFormat() {
        funct.put("j", "000010");
        funct.put("jal", "000011");
    }

    public String get(String param) {
        return funct.get(param);
    }
}
