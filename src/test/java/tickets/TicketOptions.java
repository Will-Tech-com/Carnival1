package tickets;

import java.util.Scanner;

public class TicketOptions {

    public static void runTicketOptions() {
        System.out.println("In order to enter the Carnival, you'll need a Ticket");

        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("\nEnter 'tbuy' to Purchase Tickets: " +
                "\nEnter 'quit' if you already have a Ticket: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "tbuy":
                    SelectTickets.runBuyTickets();
                    TicketPurchase.runPayment();
                    afterOpt();
                    break;
                case "quit":
                    System.out.println("Exiting Ticket Purchase Options...");
                    break;
                default:
                    System.out.println("Value entered was Invalid... Please Try Again");
            }
        }
    }
    static void afterOpt(){
        System.out.println("\nEnter 'tbuy' to Purchase Tickets: " +
                "\nEnter 'quit' to go to Menu: \n");
    }
}
