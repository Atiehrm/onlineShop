package models;


import java.util.Objects;

public class ShoppingBasket {
    private int id;
    private Product product;
    private Customer customer;
    private int numberOfOrder;

    public ShoppingBasket(Product product, Customer customer, int numberOfOrder) {
        this.product = product;
        this.customer = customer;
        this.numberOfOrder = numberOfOrder;
    }

    public ShoppingBasket() {

    }


    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "id=" + id +
                ", product_id=" + product.getId() +
                ", customer_nationalCode=" + customer.getNationalCode() +
                ", numberOfOrder=" + numberOfOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingBasket that = (ShoppingBasket) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
