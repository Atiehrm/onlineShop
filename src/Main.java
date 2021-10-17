import exception.CustomException;
import servise.CustomerService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService;

    static {
        try {
            customerService = new CustomerService();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        outer:
        while (true) {
            try {
                System.out.println("1) login\n" +
                        "2) signup\n" +
                        "3) exit");
                String choice = scanner.nextLine().trim();
                if (Utilities.isValidChoice(choice, 4)) {
                    switch (choice) {
                        case "1":
                            login();
                            break;
                        case "2":
                            signUp();
                            break;
                        case "3":
                            break outer;
                    }
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage() + "\n try again ");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void login() throws CustomException, SQLException
            , NullPointerException, ClassNotFoundException {
        System.out.println("enter your email: ");
        String email = scanner.nextLine().trim().toLowerCase();
        scanner.nextLine();
        System.out.println("enter your nationalCode: ");
        String nationalCode = scanner.nextLine().trim();
        if (Utilities.isValidEmail(email, "email")
                && Utilities.isValidNationalCode(nationalCode, "national code")) {
            if (customerService.isAuthenticated(email, nationalCode)) {
                System.out.println("authentication done! ");
                onlineShop();
            } else {
                throw new CustomException("sign up first ");
            }
        }
    }

    private static void signUp() throws CustomException, SQLException, NullPointerException, ClassNotFoundException {
        String[] customerInfo = customerInfo();
        if (Utilities.isAlphabetic(customerInfo[0], "full name")
                && Utilities.isValidNationalCode(customerInfo[1], "national code sign up")
                && Utilities.isValidEmail(customerInfo[2], "email")) {
            customerService.signUp(customerInfo);
            System.out.println("u successfully signed up \n" +
                    "your username is your email & your password is your national code\n" +
                    "** now login first **");
        }
    }

    private static String[] customerInfo() {
        String[] customerInfo = new String[3];
        System.out.println("enter your name: ");
        customerInfo[0] = scanner.nextLine().trim().toLowerCase();
        System.out.println("enter national code: ");
        customerInfo[1] = scanner.nextLine().trim();
        System.out.println("enter your email address: ");
        customerInfo[2] = scanner.nextLine().trim().toLowerCase();
        return customerInfo;
    }

    private static void onlineShop() {

    }

}