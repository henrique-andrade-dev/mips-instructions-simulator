package application;

import java.util.HashMap;

public class Mips {
	private HashMap<String, String> _registers;
	private HashMap<String, String> _memory;
	private String _registersFormatted;
	private String _memoryFormatted;

	public Mips() {
		this.initializeRegisters();
		this.initializeMemory();
		this.initializeRegistersFormatted();
		this.initializeMemoryFormatted();
	}

	//region Initializers
	private void initializeRegisters() {
		this._registers = new HashMap<String, String>();

		for (String key : Constants.REGISTER_KEYS) {
			this._registers.put(key, "00000000");
		}
	}

	private void initializeMemory() {
		this._memory = new HashMap<String, String>();
	}

	private void initializeRegistersFormatted() {
		this._registers.entrySet().forEach(kayValuePair -> {
			this._registersFormatted += "\n" + kayValuePair.getKey() + " " + kayValuePair.getValue();
		});
	}

	private void initializeMemoryFormatted() {
		this._memory.entrySet().forEach(kayValuePair -> {
			this._memoryFormatted += "\n" + kayValuePair.getKey() + " " + kayValuePair.getValue();
		});
	}
	//endregion

	//region Getters
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
	//endregion

	public void setRegister(String register, String value) {
		if (isRegisterValid(register) && isValueValid(value)) {
			this._registers.put(register, value);
		}
	}

	public void setMemory(String address, String value) {
		if (isAddressValid(address) && isValueValid(value)) {
			this._memory.put(address, value);
		}
	}

	//region Modularize
	public boolean isRegisterValid(String register) {
		return this._registers.containsKey(register);
	}

	public boolean isAddressValid(String address) {
		int lineAddress = Integer.parseInt(address);

		return isBetweenLimits(lineAddress) && isMultipleOf16(lineAddress);
	}

	public boolean isBetweenLimits(int lineAddress) {
		return lineAddress >= Constants.MINIMUM_MEMORY_LINE_ADDRESS && lineAddress <= Constants.MAXIMUM_MEMORY_LINE_ADDRESS;
	}

	public boolean isMultipleOf16(int lineAddress) {
		return lineAddress % 16 == 0;
	}

	public boolean isValueValid(String value) {
		return value.length() <= Constants.MAXIMUM_VALUE_LENGTH;
	}
	//endregion
}
