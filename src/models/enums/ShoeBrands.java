package models.enums;

import java.security.PublicKey;
import java.util.Locale;

public enum ShoeBrands {
    NIKE(1, "NIKE"),
    MICHAELKORS(2, "MICHAELKORS"),
    ADIDAS(3, "ADIDAS"),
    VALENTINO(4, "VALENTINO"),
    UNKNOWN(5, "UNKNOWN");

    private int id;
    private String name;

    ShoeBrands(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShoeBrands getVal(String name) {
        switch (name.trim().toLowerCase()) {
            case "nike":
                return NIKE;
            case "michaelkors":
                return MICHAELKORS;
            case "adidas":
                return ADIDAS;
            case "valentino":
                return VALENTINO;
            default:
                return UNKNOWN;
        }
    }
}
