package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import exceptions.CustomException;

public class Executer {
    private HashMap<Integer, String> _assemblyCode;
    private Ula _ula;

    public Executer(File inputFile) throws FileNotFoundException {
        this._assemblyCode = new HashMap<Integer, String>();
        this._ula = new Ula();
        this.fillAssemblyCode(inputFile);
    }

    private void fillAssemblyCode(File inputFile) {
        try {
            int programCounter = 0;
            Scanner myReader = new Scanner(inputFile);

            while (myReader.hasNextLine()) {
                String assemblyCode = myReader.nextLine();
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

        while(!ended) {
            String currentCode = this._assemblyCode.get(programCounter);
            programCounter = this._ula.execute(programCounter, currentCode);
            
            if(!checkCommand(programCounter)) {
                ended = true;
            }
        }
    }

    private boolean checkCommand(int key) {
        return this._assemblyCode.containsKey(key);
    }
}