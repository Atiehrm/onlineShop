import exception.CustomException;

public class Utilities {
    protected static boolean isValidChoice(String choice, int maxNumber) throws CustomException {
        if (isNumeric(choice, "choice") && Integer.parseInt(choice) < maxNumber) {
            return true;
        }
        throw new CustomException("choice should be less than " + maxNumber);
    }

    protected static boolean isNumeric(String input, String name) throws CustomException {
        if (input.matches("[0-9]+")) {
            return true;
        }
        throw new CustomException(name + " is not integer number");
    }

    protected static boolean isAlphabetic(String input, String name) throws CustomException {
        if (input.matches("[a-zA-Z]+")) {
            return true;
        }
        throw new CustomException(name + " is not alphabetic");
    }

    protected static boolean isValidEmail(String input, String name) throws CustomException {
        if (input.matches("^(.+)@(.+)$")) {
            return true;
        }
        throw new CustomException(name + " is not valid ");
    }

    protected static boolean isValidNationalCode(String input, String name) throws CustomException {
        if (input.matches("[0-9]+") && input.length() < 11) {
            return true;
        }
        throw new CustomException(name + " is not valid ");
    }
}
