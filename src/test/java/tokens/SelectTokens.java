package tokens;

import java.sql.*;
import java.util.Scanner;

public class SelectTokens {
    static Connection con = null;
    static String url = "jdbc:mysql://localhost/carnival";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int token_amount;

    public static void runBuyTokens(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            buyToken();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void buyToken() throws Exception {

        PreparedStatement ps;
        ResultSet rs;

        tokenPrices();

        con = DriverManager.getConnection(url, username, password);

        readIntoTokens(inputD);

        ps = con.prepareStatement("select * from tokens where Number_of_Tokens=?");
        ps.setInt(1, token_amount);

        rs = ps.executeQuery();

        while (rs.next()) {
            int num_tokens = rs.getInt("Number_of_Tokens");
            double token_price = rs.getDouble("Price_of_Tokens");


            String sql1 = "INSERT INTO purchased_tokens"
                    + "(Number_of_Tokens, Token_Prices)"
                    + "VALUES (?, ?)";

            PreparedStatement states = con.prepareStatement(sql1);

            states.setInt(1, num_tokens);
            states.setDouble(2, token_price);

            states.executeUpdate();
        }
    }
    public static void tokenPrices() {
        try {
            String sql2 = "select * from tokens";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("Menu\n");
            while (p2.next()) {
                int token_amount = p2.getInt("Number_of_Tokens");
                double token_price = p2.getDouble("Price_of_Tokens");

                String item =token_amount + " Token is R" + token_price;
                System.out.println(item);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readIntoTokens(Scanner inputD) {
        System.out.print("Choose amount of Tokens: ");
        token_amount = inputD.nextInt();
    }
}

