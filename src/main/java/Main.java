import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, CustomException {
        File inputFile = new File(args[0]);
        
        Decoder decoder = new Decoder(inputFile);
        Executer executer = new Executer(inputFile);
        executer.execute();
        
        execute(decoder);
    }

    private static void execute(Decoder decoder) throws CustomException {
        Utils.writeFile(Constants.PATH_INSTRUCTIONS_FILE, decoder.getBinaryInstructions());
        Utils.writeFile(Constants.PATH_REGISTER_FILE, Mips.getInstance().getRegistersFormatted());
        Utils.writeFile(Constants.PATH_MEMORY_FILE, Mips.getInstance().getMemoryFormatted());
    }
}
