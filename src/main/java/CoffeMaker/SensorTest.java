package main.java.CoffeMaker;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SensorTest {
    @Test
    public void getOutput_outputFalse_resultFalse() {
        ArrayList<Actuator> devices = new ArrayList<>();
        Sensor sensor = new Sensor("sensor1",true, devices);
        assertTrue(sensor.getState());
    }

    @Test
    public void switchOutput_outputTrue_resultTrue() {
        ArrayList<Actuator> devices = new ArrayList<>();
        Sensor sensor = new Sensor("sensor1",true, devices);
        assertFalse(sensor.switchState());
    }

    @Test
    public void getRelatedDevices() {
        Actuator valvula = new Actuator("valvula", true);
        Actuator led = new Actuator("led", true);
        ArrayList<Actuator> devices = new ArrayList<>();
        devices.add(valvula);
        devices.add(led);
        String[] devicesRelated = new String[5];
        devicesRelated[0] = "valvula";
        devicesRelated[1] = "led";
        Sensor sensor = new Sensor("sensor1",true, devices);
        assertArrayEquals(devicesRelated, sensor.getRelatedActuators());

    }
    @Test
    public void statusOn_initialStateOff_expectedOn(){
        ArrayList<Actuator> devices = new ArrayList<>();
        Sensor sensor = new Sensor("sensor1",false, devices);
        sensor.stateOn();
        assertTrue(sensor.getState());
    }
    @Test
    public void statusOff_initialStateOn_expectedOn(){
        ArrayList<Actuator> devices = new ArrayList<>();
        Sensor sensor = new Sensor("sensor1",true, devices);
        sensor.stateOff();
        assertFalse(sensor.getState());
    }
}