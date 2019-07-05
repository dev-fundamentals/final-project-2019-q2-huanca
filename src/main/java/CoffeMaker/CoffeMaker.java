package main.java.CoffeMaker;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class CoffeMaker {

    private String model;
    private int cupsCapacity;
    private Recipient waterBoiler;
    private Recipient coffeePot;
    private Actuator pressureReliefValve;
    private Actuator boilerHeater;
    private Actuator plateHeater;
    private Actuator indicatorLight;
    private ArrayList<Actuator> waterLevelActuators;
    private Sensor waterLevel;
    private ArrayList<Actuator> pressureSensorActuators;
    private Sensor pressureSensor;
    private Sensor startButton;

    public CoffeMaker(String model, int cupsCapacity) {
        this.model = model;
        this.cupsCapacity = cupsCapacity;
        waterBoiler = new Recipient("Water", cupsCapacity, "cups");
        pressureSensorActuators = new ArrayList<Actuator>();
        pressureSensorActuators.add(plateHeater);
        pressureSensor = new Sensor("Plate Sensor", false, pressureSensorActuators);
        coffeePot = new Recipient("Coffee", cupsCapacity, "cups", pressureSensor);
        pressureReliefValve = new Actuator("Pressure relief valve", false);
        boilerHeater = new Actuator("Boiler Heater", false);
        plateHeater = new Actuator("Plate Heater", false);
        indicatorLight = new Actuator("Indicator Light", false);
        waterLevelActuators = new ArrayList<Actuator>();
        waterLevelActuators.add(boilerHeater);
        waterLevel = new Sensor("Water Level", false, waterLevelActuators);
    }

    public void init() {
        if (waterBoiler.isEmpty()) {
            int waterCupsToFill;
            System.out.println("Please, add water to the boiler");
            do {
                System.out.print("Water cups to fill (" + waterBoiler.getAvailableQtyToFill() + " cups allowed): ");
                waterCupsToFill = read();
            } while (controlInitialWaterQty(waterCupsToFill) || waterCupsToFill <= 0);
            waterLevel.stateOn();
            waterBoiler.fill(waterCupsToFill);
        } else {
            System.out.println("There are " + waterBoiler.getAvailableQty() + " cups available");
        }
    }

    public void getEnoughWater(int coffeeCups) {
        if (waterBoiler.getAvailableQty() < coffeeCups) {
            int waterCupsToFill;
            System.out.println("Please, add more water...");
            do {
                System.out.print("Water cups to fill (" + waterBoiler.getAvailableQtyToFill() + " cups allowed): ");
                waterCupsToFill = read();
            } while (controlInitialWaterQty(waterCupsToFill) || waterCupsToFill <= 0);
            waterBoiler.fill(waterCupsToFill);
        }
        System.out.println("There are " + waterBoiler.getAvailableQty() + " cups available");
    }

    public void displayDevicesState() {
        System.out.println("Boiler Heater - Active?: " + boilerHeater.getActiveStatus());
        System.out.println("Plate Heater - Active?: " + plateHeater.getActiveStatus());
        System.out.println("Pressure Sensor - Active?: " + pressureSensor.getState());
        System.out.println("Coffee Pot - Empty?: " + coffeePot.isEmpty());
        System.out.println("Water Boiler - Empty?: " + waterBoiler.isEmpty());
        System.out.println("Indicator Light - On?: " + indicatorLight.getActiveStatus());
        System.out.println("Pressure Relief Valve - Closed?: " + pressureReliefValve.getActiveStatus());
    }

    public boolean controlInitialWaterQty(int waterCupsToFill) {
        return (waterCupsToFill > cupsCapacity);
    }

    public String getModel() {
        return model;
    }

    public int read() {
        Scanner reader = new Scanner(System.in);
        return reader.nextInt();
    }

    public void makeCoffe(int cupsToMake) {
        if (waterBoiler.isEmpty()) {
            System.out.println("Water boiler is empty");
            init();
        } else {
            if (waterBoiler.getAvailableQty() < cupsToMake) {
                System.out.println("There is not enough "+ waterBoiler.getType());
                getEnoughWater(cupsToMake);
            } else {
                System.out.println("Making...");
                indicatorLight.actuatorOn();
                pressureReliefValve.actuatorOn();
                displayDevicesState();
                for (int i = 1; i <= cupsToMake; i++) {
                    System.out.println("Coffee cup " + i);
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                    }
                }
                indicatorLight.actuatorOff();
                pressureReliefValve.actuatorOff();
                displayDevicesState();
                waterBoiler.takeCups(cupsToMake);
                if (waterBoiler.isEmpty()) {
                    waterLevel.stateOff();
                }
                coffeePot.fillWithSensor(cupsToMake);
            }
        }
        //displayDevicesState();
    }

    public int getAvailableCups() {
        return waterBoiler.getAvailableQty();
    }

    public void serveCoffee() {
        if (!coffeePot.isEmpty()) {
            System.out.print("Number of cups to serve: ");
            coffeePot.takeCupsWithSensor(read());
        } else {
            System.out.println("Coffee pot is empty, make before serving");
        }
    }
}

