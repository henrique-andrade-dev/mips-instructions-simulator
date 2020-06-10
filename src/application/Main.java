package application;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Decoder decoder = new Decoder(new File(Constants.PATH_INPUT_FILE));
        Mips mips = new Mips();

        execute(decoder, mips);
    }

    private static void execute(Decoder decoder, Mips mips) {
        System.out.println(decoder.getBinaryInstructions());

        Utils.writeFile(Decoder.PATH_INSTRUCTIONS_FILE, decoder.getBinaryInstructions());
        Utils.writeFile(Mips.PATH_REGISTER_FILE, mips.getRegistersFormatted());
        Utils.writeFile(Mips.PATH_MEMORY_FILE, mips.getMemoryFormatted());
    }
}
