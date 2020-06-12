package application;

import java.util.Arrays;
import java.util.HashMap;

import exceptions.CustomException;

public class Mips {
	private static Mips INSTANCE = new Mips();
	private HashMap<String, String> _registers;
	private HashMap<String, String> _memory;

	private Mips() {
		this.initializeRegisters();
		this.initializeMemory();
	}

	public static Mips getInstance() {
		return INSTANCE;
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

		for (int i = 0; i < Constants.LINES_OF_MEMORY_LENGTH; i += 1) {
			String key = Utils.completeWithZeros(Utils.intToHex(i), Constants.MEMORY_ADDRESS_LENGTH);

			this._memory.put(key, "00000000");
		}
	}
	// endregion

	// region Getters
	public HashMap<String, String> getRegisters() {
		return this._registers;
	}

	public HashMap<String, String> getMemory() {
		return this._memory;
	}

	public String getValueRegister(String register) throws CustomException {
		if (isRegisterValid(register)) {
			return this._registers.get(register);
		}

		throw new CustomException(String.format("Register \"%s\" is not valid.", register));
	}

	public String getValueMemory(String address) throws CustomException {
		if (!isAddressValid(address)) {
			throw new CustomException(String.format("Memory address 0x%s is not valid.", address));
		}

		return this._memory.containsKey(address) ? this._memory.get(address) : "00000000";
	}

	public String getRegistersFormatted() throws CustomException {
		String registersFormatted = "";

		for (int i = 0; i < Constants.REGISTER_KEYS.length; i++) {
			registersFormatted += Constants.REGISTER_KEYS[i] + " 0x" + getValueRegister(Constants.REGISTER_KEYS[i]) + "\n";
		}

		return registersFormatted;
	}

	public String getMemoryFormatted() {
		String memoryFormatted = "";

		for (int i = 0; i < this._memory.size(); i++) {			
			String key = Utils.completeWithZeros(Utils.intToHex(i), Constants.MEMORY_ADDRESS_LENGTH);

			memoryFormatted += "0x" + key + " 0x" + this._memory.get(key) + "\n";
		}

		return memoryFormatted;
	}
	// endregion

	// region Setters
	public void setRegister(String register, String value) {
		if (isRegisterValid(register)) {
			this._registers.put(register, Utils.completeWithZeros(value, Constants.REGISTER_LENGTH));
		}
	}

	public void setMemory(String address, String value) {
		if (isAddressValid(address)) {
			this._memory.put(Utils.completeWithZeros(address, Constants.MEMORY_ADDRESS_LENGTH), Utils.completeWithZeros(value, Constants.MEMORY_LENGTH));
		}
	}
	// endregion

	// region Validations
	public boolean isRegisterValid(String register) {
		return this._registers.containsKey(register);
	}

	public boolean isAddressValid(String address) {
		Long lineAddress = Utils.hexToLong(address);

		return isBetweenLimits(lineAddress) && isMultipleOf4(lineAddress);
	}

	public boolean isBetweenLimits(Long lineAddress) {
		return lineAddress >= Constants.MINIMUM_MEMORY_LINE_ADDRESS && lineAddress <= Constants.MAXIMUM_MEMORY_LINE_ADDRESS;
	}

	public boolean isMultipleOf4(Long lineAddress) {
		return lineAddress % 4 == 0;
	}
	// endregion

	public int execute(int programCounter, String instruction) throws CustomException {
        String[] lineItems = instruction.split("\\s+|,|\\(|\\)");
        lineItems = Arrays.stream(lineItems).filter(s -> !s.isEmpty()).toArray(String[]::new);

        String funct = lineItems[0];

        switch (funct) {
            case "addi": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long value = Utils.hexToLong(s1);
                String result = Utils.longToHex(value + constant);

                setRegister(destination, result);

                break;
            }
            case "neg": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                Long value = Utils.hexToLong(s1);
                String result = Utils.longToHex(value * -1);

                setRegister(destination, result);

                break;
            }
            case "andi": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                Long value = Utils.hexToLong(s1);
                int constant = Integer.parseInt(lineItems[3]);
                String valueString = Long.toBinaryString(value);
                String constantBinary = Integer.toBinaryString(constant);
                String resultString = "";

                for (int i = 0; i < valueString.length(); i += 1) {
                    if (valueString.charAt(i) == '1' && constantBinary.charAt(i) == '1') {
                        resultString += "1";
                    }
                    else {
                        resultString += "0";
                    }
                }

                setRegister(destination, Utils.binaryToHex(resultString));

