package shop;

import java.util.Scanner;

public class ProductOptions {

    public static void runShopOptions() {

        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("\nEnter 'buy' to Purchase Items: " +
                "\nEnter 'quit' to go back: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "buy":
                    SelectItems.runSelect();
                    BuyItems.runItemPayment();
                    afterOpt();
                    break;
                case "quit":
                    System.out.println("Exiting Shop Options...");
                    break;
                default:
                    System.out.println("Value entered was Invalid... Please Try Again");
            }
        }
    }
    static void afterOpt(){
        System.out.println("\nEnter 'buy' to Purchase Items: " +
                "\nEnter 'quit' to go to Menu: \n");
    }
}

