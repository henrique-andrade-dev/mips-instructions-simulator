package application;

import java.util.HashMap;

public class Mips {
	// private String[] _registers;
	private HashMap<String, String> _registers;
	private HashMap<String, String> _memory;
	private String _registersFormatted;
	private String _memoryFormatted;

	public static final String PATH_REGISTER_FILE = "./assets/register.txt";
	public static final String PATH_MEMORY_FILE = "./assets/memory.txt";

	public Mips() {
		this.setRegisters();
		this.setMemory();
		this.setRegistersFormatted();
		this.setMemoryFormatted();
	}

	private void setRegisters() {
		this._registers = new HashMap<String, String>();
		this._registers.put("key", "value");
	}

	private void setMemory() {
		this._memory = new HashMap<String, String>();
		this._memory.put("key", "value");
	}

	private void setRegistersFormatted() {
		this._registers.entrySet().forEach(kayValuePair -> {
			this._registersFormatted += "\n" + kayValuePair.getKey() + " " + kayValuePair.getValue();
		});
	}

	public void setMemoryFormatted() {
		this._memory.entrySet().forEach(kayValuePair -> {
			this._memoryFormatted += "\n" + kayValuePair.getKey() + " " + kayValuePair.getValue();
		});
	}

	public HashMap<String, String> getRegisters() {
		return this._registers;
	}

	public HashMap<String, String> getMemory() {
		return this._memory;
	}

	public String getRegistersFormatted() {
		return this._registersFormatted;
	}

	public String getMemoryFormatted() {
		return this._memoryFormatted;
	}
}
