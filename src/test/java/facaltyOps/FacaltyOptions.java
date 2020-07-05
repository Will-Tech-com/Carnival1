package facaltyOps;


import java.util.Scanner;

public class FacaltyOptions {

    public static void runFacaltyOptions() {

        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("\nEnter 'cust' for Customer Options: " +
                "\nEnter 'shop' for Shop Options: " +
                "\nEnter 'ride' for Rollercoaster Options: " +
                "\nEnter 'quit' to go back to Menu: \n");

        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "cust":
                    Customer1.runCustomer();
                    afterOpt();
                    break;
                case "shop":
                    InTheShop.runShopinfo();
                    afterOpt();
                    break;
                case "ride":
                    Rollercoaster.runRollercoaster();
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
        System.out.println("\nEnter 'cust' for Customer Options: " +
                "\nEnter 'shop' for Shop Options: " +
                "\nEnter 'ride' for Rollercoaster Options: " +
                "\nEnter 'quit' to go back to Menu: \n");
    }
}
