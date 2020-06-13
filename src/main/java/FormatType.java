import java.util.HashMap;

public class FormatType {
	private static final FormatType INSTANCE = new FormatType();
	private HashMap<String, String> _types;

	private FormatType() {
		this.initializeFormatTypes();
	}
	
	public static FormatType getInstance() {
		return INSTANCE;
	}

	private void initializeFormatTypes() {
		this._types = new HashMap<>();
		this._types.put("add", "r");
		this._types.put("addi", "i");
		this._types.put("sub", "r");
		this._types.put("mult", "r");
		this._types.put("div", "r");
		this._types.put("neg", "i");
		this._types.put("and", "r");
		this._types.put("andi", "i");
		this._types.put("or", "r");
		this._types.put("ori", "i");
		this._types.put("xor", "r");
		this._types.put("nor", "r");
		this._types.put("slt", "r");
		this._types.put("slti", "i");
		this._types.put("sll", "r");
		this._types.put("srl", "r");
		this._types.put("lw", "i");
		this._types.put("sw", "i");
		this._types.put("beq", "i");
		this._types.put("bne", "i");
		this._types.put("jr", "r");
		this._types.put("j", "j");
		this._types.put("jal", "j");
		this._types.put("nop", "i");
	}

	public String get(String funct) {
		return this._types.get(funct);
	}
}
