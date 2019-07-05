package main.java.CoffeMaker;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class TestCoffeeMaker {
    public static void main(String args[]){
        int option, cupsNumber;
        CoffeMaker coffeeMaker = new CoffeMaker("DEV01", 12);
        Scanner reader = new Scanner(System.in);
        do {
            System.out.println("*-*-*-*-*-*- COFFEE MAKER -*-*-*-*-*-*");
            System.out.println("*-*-*- Model: "+ coffeeMaker.getModel()+ " -*-*-*-*-*-*-*-*-*-*");
            System.out.println("Options:");
            System.out.println("1. Press 'Start' button (Make coffee)");
            System.out.println("2. Serve coffee (take out coffee pot)");
            System.out.println("3. Quit");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.print("Make your choice: ");
            option = reader.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Coffee cups to make: ");
                    cupsNumber=reader.nextInt();
                    coffeeMaker.makeCoffe(cupsNumber);
                    coffeeMaker.displayDevicesState();
                    break;
                case 2:
                    coffeeMaker.serveCoffee();
                    coffeeMaker.displayDevicesState();
                    break;
                case 3:
                    System.out.println("Shut down...");
                    try{
                        sleep(1000);
                    }catch (InterruptedException ex){}

                    break;
                default:
                    System.out.println("Invalid data...");
                    option=0;
                    break;
            }
        } while (option != 3);
    }
}
