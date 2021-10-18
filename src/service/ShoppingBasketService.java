package service;

import DataAccess.ShoppingBasketDao;
import models.Customer;
import models.Product;
import models.ShoppingBasket;

import java.sql.SQLException;
import java.util.Set;

public class ShoppingBasketService {
    private final ShoppingBasketDao shoppingBasketDao;

    public ShoppingBasketService() throws SQLException, ClassNotFoundException {
        this.shoppingBasketDao = new ShoppingBasketDao();
    }

    public void addToBasket(Product product,Customer customer) throws SQLException, ClassNotFoundException {
            ShoppingBasket shoppingBasket = new ShoppingBasket(product,customer);
            shoppingBasketDao.save(shoppingBasket);
    }

    public void removeFromBasket(String nationalCode) {

    }

    public void seeBasket(String nationalCode) {

    }

    public void getTotalPrice(String nationalCode) {

    }
}
