package rides;

import java.sql.*;
import java.util.Scanner;

public class SelectRide {

    static Connection con = null;
    static String url = "jdbc:mysql://localhost/carnival";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int ride_id;

    public static void runRideSelect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            selectRide();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void selectRide() throws Exception {

        PreparedStatement ps;
        ResultSet rs;

        ridesAvailable();

        con = DriverManager.getConnection(url, username, password);

        readIntoTickets(inputD);

        ps = con.prepareStatement("select * from rides where Ride_Id=?");
        ps.setInt(1, ride_id);

        rs = ps.executeQuery();

        while (rs.next()) {
            String ride_name = rs.getString("Ride_Name");
            double specific_height = rs.getDouble("Height_Must_Be");
            int tokens = rs.getInt("Tokens_Needed");


            String sql1 = "INSERT INTO ride_payment"
                    + "(Ride_Name ,Height_Must_Be, Tokens_Needed)"
                    + "VALUES (?, ?, ?)";

            PreparedStatement states = con.prepareStatement(sql1);

            states.setString(1, ride_name);
            states.setDouble(2, specific_height);
            states.setInt(3, tokens);


            states.executeUpdate();
        }
    }
    public static void ridesAvailable() {
        try {
            String sql2 = "select * from rides";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("Rides To Ride\n");
            while (p2.next()) {
                int ride_id = p2.getInt("Ride_Id");
                String ride_name = p2.getString("Ride_Name");
                int tokens = p2.getInt("Tokens_Needed");
                double specific_height = p2.getDouble("Height_Must_Be");

                String item = "ID " + ride_id + " "+ ride_name+ " Number of tokens needed  " + tokens + "  Height " + specific_height +"m"  ;
                System.out.println(item);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readIntoTickets(Scanner inputD) {
        System.out.print("To go on Ride " +
                "\nChoose Ride ID: ");
        ride_id = inputD.nextInt();
    }
}
