package models;

import models.enums.ProductCategory;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private ProductCategory productCategory;
    private int stock;
    private double price;

    public Product(String name, ProductCategory productCategory, int stock, double price) {
        this.name = name;
        this.productCategory = productCategory;
        this.stock = stock;
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCategory=" + productCategory +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && productCategory == product.productCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, productCategory);
    }
}
