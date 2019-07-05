package main.java.CoffeMaker;

import java.util.ArrayList;

public class Sensor {

    private String name;
    private boolean state;
    private static ArrayList<Actuator> relatedDevices;

    public Sensor(String name, boolean state, ArrayList<Actuator> devices) {
        this.name=name;
        this.state = state;
        this.relatedDevices = devices;
    }

    public static String[] getRelatedActuators() {
        String[] devices = new String[5];
        int i = 0;
        for (Actuator actuator1 : relatedDevices) {
            devices[i] = actuator1.getName();
            i++;
        }
        return devices;
    }

    public boolean getState() {
        return state;
    }

    public boolean switchState() {
        state = !state;
        return state;
    }

    public void stateOn() {
        state = true;
        for(Actuator actuator:relatedDevices){
            actuator.actuatorOn();
        }
    }

    public void stateOff() {
        state = false;
        for(Actuator actuator:relatedDevices){
            actuator.actuatorOff();
        }
    }
}
