package models.enums;

import java.util.Locale;

public enum ProductCategory {
    ELECTRONIC(1, "electronic"),
    SHOES(2, "shoes"),
    READINGS(3, "readings"),
    UNKNOWN(4, "unknown");
    private String label = null;
    private int id;

    ProductCategory(int id, String label) {
        this.label = label;
        this.id = id;
    }

    public static ProductCategory getVal(String label) {
        ProductCategory productCategory;
        try {
            productCategory = valueOf(label.trim().toLowerCase());
        } catch (IllegalArgumentException exception) {
            productCategory = UNKNOWN;

        }
        return productCategory;

        /*switch (label.trim().toLowerCase(Locale.ROOT)){
            case "electronic":
                return ELECTRONIC;
            case "shoes":
                return SHOES;
            case "readings":
                return READINGS;
            default:
                return UNKNOWN;
        }*/





    }

}
