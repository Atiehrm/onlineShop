package models.enums;

public enum ElectronicBrands {
    SAMSUNG(1, "samsung"),
    LG(2, "lg"),
    EMERSUN(3, "emersun"),
    SMEG(4, "smeg"),
    UNKNOWN(5, "unknown");
    private int id;
    private String name;

    ElectronicBrands(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ElectronicBrands getVal(String name) {
        ElectronicBrands electronicBrands;
        try {
            electronicBrands = valueOf(name);
        } catch (IllegalArgumentException e) {
            electronicBrands = UNKNOWN;
        }
        return electronicBrands;
    }
}
