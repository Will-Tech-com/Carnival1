package rides;

import java.sql.*;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PayForRide {

    static Connection con;
    static String url = "jdbc:mysql://localhost/carnival";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int cust_id;


    public static void runRidePayment(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            ridePayment();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ridePayment() {
        PreparedStatement ps1;
        ResultSet rs1;

        try {
            con = DriverManager.getConnection(url, username, password);
            readInForPayment(inputD);

            ps1 = con.prepareStatement("select * from customer where Customer_Id=?");
            ps1.setInt(1, cust_id);

            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int cust_id = rs1.getInt("Customer_Id");
                int cust_tokens = rs1.getInt("Customer_Tokens");
                double cust_height = rs1.getDouble("Customer_Height");

                System.out.println("Customer ID: " + cust_id);
                System.out.println("Customer Tokens: " + cust_tokens);
                System.out.println("Customer Height: " + cust_height);

                Statement stat1 = con.createStatement();
                ResultSet rs;
                String sql1 = "SELECT * FROM ride_payment";

                rs = stat1.executeQuery(sql1);

                while (rs.next()) {
                    double specific_height = rs.getDouble("Height_Must_Be");
                    int tokens = rs.getInt("Tokens_Needed");

                    if (cust_tokens >= tokens && cust_height >= specific_height) {
                        String sql = "UPDATE customer SET Customer_Tokens = ? WHERE Customer_Id = ?";

                        PreparedStatement state = con.prepareStatement(sql);

                        state.setDouble(1, cust_tokens - tokens);
                        state.setInt(2, cust_id);


                        int ci = state.executeUpdate();
                        if (ci > 0) {
                            System.out.println("Payment Update Successful" +
                                    "\nNo REFUNDS!!!");
                        }
                    }
                    else if (cust_tokens < tokens ) {
                        System.out.println("YOU DONT HAVE ENOUGH TOKENS!!!");
                    }
                    else if (cust_height < specific_height){
                        System.out.println("YOU TOO SHORT...");
                    }
                }
            }
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
    }
    public static void readInForPayment(Scanner inputD) {
        System.out.print("Enter Customer ID: ");
        cust_id = inputD.nextInt();
    }

}
