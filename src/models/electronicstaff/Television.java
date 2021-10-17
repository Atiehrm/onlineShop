package models.electronicstaff;

import models.Product;
import models.enums.ElectronicBrands;
import models.enums.ResolutionTvType;

public class Television extends Product {
    private ElectronicBrands electronicBrands;
    private String screenSize;
    private ResolutionTvType resolutionTvType;

    public ElectronicBrands getElectronicBrands() {
        return electronicBrands;
    }

    public void setElectronicBrands(ElectronicBrands electronicBrands) {
        this.electronicBrands = electronicBrands;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public ResolutionTvType getResolutionTvType() {
        return resolutionTvType;
    }

    public void setResolutionTvType(ResolutionTvType resolutionTvType) {
        this.resolutionTvType = resolutionTvType;
    }
}
