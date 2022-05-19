package enums;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");
    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
