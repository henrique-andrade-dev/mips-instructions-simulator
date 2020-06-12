package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Decoder {
    private String _binaryInstructions;

    public Decoder(File inputFile) throws FileNotFoundException {
        this._binaryInstructions = setBinaryInstructions(inputFile);
    }

    private String setBinaryInstructions(File inputFile) throws FileNotFoundException {
        String binary = "";

        try {
            Scanner myReader = new Scanner(inputFile);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] lineItems = line.split("\\s+|,|\\(|\\)");
                lineItems = Arrays.stream(lineItems).filter(s -> !s.isEmpty()).toArray(String[]::new);

                if (lineItems.length > 0) {
                    switch (FormatType.getInstance().get(lineItems[0])) {
                        case "r": {
                            binary += "000000"; // Opcode
                            // Opcode, rs, rt, rd, sa, funct
                            // add s0, s1, s2
                            // add = lineitems 0
                            // s0 = lineitems 1 = rd
                            // s1 = lineitems 2 = rs
                            // s2 = linteitems 3 = rt

                            String rd = Utils.returnRegisterNumber(lineItems[1]);
                            String rs = lineItems.length > 2 ? Utils.returnRegisterNumber(lineItems[2]) : "00000";
                            String rt = lineItems.length > 3 ? Utils.returnRegisterNumber(lineItems[3]) : "00000";
                            String funct = Opcode.getInstance().get(lineItems[0]);
                            String shamt = "00000";

                            if (lineItems.length == 3) {
                                String temp = rs;

                                rs = rd;
                                rt = temp;
                                rd = "00000";
                            }

                            switch (lineItems[0]) {
                                case "sll": {
                                    rt = Utils.returnBinaryString(lineItems[3], 5);
                                    String temp = shamt;
                                    shamt = rt;
                                    rt = rs;
                                    rs = temp;

                                    break;
                                }
                                case "srl": {
                                    rt = Utils.returnBinaryString(lineItems[3], 5);
                                    String temp = shamt;
                                    shamt = rt;
                                    rt = rs;
                                    rs = temp;

                                    break;
                                }
                                case "jr": {
                                    String temp = shamt;
                                    rs = rd;
                                    rd = temp;
                                    break;
                                }
                                default: {
                                    break;
                                }
                            }

                            binary += rs + rt + rd + shamt + funct + "\n";

                            break;
                        }
                        case "j": {
                            binary += Opcode.getInstance().get(lineItems[0]); // Opcode
                            binary += Utils.returnBinaryString(lineItems[1], 26) + "\n"; // Address

                            break;
                        }
                        case "i": {
                            binary += Opcode.getInstance().get(lineItems[0]); // Opcode

                            String operatorsBinary = ""; // $r, $t, etc
                            String constantBinary = ""; // valor constante

                            for (int i = lineItems.length - 1; i > 0; i -= 1) {
                                if (Utils.isRegister(lineItems[i])) {
                                    operatorsBinary += Utils.returnRegisterNumber(lineItems[i]);
                                } else {
                                    constantBinary += Utils.returnBinaryString(lineItems[i], 16);
                                }
                            }

                            switch (lineItems[0]) {
                                case "neg": {
                                    constantBinary += "1000000000000001";

                                    break;
                                }
                                default: {
                                    break;
                                }
                            }

                            binary += operatorsBinary + constantBinary + "\n";

                            break;
                        }
                        default: {
                            System.out.println("Format type not found."); // Create a custom Exception

                            break;
                        }
                    }
                }
            }

            myReader.close();

            return binary;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getBinaryInstructions() {
        return this._binaryInstructions;
    }
}
