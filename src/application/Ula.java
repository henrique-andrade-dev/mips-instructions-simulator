package application;

import java.util.Arrays;

import exceptions.CustomException;

public class Ula {
    private Mips _mips;

    public Ula() {
        this._mips = new Mips();
    }

    public int execute(int programCounter, String instruction) throws CustomException {
        String[] lineItems = instruction.split("\\s+|,|\\(|\\)");
        lineItems = Arrays.stream(lineItems).filter(s -> !s.isEmpty()).toArray(String[]::new);
        String funct = lineItems[0];

        switch (funct) {
            case "addi": {
                String destination = lineItems[1];
                String s1 = _mips.getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long value = Utils.hexToLong(s1);
                String result = Utils.longToHex(value + constant);
                this._mips.setRegister(destination, result);
                break;
            }

            case "neg": {
                String destination = lineItems[1];
                String s1 = _mips.getValueRegister(lineItems[2]);
                Long value = Utils.hexToLong(s1);
                String result = Utils.longToHex(value * -1);

                this._mips.setRegister(destination, result);
                break;
            }

            case "andi": {
                String destination = lineItems[1];
                String s1 = _mips.getValueRegister(lineItems[2]);
                Long value = Utils.hexToLong(s1);
                int constant = Integer.parseInt(lineItems[3]);
                
                String valueString = Long.toBinaryString(value);
                String constantBinary = Integer.toBinaryString(constant);

                String resultString = "";
                for (int i = 0; i < valueString.length(); i += 1) {
                    if (valueString.charAt(i) == '1' && constantBinary.charAt(i) == '1') {
                        resultString += "1";
                    } else {
                        resultString += "0";
                    }
                }

                this._mips.setRegister(destination, Utils.binaryToHex(resultString));
                break;
            }

            case "ori": {
                String destination = lineItems[1];
                String s1 = _mips.getValueRegister(lineItems[2]);
                Long value = Utils.hexToLong(s1);
                int constant = Integer.parseInt(lineItems[3]);
                
                String valueString = Long.toBinaryString(value);
                String constantBinary = Integer.toBinaryString(constant);

                String resultString = "";
                for (int i = 0; i < valueString.length(); i += 1) {
                    if (valueString.charAt(i) == '1' || constantBinary.charAt(i) == '1') {
                        resultString += "1";
                    } else {
                        resultString += "0";
                    }
                }

                this._mips.setRegister(destination, Utils.binaryToHex(resultString));
                break;
            }

            case "slti": {
                String destination = lineItems[1];
                String s1 = _mips.getValueRegister(lineItems[2]);
                int constant = Integer.parseInt(lineItems[3]);
                Long value = Utils.hexToLong(s1);

                if (value < constant) {
                    this._mips.setRegister(destination, Utils.longToHex(value));
                }
                break;
            }

            case "lw": {
                String destination = lineItems[1];
                String s1 = this._mips.getValueRegister(lineItems[3]);
                Long s1Value = Long.parseLong(s1);
                Long constant = Long.parseLong(lineItems[2]);
                
                String address = Utils.formatString(Utils.longToHex(s1Value + constant), Constants.MAXIMUM_HEX_ADDRESS_LENGTH);
                String memoryValue = this._mips.getValueMemory(address);
                this._mips.setRegister(destination, memoryValue);
                break;
            }

            case "sw": {
                String s0 = this._mips.getValueRegister(lineItems[1]);
                String s1 = this._mips.getValueRegister(lineItems[3]);
                Long s1Value = Long.parseLong(s1);
                Long constant = Long.parseLong(lineItems[2]);
                
                String address = Utils.formatString(Utils.longToHex(s1Value + constant), Constants.MAXIMUM_HEX_ADDRESS_LENGTH);
                this._mips.setMemory(address, s0);
                break;
            }

            case "beq": {
                
                break;
            }

            case "bne": {

                break;
            }

            case "nop": {

                break;
            }

            case "add": {

                break;
            }

            case "sub": {

                break;
            }

            case "mult": {

                break;
            }

            case "div": {

                break;
            }

            case "and": {

                break;
            }

            case "or": {

                break;
            }

            case "xor": {
                String destination = lineItems[1];
                String s1 = _mips.getValueRegister(lineItems[2]);
                String s2 = _mips.getValueRegister(lineItems[3]);
                Long value = Utils.hexToLong(s1);
                Long value2 = Utils.hexToLong(s2);
                
                String binaryValue = Long.toBinaryString(value);
                String binaryValue2 = Long.toBinaryString(value2);

                String resultString = "";
                for (int i = 0; i < binaryValue.length(); i += 1) {
                    if ((binaryValue.charAt(i) == '1' || binaryValue2.charAt(i) == '1') &&
                        binaryValue.charAt(i) != binaryValue2.charAt(i)
                    ) {
                        resultString += "1";
                    } else {
                        resultString += "0";
                    }
                }

                this._mips.setRegister(destination, Utils.binaryToHex(resultString));
                break;
            }

            case "nor": {

                break;
            }

            case "slt": {

                break;
            }

            case "sll": {

                break;
            }

            case "srl": {

                break;
            }

            case "jr": {

                break;
            }

            case "j": {

                break;
            }

            case "jal": {

                break;
            }
            default:
                break;
        }
        return programCounter + 4;
    }
}