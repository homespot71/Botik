package Botik.service;


public enum Command {

    START("/start"), STOP("/stop");

    private final String name;


    Command(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
