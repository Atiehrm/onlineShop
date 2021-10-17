package models.enums;

public enum EnergyConsumption {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    UNKNOWN("UNKNOWN");
    private String name;

    EnergyConsumption(String name) {
        this.name = name;
    }

    public EnergyConsumption getVal(String name) {
        EnergyConsumption energyConsumption;
        try {
            energyConsumption = valueOf(name.trim());
        } catch (IllegalArgumentException e) {
            energyConsumption = UNKNOWN;
        }
        return energyConsumption;
    }
}
