import rides.RideOptions;
import shop.ProductOptions;
import tokens.TokenOptions;

import java.util.Scanner;

public class Explore {

    public static void main(String [] args){
        exploreC();
    }

    public static void exploreC(){
        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("Enter 'tt' to buy Tokens: \n" +
                "Enter 'rides' to go on Rollercoasters: \n" +
                "Enter 'shop' to purchase snacks from Store: \n" +
                "Enter 'quit' to go to Menu: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "tt":
                    TokenOptions.runTokenOptions();
                    afterOptions();
                    break;
                case "rides":
                    RideOptions.runRideOptions();
                    afterOptions();
                    break;
                case "shop":
                    ProductOptions.runShopOptions();
                    afterOptions();
                    break;
                case "quit":
                    System.out.println("Exitting the Carnival...");

                    System.out.println("\nEnter 'expo' to Explore Carnival: \n" +
                            "Enter 'cust' for Customer Details: \n" +
                            "Enter 'carn' for Carnival Details and option: \n" +
                            "Enter 'quit' to exit carnival: \n");
                    break;
                default:
                    System.out.println("Invalid Input...Please try again.");
            }
        }
    }
    public static void afterOptions(){
        System.out.println("Enter 'tt' to buy Tokens: \n" +
                "Enter 'rides' to go on Rollercoasters: \n" +
                "Enter 'shop' to purchase snacks from Store: \n" +
                "Enter 'quit' to go to Menu: \n");
    }
}
