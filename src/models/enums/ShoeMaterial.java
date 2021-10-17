package models.enums;

public enum ShoeMaterial {
    TEXTILE(1, "TEXTILE"),
    LEATHER(2, "LEATHER"),
    UNKNOWN(3, "UNKNOWN");
    private int id;
    private String name;

    ShoeMaterial(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShoeMaterial getVal(String name) {
        ShoeMaterial shoeMaterial;
        try {
            shoeMaterial = valueOf(name.trim());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
        return shoeMaterial;
    }
}
