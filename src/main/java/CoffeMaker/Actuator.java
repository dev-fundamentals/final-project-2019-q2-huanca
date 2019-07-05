package main.java.CoffeMaker;

public class Actuator {
    private String name;
    private boolean active;

    public Actuator(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean getActiveStatus() {
        return active;
    }

    public void actuatorOn() {
        active = true;
    }

    public void actuatorOff() {
        active = false;
    }
}
