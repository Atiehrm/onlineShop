package models.enums;

public enum ShoeType {
    SPORT(1, "SPORT"),
    HIGHHEELS(2, "HIGHHEELS"),
    OXFORD(3, "OXFORD"),
    UNKNOWN(4, "UNKNOWN");
    private int id;
    private String name;

    ShoeType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShoeType getVal(String name) {
       /* ShoeType shoeTypeHolder = null;
        try {
            for (ShoeType shoeType : values()) {
                if (shoeType.name.equalsIgnoreCase(name))
                    shoeTypeHolder = shoeType;
                return shoeTypeHolder;
            }
        } catch (IllegalArgumentException e) {
            shoeTypeHolder = UNKNOWN;
            return shoeTypeHolder;
        }
        return shoeTypeHolder;
    }*/
        ShoeType shoeType;
        try {
            shoeType = valueOf(name.trim());
        } catch (IllegalArgumentException e) {
            shoeType = UNKNOWN;
        }
        return shoeType;
    }
}
