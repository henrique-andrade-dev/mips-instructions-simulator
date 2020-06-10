package application;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Decoder decoder = new Decoder(new File("./assets/input.txt"));

        execute(decoder);
    }

    private static void execute(Decoder decoder) {
        System.out.println(decoder.getBinaryInstructions());

        decoder.writeFile();
    }
}
