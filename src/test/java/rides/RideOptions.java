package rides;

import java.util.Scanner;

public class RideOptions {

    public static void runRideOptions() {

        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("\nEnter 'go' to go on Ride: " +
                "\nEnter 'quit' to go back: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "go":
                    SelectRide.runRideSelect();
                    PayForRide.runRidePayment();
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
        System.out.println("\nEnter 'go' to go on Ride: " +
                "\nEnter 'quit' to go to menu: \n");
    }
}

