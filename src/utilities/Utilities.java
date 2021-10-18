package utilities;

import exception.CustomException;

public class Utilities {
    public static boolean isValidChoice(String choice, int maxNumber) throws CustomException {
        if (isNumeric(choice, "input") && Integer.parseInt(choice) < maxNumber) {
            return true;
        }
        throw new CustomException(" your choice should be less than " + maxNumber);
    }

    public static boolean isNumeric(String input, String name) throws CustomException {
        if (input.matches("[0-9]+")) {
            return true;
        }
        throw new CustomException(name + " is not integer number");
    }

    public static boolean isAlphabetic(String input, String name) throws CustomException {
        if (input.matches("[a-zA-Z]+")) {
            return true;
        }
        throw new CustomException(name + " is not alphabetic");
    }

    public static boolean isValidEmail(String input, String name) throws CustomException {
        if (input.matches("^(.+)@(.+)$")) {
            return true;
        }
        throw new CustomException(name + " is not valid ");
    }

    public static boolean isValidNationalCode(String input, String name) throws CustomException {
        if (input.matches("[0-9]+") && input.length() < 11) {
            return true;
        }
        throw new CustomException(name + " is not valid ");
    }
}
