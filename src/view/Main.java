package view;

import exception.CustomException;
import models.Customer;
import models.Product;
import service.CustomerService;
import service.ProductService;
import service.ShoppingBasketService;
import utilities.Utilities;

import java.sql.SQLException;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService;
    private static ProductService productService;
    private static ShoppingBasketService shoppingBasketService;

    static {
        try {
            customerService = new CustomerService();
            productService = new ProductService();
            shoppingBasketService = new ShoppingBasketService();
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
                onlineShop(nationalCode);
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

    private static void onlineShop(String nationalCode) throws SQLException, ClassNotFoundException, CustomException {
        showCustomerMenu();
        String choice = scanner.nextLine().trim();
        if (Utilities.isValidChoice(choice, 6)) {
            switch (choice) {
                case "1":
                    addToBasket(nationalCode);
                    break;
                case "2":
                    removeFromBasket(nationalCode);
                    break;
                case "3":
                    seeBasket(nationalCode);
                    break;
                case "4":
                    getTotalPrice(nationalCode);
                    break;
                case "5":
                    //todo
                    break;
            }
        }
    }

    private static void showCustomerMenu() throws SQLException, ClassNotFoundException {
        System.out.println("**wlc to online shop**");
        showProductsMenu();
        System.out.println("1) add items to card\n" +
                "2) remove items from card \n" + "" +
                "3) see your basket \n" +
                "4) total price of your shopping list \n" +
                "5) confirm your shopping\n" +
                "select your choice: ");
    }

    private static void showProductsMenu() throws SQLException, ClassNotFoundException {
        List<Product> productList = productService.getAllProducts();
        for (Product product : productList) {
            System.out.println("*" + " " + product);
        }
    }

    private static void addToBasket(String nationalCode) throws CustomException, SQLException, ClassNotFoundException {
        try {
            Set<Integer> customerInput = new HashSet<>();
            String[] newCustomerInput = getCustomerProductChoice();
            if (newCustomerInput.length < 6) {
                addToShoppingBasket(nationalCode, customerInput, newCustomerInput);
            } else {
                System.out.println("only 5 items valid to add");
            }
        }catch (CustomException e){
            System.out.println(" duplicate id is not valid choose unique item ");
        }

    }

    private static String[] getCustomerProductChoice() {
        System.out.println("enter id of items: ");
        String[] newCustomerInput = scanner.nextLine().trim().split(",");
        return newCustomerInput;
    }

    private static void addToShoppingBasket(String nationalCode, Set<Integer> customerInput, String[] newCustomerInput) throws CustomException, SQLException, ClassNotFoundException {
        for (int i = 0; i < 6; i++) {
            if (Utilities.isValidChoice(newCustomerInput[i],productService.getAllProducts().size())) {
                customerInput.add(Integer.parseInt(newCustomerInput[i]));
            }
        }
        Customer customer = customerService.findByNationalCode(nationalCode);
        for (Integer i : customerInput){
            Product product = productService.findById(Integer.parseInt(newCustomerInput[i]));
            shoppingBasketService.addToBasket(product,customer);
        }
    }

    public static void removeFromBasket(String nationalCode) {
        shoppingBasketService.removeFromBasket(nationalCode);
    }

    public static void seeBasket(String nationalCode) {
        shoppingBasketService.seeBasket(nationalCode);
    }

    public static void getTotalPrice(String nationalCode) {
        shoppingBasketService.getTotalPrice(nationalCode);
    }
}