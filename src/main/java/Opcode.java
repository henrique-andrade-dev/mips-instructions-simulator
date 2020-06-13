import java.util.HashMap;

public class Opcode {
	private static final Opcode INSTANCE = new Opcode();
	private HashMap<String, String> _binaries;

	private Opcode() {
		this.initializeOpcode();
	}

	public static Opcode getInstance() {
		return INSTANCE;
	}

	private void initializeOpcode() {
		this._binaries = new HashMap<>();

		// IFormat
		this._binaries.put("addi", "001000");
		this._binaries.put("neg", "000111");
		this._binaries.put("andi", "001100");
		this._binaries.put("ori", "001101");
		this._binaries.put("slti", "001010");
		this._binaries.put("lw", "100011");
		this._binaries.put("sw", "101011");
		this._binaries.put("beq", "000100");
		this._binaries.put("bne", "000101");
		this._binaries.put("nop", "000000");

		// RFormat
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

		// JFormat
		this._binaries.put("j", "000010");
		this._binaries.put("jal", "000011");
	}

	public String get(String funct) {
		return this._binaries.get(funct);
	}
}
