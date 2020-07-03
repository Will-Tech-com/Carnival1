package tokens;

import java.util.Scanner;

public class TokenOptions {

    public static void main(String [] args){
        runTokenOptions();
    }

    public static void runTokenOptions() {
        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("\nEnter 'tbuy' to Purchase Tokens: " +
                "\nEnter 'quit' to go back to Menu: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "tbuy":
                    SelectTokens.runBuyTokens();
                    TokenPurchase.runPayment();
                    afterOpt();
                    break;
                case "quit":
                    System.out.println("Exiting Tokken Purchase Options...");
                    break;
                default:
                    System.out.println("Value entered was Invalid... Please Try Again");
            }
        }
    }
    static void afterOpt(){
        System.out.println("\nEnter 'tbuy' to Purchase Tokens: " +
                "\nEnter 'quit' to go back to Menu: \n");
    }
}

