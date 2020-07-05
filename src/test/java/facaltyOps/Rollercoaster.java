package facaltyOps;

import java.sql.*;
import java.util.Scanner;

public class Rollercoaster {

    static Connection con = null;

    public static void runRollercoaster(){
        try {
            String url = "jdbc:mysql://localhost/carnival";
            String username = "root";
            String password =  "will12boskowski1999";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            rollercoasterInfo();
            con.close();


        }catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

    static void rollercoasterInfo(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'display' to display Rollercoaster: " +
                "\nEnter 'input' to add new Rollercoaster: " +
                "\nEnter 'quit' to exit Rollercoaster Options: ");
        String action = "";
        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "display":
                    displayRollercoasters();
                    break;
                case "input":
                    readInRideDetails(input);
                    break;
                case "quit":
                    System.out.println("Exiting Shop Options");
                    break;
                default:
                    System.out.println("Invalid Entry... " +
                            "\nPlease try again...");
                    break;
            }
        }
    }

    public static void displayRollercoasters() {
        String sql = "select * from rides";
        System.out.println("\nRollercoaster Information:\n");
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                int ride_id = rs.getInt("Ride_Id");
                String ride_name = rs.getString("Ride_Name");
                int tokens_needed = rs.getInt("Tokens_Needed");
                double height = rs.getDouble("Height_Must_Be");

                System.out.println(ride_id + " " + ride_name + " Number of Tokens : " + tokens_needed + " " + height + "m");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readInRideDetails(Scanner input) {
        Rides ride = new Rides();

        System.out.print("Ride ID: ");
        ride.setRide_id(input.nextInt());
        System.out.println("Ride Name: ");
        ride.setRide_name(input.next());
        System.out.println("Tokens Needed: ");
        ride.setTokens_needed(input.nextInt());
        System.out.println("Height Needed: ");
        ride.setHeight(input.nextDouble());

        rideUpdate(ride);
    }

    public static void rideUpdate(Rides ride) {
        try {
            String sqls = "INSERT INTO rides"
                    + "(Ride_Id, Ride_Name, Tokens_Needed, Height_Must_Be)"
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement state = con.prepareStatement(sqls);

            state.setInt(1, ride.getRide_id());
            state.setString(2, ride.getRide_name());
            state.setInt(3, ride.getTokens_needed());
            state.setDouble(4, ride.getHeight());

            int ci = state.executeUpdate();
            if (ci > 0) {
                System.out.println("Rollercoaster Information Updated" +
                        "\nEnter 'quit' to exit: ");
            }

        } catch (SQLException e2) {
            System.err.println(e2.getMessage());
        }

    }

    private static class Rides {
        private int ride_id;
        private String ride_name;
        private int tokens_needed;
        private double height;

        public int getRide_id() {
            return ride_id;
        }
        public void setRide_id(int ride_id) {
            this.ride_id = ride_id;
        }

        public String getRide_name(){
            return ride_name;
        }
        public void setRide_name(String ride_name){
            this.ride_name = ride_name;
        }

        public int getTokens_needed() {
            return tokens_needed;
        }
        public void setTokens_needed(int tokens_needed){
            this.tokens_needed = tokens_needed;
        }
        public double getHeight(){
            return height;
        }
        public void setHeight(double height) {
            this.height = height;
        }
    }
}