package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import exceptions.CustomException;

public class Executer {
    private HashMap<Integer, String> _assemblyCode;

    public Executer(File inputFile) throws FileNotFoundException {
        this._assemblyCode = new HashMap<Integer, String>();
        this.fillAssemblyCode(inputFile);
    }

    private void fillAssemblyCode(File inputFile) {
        try {
            int programCounter = 0;
            Scanner myReader = new Scanner(inputFile);

            while (myReader.hasNextLine()) {
                String assemblyCode = myReader.nextLine();

                if (Utils.isEmptyOrSpaces(assemblyCode)/* || Utils.isCommentLine(assemblyCode)*/) {
                    continue;
                }

                this._assemblyCode.put(programCounter, assemblyCode);

                programCounter += 4;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void execute() throws CustomException {
        boolean ended = false;
        int programCounter = 0;

        do {
            String codeLine = this._assemblyCode.get(programCounter);

            programCounter = Mips.getInstance().execute(programCounter, codeLine);
    
            if (!this._assemblyCode.containsKey(programCounter)) {
                ended = true;
            }            
        } while (!ended);
    }
}
