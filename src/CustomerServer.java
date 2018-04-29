import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class CustomerServer extends Thread {
    private ServerSocket listenSocket;

    public static void main(String args[]) {
        new CustomerServer();
    }

    private CustomerServer() {
        // Replace 97xx with your port number
        int port = 9735;
        try {
            listenSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Exception creating server socket: " + e);
            System.exit(1);
        }

        System.out.println("LOG: Server listening on port " + port);
        this.start();
    }

    /**
     * run()
     * The body of the server thread. Loops forever, listening for and
     * accepting connections from clients. For each connection, create a
     * new Conversation object to handle the communication through the
     * new Socket.
     */

    public void run() {
        try {
            while (true) {
                System.out.println("I am here");
                Socket clientSocket = listenSocket.accept();

                System.out.println("LOG: Client connected");

                // Create a Conversation object to handle this client and pass
                // it the Socket to use.  If needed, we could save the Conversation
                // object reference in an ArrayList. In this way we could later iterate
                // through this list looking for "dead" connections and reclaim
                // any resources.
                new Conversation(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Exception listening for connections: " + e);
        }
    }
}

/**
 * The Conversation class handles all communication with a client.
 */
class Conversation extends Thread {

    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    // Where JavaCustXX is your database name
    private static final String URL = "jdbc:mysql://courses:3306/JavaCust35";

    private Statement getAllStatement = null;
    private PreparedStatement addStatement = null;
    private PreparedStatement deleteStatement = null;
    private PreparedStatement updateStatement = null;


    Statement st;
    Customer customer;
    Connection connection;

    /**
     * Constructor
     * <p>
     * Initialize the streams and start the thread.
     */
    Conversation(Socket socket) {
        clientSocket = socket;

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("LOG: Streams opened");
        } catch (IOException e) {
            try {
                clientSocket.close();
            } catch (IOException e2) {
                System.err.println("Exception closing client socket: " + e2);
            }

            System.err.println("Exception getting socket streams: " + e);
            return;
        }

        try {
            System.out.println("LOG: Trying to create database connection");
            connection = DriverManager.getConnection(URL);
            System.out.println("LOG: Connected to database");
            //st=connection.createStatement();
            /*customer=new Customer();
            //String str="select * from customer";
            String sql3 = "SELECT * FROM customer";

            //Statement statement = connection.createStatement();
            ResultSet result = st.executeQuery(sql3);

            int count = 0;

            while (result.next()){
                String name = result.getString(1);
                String ssn = result.getString(2);
                String address = result.getString(3);
                String zipcode = result.getString(4);

                String output = "customer #%d: %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, name, ssn, address, zipcode));
            }*/


            /*PreparedStatement ps= connection.prepareStatement("INSERT INTO customer (name, ssn, address, zipcode) VALUES (?, ?, ?, ?)");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSsn());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getZipcode());
            ps.executeUpdate();
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            ResultSet rset=ps.executeQuery();


            /*String sql = "UPDATE customer SET name=?, address=? ,zipcode=? WHERE ssn=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "vaib");
            statement.setString(2, "monroe valley 123");
            statement.setString(3, "15896");
            statement.setString(4, "909-89-8765");

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }*/

            /*String sql2 = "DELETE FROM Users WHERE ssn=?";

            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, "909-89-8765");

            int rowsDeleted = statement1.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }*/
            // Create your Statements and PreparedStatements here


            //System.out.println("LOG: Connected to database");

        } catch (SQLException e) {
            System.err.println("Exception connecting to database manager: " + e);
            return;
        }

        // Start the run loop.
        run();
        System.out.println("LOG: Connection achieved, starting run loop");
        this.start();
    }

    /**
     * run()
     * <p>
     * Reads and processes input from the client until the client disconnects.
     */
    public void run() {
        System.out.println("LOG: Thread running");

        try {
            while (true) {


                // Read and process input from the client.
            }
        } catch (Exception e) {
            System.err.println("IOException: " + e);
            System.out.println("LOG: Client disconnected");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Exception closing client socket: " + e);
            }
        }
    }

    /*public ArrayList<Customer> customerList(){

        ArrayList<Customer> custList=new ArrayList<Customer>();
        try{
            Connection conn=DriverManager.getConnection(URL);
            String query="SELECT * FROM Customer";
            Statement stm;
            ResultSet rs;


            stm=conn.createStatement();
            rs=stm.executeQuery(query);
            Customer customer;
            while (rs.next()) {
                customer =new Customer(rs.getString("name"),rs.getString("ssn"),rs.getString("address"),rs.getString("zipcode"));
                custList.add(customer);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return custList;
    }*/


    private void handleGetAll() {
       /*ArrayList<Customer> cust=customerList();
       ShowingGUI s=new ShowingGUI();*/
        //DefaultTableModel model=s.
        //Object[] row=new Object)
        customer = new Customer();
        //String str="select * from customer";
        String sql3 = "SELECT * FROM customer";
        try {
            //Statement statement = connection.createStatement();
            ResultSet result = st.executeQuery(sql3);

            int count = 0;

            while (result.next()) {
                String name = result.getString(1);
                String ssn = result.getString(2);
                String address = result.getString(3);
                String zipcode = result.getString(4);

                String output = "customer #%d: %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, name, ssn, address, zipcode));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleAdd(MessageObject clientMsg) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customer (name, ssn, address, zipcode) VALUES (?, ?, ?, ?)");

            ps.setString(1, customer.getName());

            ps.setString(2, customer.getSsn());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getZipcode());
            ps.executeUpdate();
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            ResultSet rset = ps.executeQuery();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void handleDelete(MessageObject clientMsg) {
        try {
            String sql2 = "DELETE FROM Users WHERE ssn=?";

            PreparedStatement statement1 = connection.prepareStatement(sql2);
            statement1.setString(1, "909-89-8765");

            int rowsDeleted = statement1.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private void handleUpdate(MessageObject clientMsg) {
        try {
            String sql2 = "DELETE FROM Users WHERE ssn=?";

            PreparedStatement statement1 = connection.prepareStatement(sql2);
            statement1.setString(1, "909-89-8765");

            int rowsDeleted = statement1.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

    }
}
