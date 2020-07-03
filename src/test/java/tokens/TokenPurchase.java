package tokens;

import java.sql.*;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TokenPurchase {

    static Connection con;
    static String url = "jdbc:mysql://localhost/carnival";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);

    static int cust_id;

    public static void runPayment(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            payment();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void payment() {
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
                int cust_balance = rs1.getInt("Customer_Balance");
                double cust_height = rs1.getDouble("Customer_Height");


                System.out.println("Customer ID: " + cust_id);
                System.out.println("Customer Balance: " + cust_balance);
                System.out.println("Customer Height: " + cust_height);

                Statement stat1 = con.createStatement();
                ResultSet rs;
                String sql1 = "SELECT * FROM purchased_tokens";

                rs = stat1.executeQuery(sql1);

                while (rs.next()) {
                    int num_tokens = rs.getInt("Number_of_Tokens");
                    double price = rs.getDouble("Token_Prices");

                    String sql = "UPDATE customer SET Customer_Tokens = ?, Customer_Balance = ? WHERE Customer_Id = ?";

                    PreparedStatement state = con.prepareStatement(sql);

                    state.setInt(1, num_tokens);
                    state.setDouble(2, cust_balance - price);
                    state.setInt(3, cust_id);


                    int ci = state.executeUpdate();
                    if (ci > 0) {
                        System.out.println("Payment Update Successful" +
                                "\nNo REFUNDS!!!");
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
