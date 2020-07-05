package facaltyOps;

import java.sql.*;
import java.util.Scanner;

public class InTheShop {

    static Connection con = null;

    public static void runShopinfo(){
        try {
            String url = "jdbc:mysql://localhost/carnival";
            String username = "root";
            String password =  "will12boskowski1999";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            shopInfo();
            con.close();


        }catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

    static void shopInfo(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'display' to display Shop Items: " +
                "\nEnter 'input' to insert new Items: " +
                "\nEnter 'quit' to exit Shop Options: ");
        String action = "";
        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "display":
                    displayShop();
                    break;
                case "input":
                    readInProductDetails(input);
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

    public static void displayShop() {
        String sql = "select * from shop";
        System.out.println("\nShop Information:\n");
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                int prod_id = rs.getInt("Product_Id");
                String prod_name = rs.getString("Product_Name");
                int tokens_needed = rs.getInt("Tokens_Needed");

                System.out.println(prod_id + " " + prod_name + " Number of Tokens : " + tokens_needed);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readInProductDetails(Scanner input) {
        Product product = new Product();

        System.out.print("Product ID: ");
        product.setProd_id(input.nextInt());
        System.out.println("Product Name: ");
        product.setProd_name(input.next());
        System.out.println("Tokens Needed: ");
        product.setTokens_needed(input.nextInt());

        productUpdate(product);
    }

    public static void productUpdate(Product product) {
        try {
            String sqls = "INSERT INTO shop"
                    + "(Product_Id, Product_Name, Tokens_Needed)"
                    + "VALUES (?, ?, ?)";

            PreparedStatement state = con.prepareStatement(sqls);

            state.setInt(1, product.getProd_id());
            state.setString(2, product.getProd_name());
            state.setInt(3, product.getTokens_needed());

            int ci = state.executeUpdate();
            if (ci > 0) {
                System.out.println("Product Information Updated" +
                        "\nEnter 'quit' to exit: ");
            }

        } catch (SQLException e2) {
            System.err.println(e2.getMessage());
        }

    }

    private static class Product {
        private int prod_id;
        private String prod_name;
        private int tokens_needed;

        public int getProd_id() {
            return prod_id;
        }
        public void setProd_id(int prod_id) {
            this.prod_id = prod_id;
        }

        public String getProd_name(){
            return prod_name;
        }
        public void setProd_name(String prod_name){
            this.prod_name = prod_name;
        }

        public int getTokens_needed() {
            return tokens_needed;
        }
        public void setTokens_needed(int tokens_needed){
            this.tokens_needed = tokens_needed;
        }
    }
}