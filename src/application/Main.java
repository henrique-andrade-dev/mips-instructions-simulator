package application;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Decoder decoder = new Decoder(new File(Constants.PATH_INPUT_FILE));

        execute(decoder);
    }

    private static void execute(Decoder decoder) {
        String binaryInstructions = decoder.getBinaryInstructions();

        System.out.println(binaryInstructions);

        Utils.writeFile(Decoder.PATH_INSTRUCTIONS_FILE, binaryInstructions);
    }
}
