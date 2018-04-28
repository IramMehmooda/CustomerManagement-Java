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

    /**
     * Constructor
     *
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
            Connection connection = DriverManager.getConnection(URL);
            Statement st=connection.createStatement();
            String str="select * from customer";
            ResultSet rset=st.executeQuery(str);
            // Create your Statements and PreparedStatements here
            

            System.out.println("LOG: Connected to database");

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
     *
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
    
    public ArrayList<Customer> customerList(){
    	
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
    	}
    
    
    private void handleGetAll() {
    	ArrayList<Customer> cust=customerList();
    	ShowingGUI s=new ShowingGUI();
    	//DefaultTableModel model=s.
    	//Object[] row=new Object)
    }

    /*private void handleAdd(MessageObject clientMsg) {
    }
 
    private void handleDelete(MessageObject clientMsg) {
    }

    private void handleUpdate(MessageObject clientMsg) {
    }*/
}