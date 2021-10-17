package models.enums;

public enum RefrigeratorType {
    SIDEBYSIDE(1, "SIDEBYSIDE"),
    MINIFRIDGE(2, "MINIFRIDGE"),
    TWIN(3, "TWIN"),
    UNKNOWN(4, "UNKNOWN");
    private int id;
    private String name;

    RefrigeratorType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RefrigeratorType getVal(String name) {
        RefrigeratorType refrigeratorType;
        try {
            refrigeratorType = valueOf(name.trim());
        } catch (IllegalArgumentException e) {
            refrigeratorType = UNKNOWN;
        }
        return refrigeratorType;
    }
}
