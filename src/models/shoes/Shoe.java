package models.shoes;

import models.Product;
import models.enums.ShoeBrands;
import models.enums.ShoeMaterial;
import models.enums.ShoeType;

public class Shoe extends Product {
    private ShoeBrands shoeBrands;
    private int height;
    private String color;
    private ShoeType shoeType;
    private ShoeMaterial shoeMaterial;

    public ShoeBrands getShoeBrands() {
        return shoeBrands;
    }

    public void setShoeBrands(ShoeBrands shoeBrands) {
        this.shoeBrands = shoeBrands;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ShoeType getShoeType() {
        return shoeType;
    }

    public void setShoeType(ShoeType shoeType) {
        this.shoeType = shoeType;
    }

    public ShoeMaterial getShoeMaterial() {
        return shoeMaterial;
    }

    public void setShoeMaterial(ShoeMaterial shoeMaterial) {
        this.shoeMaterial = shoeMaterial;
    }
}
