package models.enums;

public enum ResolutionTvType {

    HD(1, ""),
    FULLHD(2, ""),
    UNKNOWN(3, "UNKNOWN");
    private int id;
    private String name;

    ResolutionTvType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ResolutionTvType getVal(String name) {
        ResolutionTvType resolutionTvType;
        try {
            resolutionTvType = valueOf(name.trim());
        } catch (IllegalArgumentException e) {
            resolutionTvType = UNKNOWN;
        }
        return resolutionTvType;
    }
}
