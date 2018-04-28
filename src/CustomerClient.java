import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class CustomerClient extends JFrame implements ActionListener {

    // GUI components
    private JButton connectButton = new JButton("Connect");
    private JButton getAllButton = new JButton("Get All");
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");
    private JButton updateButton = new JButton("Update Address");

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CustomerClient client = new CustomerClient();
            client.createAndShowGUI();
        });
    }

    private CustomerClient() {
        super("Customer Database");
    }

    private void createAndShowGUI() {
        // Set up GUI
    	ShowingGUI show=new ShowingGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Connect")) {
            connect();
        } else if (e.getActionCommand().equals("Disconnect")) {
            disconnect();
        } else if (e.getSource() == getAllButton) {
            handleGetAll();
        } else if (e.getSource() == addButton) {
            handleAdd();
        } else if (e.getSource() == updateButton) {
            handleUpdate();
        } else if (e.getSource() == deleteButton) {
            handleDelete();
        }
    }

    private void connect() {
        try {
            // Replace 97xx with your port number
            socket = new Socket("turing.cs.niu.edu", 9735);

            System.out.println("LOG: Socket opened");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("LOG: Streams opened");

            connectButton.setText("Disconnect");
            
            // Enable buttons
            getAllButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);

        } catch (UnknownHostException e) {
            System.err.println("Exception resolving host name: " + e);
        } catch (IOException e) {
            System.err.println("Exception establishing socket connection: " + e);
        }
    }

    private void disconnect() {
        connectButton.setText("Connect");
        
        // Disable buttons
        getAllButton.setEnabled(false);
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Exception closing socket: " + e);
        }
    }

    private void handleGetAll() {
    	
    	
    }

    private void handleAdd() {
    }

    private void handleDelete() {
    }

    private void handleUpdate() {
    }
}