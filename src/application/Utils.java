package application;

import java.io.FileWriter;
import java.io.IOException;

public class Utils {
	public static int tryParseInt(String lineItem) {
		return lineItem.matches("\\d+") ? Integer.parseInt(lineItem) : -1;
	}

	// Merge with "isRegisterValid" from Mips's class
	public static boolean isRegister(String lineItem) {
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
			default: {
				return returnBinaryString(Character.toString(lineItem.charAt(0)), 5);
			}
		}
	}

	public static String returnBinaryString(String input, int max) {
		String binaryInput = Integer.toBinaryString(Integer.parseInt(input));

		return completeWithZeros(binaryInput, max);
	}

	public static String completeWithZeros(String value, int max) {
		String returnString = "";

		for (int i = 0; i < max - value.length(); i += 1) {
			returnString += "0";
		}

		return returnString + value;
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

	public static String intToHex(int value) {
		return Integer.toHexString(value);
	}

	public static Long hexToLong(String hex) {
		return Long.parseLong(hex, 16);
	}

	public static String longToHex(long value) {
		return Long.toHexString(value);
	}

	public static String binaryToHex(String value) {
		Long valueDecimalBase = Long.parseLong(value, 2);

		return Long.toString(valueDecimalBase, 16);
	}
}
