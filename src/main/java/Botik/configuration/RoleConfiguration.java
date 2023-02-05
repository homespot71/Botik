package Botik.configuration;


public enum RoleConfiguration {

    ADMIN("Administrator"), USER("User");

    private final String name;


    RoleConfiguration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}