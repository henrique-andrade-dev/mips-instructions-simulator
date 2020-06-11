package application;

import java.util.HashMap;

import exceptions.CustomException;

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

	// region Initializers
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
		this._registers.entrySet().forEach(keyValuePair -> {
			this._registersFormatted += "\n" + keyValuePair.getKey() + " " + keyValuePair.getValue();
		});
	}

	private void initializeMemoryFormatted() {
		this._memory.entrySet().forEach(keyValuePair -> {
			this._memoryFormatted += "\n" + keyValuePair.getKey() + " " + keyValuePair.getValue();
		});
	}
	// endregion

	// region Getters
	public HashMap<String, String> getRegisters() {
		return this._registers;
	}

	public String getValueRegister(String register) throws CustomException {
		if (isRegisterValid(register))
			return this._registers.get(register);
		throw new CustomException(String.format("O registrador %s é inválido", register));
	}

	public String getValueMemory(String address) throws CustomException {
		if(!isAddressValid(address))
		throw new CustomException(String.format("O endereço de memória 0x%s é inválido", address));

		if (this._memory.containsKey(address)) return this._memory.get(address);
		else return "00000000";
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
	// endregion

	public void setRegister(String register, String value) {
		if (isRegisterValid(register)) {
			this._registers.put(register, Utils.formatString(value, Constants.REGISTER_LENGTH));
		}
	}

	public void setMemory(String address, String value) {
		if (isAddressValid(address)) {
			this._memory.put(address, Utils.formatString(value, Constants.MEMORY_LENGTH));
		}
	}

	// region Modularize
	public boolean isRegisterValid(String register) {
		return this._registers.containsKey(register);
	}

	public boolean isAddressValid(String address) {
		Long lineAddress = Utils.hexToLong(address);

		return isBetweenLimits(lineAddress) && isMultipleOf4(lineAddress);
	}

	public boolean isBetweenLimits(Long lineAddress) {
		return lineAddress >= Constants.MINIMUM_MEMORY_LINE_ADDRESS
				&& lineAddress <= Constants.MAXIMUM_MEMORY_LINE_ADDRESS;
	}

	public boolean isMultipleOf4(Long lineAddress) {
		return lineAddress % 4 == 0;
	}
	// endregion
}
