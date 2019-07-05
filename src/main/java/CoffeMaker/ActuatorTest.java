package main.java.CoffeMaker;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActuatorTest {
    @Test
    public void getnameTest() {
        Actuator act = new Actuator("valvula", true);
        String name = "valvula";
        assertEquals(name, act.getName());
    }

    @Test
    public void getActive() {
        Actuator act = new Actuator("valvula", true);
        assertEquals(true, act.getActiveStatus());
    }

    @Test
    public void actuatorOn_initialOff_expectedOn() {
        Actuator act = new Actuator("valvula", false);
        act.actuatorOn();
        assertTrue(act.getActiveStatus());
    }

    @Test
    public void actuatorOff_initialOn_expectedOff() {
        Actuator act = new Actuator("valvula", true);
        act.actuatorOff();
        assertFalse(act.getActiveStatus());
    }
}