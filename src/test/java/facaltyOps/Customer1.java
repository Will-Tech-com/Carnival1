package facaltyOps;

import java.sql.*;
import java.util.Scanner;

public class Customer1 {

    static Connection con = null;

    public static void runCustomer(){
        try {
            String url = "jdbc:mysql://localhost/carnival";
            String username = "root";
            String password =  "will12boskowski1999";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            customerInfo();
            con.close();


        }catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

    static void customerInfo(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'display' to show Customer Information: " +
                "\nEnter 'input' to create new Customer Profile: " +
                "\nEnter 'quit' to exit Customer Options: ");
        String action = "";
        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "display":
                    displayInfo();
                    break;
                case "input":
                    readInCustomerDetails(input);
                    break;
                case "quit":
                    System.out.println("Exiting Customer options");
                    break;
                default:
                    System.out.println("Action does not Exist... " +
                            "\nPlease try again...");
                    break;
            }
        }
    }

    public static void displayInfo() {
        String sql = "select * from customer";
        System.out.println("\nCustomer Information:\n");
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                int cust_id = rs.getInt("Customer_ID");
                double cust_balance = rs.getDouble("Customer_Balance");
                double cust_height = rs.getDouble("Customer_Height");

                System.out.println(cust_id + " R" + cust_balance + " " + cust_height +"m" );
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readInCustomerDetails(Scanner input) {
        Customer customer = new Customer();

        System.out.print("Customer ID: ");
        customer.setCust_id(input.nextInt());
        System.out.println("Enter Customer Balance: ");
        customer.setCust_balance(input.nextDouble());
        System.out.println("Enter Customer Height: ");
        customer.setCust_height(input.nextDouble());

        customerUpdate(customer);
    }

    public static void customerUpdate(Customer customer) {
        try {
            String sqls = "INSERT INTO customer"
                    + "(Customer_ID, Customer_Balance, Customer_Height)"
                    + "VALUES (?, ?, ?)";

            PreparedStatement state = con.prepareStatement(sqls);

            state.setInt(1, customer.getCust_id());
            state.setDouble(2,customer.getCust_balance());
            state.setDouble(3, customer.getCust_height());

            int ci = state.executeUpdate();
            if (ci > 0) {
                System.out.println("Customer Information Updated" +
                        "\nEnter 'quit' to exit: ");
            }

        } catch (SQLException e2) {
            System.err.println(e2.getMessage());
        }

    }

    private static class Customer {
        private int cust_id;
        private double cust_balance;
        private double cust_height;

        public int getCust_id() {
            return cust_id;
        }
        public void setCust_id(int cust_id) {
            this.cust_id = cust_id;
        }

        public double getCust_balance(){
            return cust_balance;
        }
        public void setCust_balance(double cust_balance){
            this.cust_balance = cust_balance;
        }

        public double getCust_height() {
            return cust_height;
        }
        public void setCust_height(double cust_height){
            this.cust_height = cust_height;
        }
    }
}
