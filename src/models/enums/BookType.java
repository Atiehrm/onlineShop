package models.enums;

public enum BookType {
    HISTORICAL(1, "HISTORICAL"),
    MAGAZINE(2, "MAGAZINE"),
    SCIENTIFIC(3, "SCIENTIFIC"),
    OTHER(4, "OTHER"),
    UNKNOWN(5, "UNKNOWN");
    private int id;
    private String name;

    BookType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookType getVal(String name) {
        BookType bookType;
        try {
            bookType = valueOf(name.trim());
        } catch (IllegalArgumentException e) {
            bookType = UNKNOWN;
        }
        return bookType;
    }
}