                break;
            }
            case "ori": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                Long value = Utils.hexToLong(s1);
                int constant = Integer.parseInt(lineItems[3]);
                String valueString = Long.toBinaryString(value);
                String constantBinary = Integer.toBinaryString(constant);
                String resultString = "";

                for (int i = 0; i < valueString.length(); i += 1) {
                    if (valueString.charAt(i) == '1' || constantBinary.charAt(i) == '1') {
                        resultString += "1";
                    }
                    else {
                        resultString += "0";
                    }
                }

                setRegister(destination, Utils.binaryToHex(resultString));

                break;
            }
            case "slti": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long value = Utils.hexToLong(s1);

                if (value < constant) {
                    setRegister(destination, Utils.longToHex(value));
                }

                break;
            }
            case "lw": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[3]);
                Long s1Value = Long.parseLong(s1);
                Long constant = Long.parseLong(lineItems[2]);
                String address = Utils.longToHex(s1Value + constant);
                String memoryValue = getValueMemory(address);

                setRegister(destination, memoryValue);

                break;
            }
            case "sw": {
				String s0 = getValueRegister(lineItems[1]);
                String s1 = getValueRegister(lineItems[3]);
                Long s1Value = Long.parseLong(s1);
                Long constant = Long.parseLong(lineItems[2]);
                String address = Utils.longToHex(s1Value + constant);

                setMemory(address, s0);

                break;
            }
            case "beq": {
                String s0 = getValueRegister(lineItems[1]);
                String s1 = getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long s0Value = Utils.hexToLong(s0);
                Long s1Value = Utils.hexToLong(s1);

                if (s0Value == s1Value) {
                    return constant;
                }

                break;
            }
            case "bne": {
                String s0 = getValueRegister(lineItems[1]);
                String s1 = getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long s0Value = Utils.hexToLong(s0);
                Long s1Value = Utils.hexToLong(s1);

                if (s0Value != s1Value) {
                    return constant;
                }

                break;
            }
            case "nop": {
                break;
            }
            case "add": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long s1value = Utils.hexToLong(s1);
                Long s2value = Utils.hexToLong(s2);
                String result = Utils.longToHex(s1value + s2value);

                setRegister(destination, result);

                break;
            }
            case "sub": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long s1value = Utils.hexToLong(s1);
                Long s2value = Utils.hexToLong(s2);
                String result = Utils.longToHex(s1value - s2value);

                setRegister(destination, result);

                break;
            }
            case "mult": {
                String destination = lineItems[1];
                Long destinationValue = Utils.hexToLong(getValueRegister(destination));
                String s1 = getValueRegister(lineItems[2]);
                Long s1value = Utils.hexToLong(s1);
                String result = Utils.longToHex(destinationValue * s1value);

                setRegister(destination, result);

                break;
            }
            case "div": {
                String destination = lineItems[1];
                Long destinationValue = Utils.hexToLong(getValueRegister(destination));
                String s1 = getValueRegister(lineItems[2]);
                Long s1Value = Utils.hexToLong(s1);
                String result = (s1Value != 0) ? Utils.longToHex(destinationValue / s1Value) : "0"; // Division by zero

                setRegister(destination, result);
                
                break;
            }
            case "and": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long s1Value = Utils.hexToLong(s1);
                Long s2Value = Utils.hexToLong(s2);
                String s1ValueBinary = Long.toBinaryString(s1Value);
                String s2ValueBinary = Long.toBinaryString(s2Value);
                String result = "";

                for (int i = 0; i < s1ValueBinary.length(); i += 1) {
                    if (s1ValueBinary.charAt(i) == '1' && s2ValueBinary.charAt(i) == '1') {
                        result += "1";
                    }
                    else {
                        result += "0";
                    }
                }

                setRegister(destination, Utils.binaryToHex(result));

                break;
            }
            case "or": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long s1Value = Utils.hexToLong(s1);
                Long s2Value = Utils.hexToLong(s2);
                String s1ValueBinary = Long.toBinaryString(s1Value);
                String s2ValueBinary = Long.toBinaryString(s2Value);
                String result = "";

                for (int i = 0; i < s1ValueBinary.length(); i += 1) {
                    if (s1ValueBinary.charAt(i) == '1' || s2ValueBinary.charAt(i) == '1') {
                        result += "1";
                    }
                    else {
                        result += "0";
                    }
                }

                setRegister(destination, Utils.binaryToHex(result));

                break;
            }
            case "xor": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long value1 = Utils.hexToLong(s1);
                Long value2 = Utils.hexToLong(s2);
                String binaryValue = Long.toBinaryString(value1);
                String binaryValue2 = Long.toBinaryString(value2);
                String result = "";

                for (int i = 0; i < binaryValue.length(); i += 1) {
                    if ((binaryValue.charAt(i) == '1' || binaryValue2.charAt(i) == '1') && binaryValue.charAt(i) != binaryValue2.charAt(i)) {
                        result += "1";
                    } 
                    else {
                        result += "0";
                    }
                }

                setRegister(destination, Utils.binaryToHex(result));

                break;
            }
            case "nor": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long value1 = Utils.hexToLong(s1);
                Long value2 = Utils.hexToLong(s2);
                String binaryValue = Utils.completeWithZeros(Long.toBinaryString(value1), Constants.BINARY_LENGTH);
                String binaryValue2 = Utils.completeWithZeros(Long.toBinaryString(value2), Constants.BINARY_LENGTH);
                String result = "";

                for (int i = 0; i < binaryValue.length(); i += 1) {
                    if (binaryValue.charAt(i) == '0' && binaryValue2.charAt(i) == '0') {
                        result += "1";
                    }
                    else {
                        result += "0";
                    }
                }

                setRegister(destination, Utils.binaryToHex(result));

                break;
            }
            case "slt": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                String s2 = getValueRegister(lineItems[3]);
                Long value1 = Utils.hexToLong(s1);
                Long value2 = Utils.hexToLong(s2);

                if (value1 < value2) {
                    setRegister(destination, Utils.longToHex(value1));
                }

                break;
            }
            case "sll": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long s1Value = Utils.hexToLong(s1);
                Long result = s1Value<<constant;

                setRegister(destination, Utils.longToHex(result));

                break;
            }
            case "srl": {
                String destination = lineItems[1];
                String s1 = getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long s1Value = Utils.hexToLong(s1);
                Long result = s1Value>>constant;

                setRegister(destination, Utils.longToHex(result));

                break;
            }
            case "jr": {
                String s1 = getValueRegister(lineItems[1]);
                Long s1Value = Utils.hexToLong(s1);

                return Integer.parseInt(Long.toString(s1Value));
            }
            case "j": {
                return Integer.parseInt(lineItems[1]);
            }
            case "jal": {
                return Integer.parseInt(lineItems[1]);
            }
            default: {
                throw new CustomException(String.format("\"%s\" funct is not valid.", funct));
            }
        }

        return programCounter + 4;
    }
}
