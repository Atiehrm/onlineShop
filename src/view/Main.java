package view;

import exception.CustomException;
import models.Customer;
import models.Product;
import models.ShoppingBasket;
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
        outer:
        while (true) {
            showCustomerMenu();
            String choice = scanner.nextLine().trim();
            if (Utilities.isValidChoice(choice, 7)) {
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
                        confirmToPay(nationalCode);
                        break;
                    case "6":
                        break outer;
                }
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
                "6) exit \n" +
                "select your choice: ");
    }

    private static void showProductsMenu() throws SQLException, ClassNotFoundException {
        List<Product> productList = productService.getAllProducts();
        for (Product product : productList) {
            System.out.println("*" + " " + product);
        }
    }

    private static void addToBasket(String nationalCode) throws SQLException, ClassNotFoundException, CustomException {
        String[] newCustomerInput = getCustomerProductChoice();
        String[] countOfEachProduct = getCountOfEachProduct();
        Customer customer = customerService.findByNationalCode(nationalCode);
        Map<Integer, Integer> customerInputProductIdAndCount = new HashMap<>();
        if (!((shoppingBasketService.getRows(nationalCode) + newCustomerInput.length) > 5)) {
            if (newCustomerInput.length == countOfEachProduct.length) {
                customerInputValidationAndAddToMap(newCustomerInput, countOfEachProduct, customerInputProductIdAndCount);
                checkStockAndAddBasket(customer, customerInputProductIdAndCount);
                System.out.println("your basket is uptodated, see your basket from menu 3\n" +
                        "**************************\n");
            } else {
                throw new CustomException("enter count of all products");
            }

        } else {
            throw new CustomException("only 5 items valid to add");
        }
    }

    private static void customerInputValidationAndAddToMap(String[] newCustomerInput
            , String[] countOfEachProduct, Map<Integer, Integer> customerInput) throws CustomException {
        for (int i = 0; i < newCustomerInput.length; i++) {
            if (Utilities.isNumeric(newCustomerInput[i], " item id of customer input ")) {
                customerInput.put(Integer.parseInt(newCustomerInput[i])
                        , Integer.parseInt(countOfEachProduct[i]));
            }
        }
    }

    private static void checkStockAndAddBasket(Customer customer, Map<Integer
            , Integer> customerInputProductIdAndCount) throws SQLException, ClassNotFoundException, CustomException {

        for (Map.Entry<Integer, Integer> entry : customerInputProductIdAndCount.entrySet()) {
            Product product = productService.findById(entry.getKey());
            if (product.getStock() >= entry.getValue()) {
                if (shoppingBasketService.findProduct(product) == null) {
                    shoppingBasketService.addToBasket(product, customer, entry.getValue());
                }else{
                    shoppingBasketService.updateOrder(customer,product,entry.getValue());
                    }
                } else{
                    throw new CustomException("out of stock");
                }

            }
        }

        private static String[] getCustomerProductChoice () {
            System.out.println("enter id of items: ");
            String[] newCustomerInput = scanner.nextLine().trim().split(",");
            return newCustomerInput;
        }

        private static String[] getCountOfEachProduct () {
            System.out.println("enter number of each product u wanna add: ");
            String[] countOfProducts = scanner.nextLine().trim().split(",");
            return countOfProducts;
        }


        public static void removeFromBasket (String nationalCode) throws
        CustomException, SQLException, ClassNotFoundException {
            System.out.println("enter id you wanna remove: ");
            String idOfRemoving = scanner.nextLine().trim();
            if (Utilities.isValidChoice(idOfRemoving, shoppingBasketService.getRows(nationalCode) + 1)) {
                shoppingBasketService.removeFromBasket(Integer.parseInt(idOfRemoving));
                System.out.println(idOfRemoving + " is removed from your basket! \n" +
                        "**************** \n");
            }
        }

        public static void seeBasket (String nationalCode) throws
        SQLException, ClassNotFoundException, NullPointerException {
            List<ShoppingBasket> seeBasket = shoppingBasketService.seeBasket(nationalCode);
            System.out.println("your basket: ");
            for (ShoppingBasket shoppingBasket : seeBasket) {
                System.out.println(shoppingBasket);
            }
        }

        public static void getTotalPrice (String nationalCode) throws SQLException, ClassNotFoundException {

            System.out.println("Total price is: " + shoppingBasketService.getTotalPrice(nationalCode));
        }

        public static void confirmToPay (String nationalCode) throws SQLException, ClassNotFoundException {
            System.out.println("this is your final basket list: ");
            seeBasket(nationalCode);
            Map<Integer, Integer> productOrder = shoppingBasketService.findProductAndOrder(nationalCode);
            for (Map.Entry<Integer, Integer> entry : productOrder.entrySet()) {
                productService.updateStock(entry.getKey(), entry.getValue());
            }
        }
    }