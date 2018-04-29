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
    private JFrame frame;
    JTextArea t1 =new JTextArea();
    JTextArea t2 =new JTextArea();
    JTextArea t3 =new JTextArea();
    JTextArea t4 =new JTextArea();
    
    JPanel c = new JPanel();
    
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
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Customer ");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        //frame.setContentPane(new CustomerClient());
        //frame.pack();
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        

        // c.setSize(300,);
        JPanel d = new JPanel(new GridLayout(2,4,3,3));
        JPanel e = new JPanel(new FlowLayout());
        //JLabel lb = new JLabel("Client Started");
        //lb.setMaximumSize(new Dimension(400,10));
       
        t1.setMinimumSize(new Dimension(40,10));
        
        JLabel l1 = new JLabel("Name             ");
        JLabel l2 = new JLabel("Address          ");
        JLabel l3 = new JLabel("SSN              ");
        JLabel l4 = new JLabel("zipcode          ");

        d.add(l1);
        d.add(t1);
        d.add(l2);
        d.add(t2);
        d.add(l3);
        d.add(t3);
        d.add(l4);
        d.add(t4);
        e.add(connectButton);
        e.add(getAllButton);
        e.add(updateButton);
        e.add(deleteButton);
        e.add(addButton);

        JScrollBar scrollBar = new JScrollBar();
        JTextArea a = new JTextArea(10,60);
        c.add(d,BorderLayout.PAGE_START);
        c.add(e,BorderLayout.CENTER);
        

        // c.setBackground(Color.GREEN);
        d.setBackground(Color.GRAY);
        frame.add(c, BorderLayout.CENTER);
        frame.add(new JScrollPane(a),BorderLayout.PAGE_END);
        connectButton.addActionListener(this);
        getAllButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        addButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Connect")) {
            connect();
            System.out.println("Calling connect");
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
        	System.out.println("I am in connect");
            socket = new Socket("turing.cs.niu.edu", 9735);

            System.out.println("LOG: Socket opened");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("LOG: Streams opened");

            connectButton.setText("Disconnect");

            deleteButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            connectButton.setEnabled(true);
            getAllButton.setEnabled(true);

        } catch (UnknownHostException e) {
            System.err.println("Exception resolving host name: " + e);
        } catch (IOException e) {
            System.err.println("Exception establishing socket connection: " + e);
        }
    }

    private void disconnect() {
        connectButton.setText("Connect");
        updateButton.setEnabled(false);
        getAllButton.setEnabled(false);
        addButton.setEnabled(false);
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
    	
    	String regex1="^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";
    	if(t1.getText().trim().length()==0 || t2.getText().trim().length()==0 || t3.getText().trim().length()==0 || t4.getText().trim().length()==0) {
    		JLabel lb = new JLabel("All fields are required");
    		c.add(lb,BorderLayout.PAGE_END);
    		lb.setMaximumSize(new Dimension(400,10));
    	}
    	if(t1.getText().trim().length()>20){
    		JLabel lb = new JLabel("Length of 'name' should not be greater than 20");
    		c.add(lb,BorderLayout.PAGE_END);
    		lb.setMaximumSize(new Dimension(400,10));
    	}
    	if(t2.getText().trim().length()>40){
    		JLabel lb = new JLabel("Address should not be more than 40 characters");
    		c.add(lb,BorderLayout.PAGE_END);
    		lb.setMaximumSize(new Dimension(400,10));
    	}
    	if(!t3.getText().matches(regex1)) {
    		JLabel lb = new JLabel("ssn should be in the format xxx-xx-xxxx or 11 digits");
    		c.add(lb,BorderLayout.PAGE_END);
    		lb.setMaximumSize(new Dimension(400,10));
    	}
    	if(!t4.getText().matches("\\d{5}")) {
    		JLabel lb = new JLabel("zipcode should 5 digits");
    		c.add(lb,BorderLayout.PAGE_END);
    		lb.setMaximumSize(new Dimension(400,10));
    		
    	}
    	else {
    		
    	}
    }

    private void handleDelete() {
    }

    private void handleUpdate() {
    }
}
