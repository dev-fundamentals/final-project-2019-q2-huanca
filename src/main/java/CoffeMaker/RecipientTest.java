package main.java.CoffeMaker;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipientTest {
    @Test
    public void getCapacityAvailableTest(){
        Recipient recipient = new Recipient("Water Boiler", 12, "cups");
        recipient.fill(12);
        recipient.takeCups(4);
        int availableQty = 8;
        assertEquals(availableQty, recipient.getAvailableQty());
    }
    @Test
    public void take_qtyFromEmptyRec_resultZero(){
        Recipient recipient = new Recipient("Water Boiler", 12, "cups");
        recipient.takeCups(4);
        assertEquals(0, recipient.getAvailableQty());
    }
}