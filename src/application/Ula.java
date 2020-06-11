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
                int value = Utils.hexToInt(s1);
                String result = Utils.intToHex(value + constant);

                this._mips.setRegister(destination, result);
                break;
            }

            case "neg": {

                break;
            }

            case "andi": {

                break;
            }

            case "ori": {

                break;
            }

            case "slti": {

                break;
            }

            case "lw": {

                break;
            }

            case "sw": {

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