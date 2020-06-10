package application;

import java.io.FileWriter;
import java.io.IOException;

public class Utils {
	// private static final Utils INSTANCE = new Utils();

	// public Utils() { // If using Singleton make it private
	// Initialize something
	// }

	// public static Utils getInstance() {
	// return INSTANCE;
	// }

	public static int tryParseInt(String lineItem) {
		return lineItem.matches("\\d+") ? Integer.parseInt(lineItem) : -1;
	}

	public static boolean checkIfRegister(String lineItem) {
		return !lineItem.matches("^[0-9]*$");
	}

	public static String returnRegisterNumber(String lineItem) {
		switch (lineItem.charAt(0)) {
			case 'r': {
				int[] sValues = { 16, 17, 18, 19, 20, 21, 22, 23 };
				int indexValue = Integer.parseInt(lineItem.replaceAll("\\D+", ""));

				return Integer.toBinaryString(sValues[indexValue]);
			}
			case 's': {
				int[] sValues = { 16, 17, 18, 19, 20, 21, 22, 23 };
				int indexValue = Integer.parseInt(lineItem.replaceAll("\\D+", ""));

				return Integer.toBinaryString(sValues[indexValue]);
			}
			case 't': {
				int[] tValues = { 8, 9, 10, 11, 12, 13, 14, 15, 24, 25 };

				return Integer.toBinaryString(tValues[lineItem.charAt(1)]);
			}
			default:
				return returnBinaryString(Character.toString(lineItem.charAt(0)), 5);
		}
	}

	public static String returnBinaryString(String input, int max) {
		String binaryString = Integer.toBinaryString(Integer.parseInt(input));
		String prefix = "";

		for (int i = 0; i < max - binaryString.length(); i += 1) {
			prefix += "0";
		}

		return prefix + binaryString;
	}

	public static void writeFile(String pathFile, String content) {
		try {
			FileWriter outputFile = new FileWriter(pathFile);

			outputFile.write(content);
			outputFile.close();

			System.out.println(String.format("\"%s\" file created.", pathFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
