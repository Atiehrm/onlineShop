package service;

import DataAccess.CustomerDao;
import exception.CustomException;
import models.Customer;

import java.sql.SQLException;

public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService() throws SQLException, ClassNotFoundException {
        this.customerDao = new CustomerDao();
    }


    public boolean isAuthenticated(String email, String nationalCode) throws SQLException, NullPointerException, ClassNotFoundException {
        if (customerDao.findEmail(email) != -1
                && customerDao.findNationalCode(nationalCode) != -1) {
            return true;
        }
        return false;
    }

    public void signUp(String[] customerInfo) throws SQLException, NullPointerException, ClassNotFoundException, CustomException {
        if (!isExistCustomer(customerInfo[1], customerInfo[2])) {
            Customer customer = new Customer(customerInfo[0], customerInfo[1], customerInfo[2]);
            customerDao.save(customer);
        } else throw new CustomException(customerInfo[2] + " is registered before");
    }

    public boolean isExistCustomer(String nationalCode, String email) throws SQLException, ClassNotFoundException {
        if (customerDao.findNationalCode(nationalCode) != -1 || customerDao.findEmail(email) != -1) {
            return true;
        } else {
            return false;
        }
    }
}
