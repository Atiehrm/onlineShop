package models.electronicstaff;


import models.Product;
import models.enums.ElectronicBrands;
import models.enums.EnergyConsumption;
import models.enums.RefrigeratorType;

public class Refrigerator extends Product {
    private String color;
    private ElectronicBrands electronicBrands;
    private int height;
    private int Weight;
    private RefrigeratorType refrigeratorType;
    private EnergyConsumption energyConsumption;
    private int numberOfFloors;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ElectronicBrands getElectronicBrands() {
        return electronicBrands;
    }

    public void setElectronicBrands(ElectronicBrands electronicBrands) {
        this.electronicBrands = electronicBrands;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public RefrigeratorType getRefrigeratorType() {
        return refrigeratorType;
    }

    public void setRefrigeratorType(RefrigeratorType refrigeratorType) {
        this.refrigeratorType = refrigeratorType;
    }

    public EnergyConsumption getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(EnergyConsumption energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}
