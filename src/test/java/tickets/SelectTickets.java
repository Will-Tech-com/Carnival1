package tickets;

import java.sql.*;
import java.util.Scanner;

public class SelectTickets {

    static Connection con = null;
    static String url = "jdbc:mysql://localhost/carnival";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int ticket_id;

    public static void runBuyTickets(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            buyTickets();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void buyTickets() throws Exception {

        PreparedStatement ps;
        ResultSet rs;

        ticketPrices();

        con = DriverManager.getConnection(url, username, password);

        readIntoTickets(inputD);

        ps = con.prepareStatement("select * from tickets where Ticket_Id=?");
        ps.setInt(1, ticket_id);

        rs = ps.executeQuery();

        while (rs.next()) {
            int ticket_id = rs.getInt("Ticket_Id");
            String ticket_type= rs.getString("Ticket_type");
            double ticket_price = rs.getDouble("Ticket_Price");


            String sql1 = "INSERT INTO purchased_tickets"
                    + "(Ticket_Id, Ticket_Type, Ticket_Price)"
                    + "VALUES (?, ?, ?)";

            PreparedStatement states = con.prepareStatement(sql1);

            states.setInt(1, ticket_id);
            states.setString(2, ticket_type);
            states.setDouble(3, ticket_price);

            states.executeUpdate();
        }
    }
    public static void ticketPrices() {
        try {
            String sql2 = "select * from tickets";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("Ticket Menu\n");
            while (p2.next()) {
                int ticket_id = p2.getInt("Ticket_Id");
                String ticket_type = p2.getString("Ticket_Type");
                double ticket_price = p2.getDouble("Ticket_Price");
                int people_num = p2.getInt("Amount_of_People");

                String item =ticket_id + " "+ ticket_type+ " R" + ticket_price + " Number of People " + people_num;
                System.out.println(item);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readIntoTickets(Scanner inputD) {
        System.out.print("Choose Ticket ID: ");
        ticket_id = inputD.nextInt();
    }
}

