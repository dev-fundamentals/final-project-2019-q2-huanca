package main.java.CoffeMaker;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoffeMakerTest {
    @Test
    public void makeCoffe_threeCupsFromFull_expectedZeroCupsAvailable(){
        CoffeMaker coffemaker = new CoffeMaker("DEV01",12);
        coffemaker.makeCoffe(3);
        int cupsAvailable=0;
        assertEquals(cupsAvailable,coffemaker.getAvailableCups());
    }
}