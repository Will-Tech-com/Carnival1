import facaltyOps.Customer1;
import facaltyOps.FacaltyOptions;
import tickets.TicketOptions;

import java.util.Scanner;

public class EnterCarnival {


    public static void main(String[] args){
        enterCarnival();
    }

    public static void enterCarnival(){
        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("Enter 'expo' to Explore Carnival: \n" +
                "Enter 'facalty' for Carnival Details and option: \n" +
                "Enter 'quit' if you already have a ticket: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "expo":
                    TicketOptions.runTicketOptions();
                    Explore.exploreC();
                    break;
                case "facalty":
                    FacaltyOptions.runFacaltyOptions();
                    break;
                case "quit":
                    System.out.println("Exitting the Carnival...");
                    break;
                default:
                    System.out.println("Invalid Input...Please try again.");
            }
        }
    }
}
