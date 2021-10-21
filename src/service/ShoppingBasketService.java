package service;

import DataAccess.CustomerDao;
import DataAccess.ProductDao;
import DataAccess.ShoppingBasketDao;
import models.Customer;
import models.Product;
import models.ShoppingBasket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingBasketService {
    private final ShoppingBasketDao shoppingBasketDao;
    private final ProductDao productDao;
    private final CustomerDao customerDao;

    public ShoppingBasketService() throws SQLException, ClassNotFoundException {
        this.shoppingBasketDao = new ShoppingBasketDao();
        this.productDao = new ProductDao();
        this.customerDao = new CustomerDao();
    }

    public void addToBasket(Product product, Customer customer, int numberOfOrderPerProduct) throws SQLException, ClassNotFoundException {
        ShoppingBasket shoppingBasket = new ShoppingBasket(product, customer, numberOfOrderPerProduct);
        shoppingBasketDao.save(shoppingBasket);
    }

    public void removeFromBasket(int id) throws SQLException, ClassNotFoundException {
        shoppingBasketDao.delete(id);
    }

    public List<ShoppingBasket> seeBasket(String nationalCode) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.findByNationalCode(nationalCode);
        List<ShoppingBasket> shoppingBaskets = shoppingBasketDao.getBasketList(nationalCode, customer);
        return shoppingBaskets;
    }

    public double getTotalPrice(String nationalCode) throws SQLException, ClassNotFoundException {
        Map<Integer, Integer> allOrders = new HashMap<>();
        double sum = 0D;
        allOrders = shoppingBasketDao.findProductAndOrder(nationalCode);
        for (Map.Entry<Integer, Integer> entry : allOrders.entrySet()) {
            Product product = productDao.findProduct(entry.getKey());
            double priceOfEachProduct = product.getPrice() * entry.getValue();
            sum = sum + priceOfEachProduct;
        }
        return sum;
    }

    public int getRows(String nationalCode) throws SQLException, ClassNotFoundException {
        int count = shoppingBasketDao.getRows(nationalCode);
        return count;
    }

    public Map<Integer, Integer> findProductAndOrder(String nationalCode) throws SQLException, ClassNotFoundException {
        Map<Integer, Integer> productAndOrder = shoppingBasketDao.findProductAndOrder(nationalCode);
        return productAndOrder;
    }

    public Product findProduct(Product product) throws SQLException, ClassNotFoundException {
        Product newProduct = productDao.findProduct(product.getId());
        return newProduct;
    }

    public void updateOrder(Customer customer,Product product, int productCountOrder) throws SQLException, ClassNotFoundException {
        int orderNumber = shoppingBasketDao.getOrderNumber(customer,product);
        shoppingBasketDao.updateOrder(customer,product,productCountOrder+orderNumber);

    }

}
