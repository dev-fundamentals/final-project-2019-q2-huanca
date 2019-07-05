package main.java.CoffeMaker;

public class Recipient {
    private String type;
    private int currentLevel;
    private int maxCapacity;
    private String unit;
    private Sensor sensor;

    public Recipient(String type, int maxCapacity, String unit) {
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.unit = unit;
        currentLevel = 0;
    }

    public Recipient(String type, int maxCapacity, String unit, Sensor sensor) {
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.unit = unit;
        this.sensor = sensor;
        currentLevel = 0;
    }

    public String getType() {
        return type;
    }

    public int getAvailableQtyToFill() {
        return maxCapacity - currentLevel;
    }

    public int getAvailableQty() {
        return currentLevel;
    }

    public void fill(int qtyToFill) {
        if (currentLevel + qtyToFill > maxCapacity) {
            System.out.println("Capacity exceeded...");
        } else {
            currentLevel += qtyToFill;
            System.out.println("Quantity allowed, quantity available " + currentLevel + " " + unit + " of " + type);

        }
    }

    public void fillWithSensor(int qtyToFill) {
        if (currentLevel + qtyToFill > maxCapacity) {
            System.out.println("Capacity exceeded...");
        } else {
            currentLevel += qtyToFill;
            System.out.println("Quantity allowed, quantity available " + currentLevel + " " + unit + " of " + type);

        }
        sensor.stateOn();
    }

    public String messageToDisplay(int type) {
        switch (type) {
            case 1:
                return "Quantity not available, please add more " + type;
            default:
                return "Empty String...";
        }

    }

    public void takeCups(int cupsToTake) {
        if (isEmpty() || cupsToTake > currentLevel) {
            System.out.println(messageToDisplay(1));
        } else {
            currentLevel -= cupsToTake;
        }
    }
    public void takeCupsWithSensor(int cupsToTake) {
        if (isEmpty() || cupsToTake > currentLevel) {
            System.out.println(messageToDisplay(1));
        } else {
            currentLevel -= cupsToTake;
            if(isEmpty()){
                sensor.stateOff();
            }
        }
    }

    public boolean isEmpty() {
        return (currentLevel == 0);
    }

    public boolean placeOut() {
        return sensor.getState();
    }
}
