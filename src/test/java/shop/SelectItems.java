package shop;

import java.sql.*;
import java.util.Scanner;

public class SelectItems {

    static Connection con = null;
    static String url = "jdbc:mysql://localhost/carnival";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int prod_id;

    public static void runSelect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            select();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void select() throws Exception {

        PreparedStatement ps;
        ResultSet rs;

        productsToBuy();

        con = DriverManager.getConnection(url, username, password);

        readIntoTickets(inputD);

        ps = con.prepareStatement("select * from shop where Product_Id=?");
        ps.setInt(1, prod_id);

        rs = ps.executeQuery();

        while (rs.next()) {
            String prod_name = rs.getString("Product_Name");
            int tokens = rs.getInt("Tokens_Needed");


            String sql1 = "INSERT INTO shop_purchases"
                    + "(Product_Name, Tokens_Needed)"
                    + "VALUES (?, ?)";

            PreparedStatement states = con.prepareStatement(sql1);

            states.setString(1, prod_name);
            states.setInt(2, tokens);


            states.executeUpdate();
        }
    }
    public static void productsToBuy() {
        try {
            String sql2 = "select * from shop";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("Shop Menu\n");
            while (p2.next()) {
                int prod_id = p2.getInt("Product_Id");
                String prod_name = p2.getString("Product_Name");
                int tokens = p2.getInt("Tokens-Needed");

                String item = prod_id + " "+ prod_name+ " Number of tokens needed " + tokens ;
                System.out.println(item);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readIntoTickets(Scanner inputD) {
        System.out.print("Choose Product ID: ");
        prod_id = inputD.nextInt();
    }
}

