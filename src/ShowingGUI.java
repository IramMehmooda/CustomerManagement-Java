//import com.sun.jndi.cosnaming.IiopUrl;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ShowingGUI extends JPanel{
    static JFrame frame;
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
   // frame = new JFrame("Bouncing Ball");
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(450, 400);
//        frame.setContentPane(new ShowingGUI());
//        //frame.pack();
//        frame.setVisible(true);
        System.out.println("Hello");
        
    }
    ShowingGUI(){
        super(new BorderLayout());
        JPanel c = new JPanel();
        JTable customerTable = new JTable();
      JButton connectButton = new JButton("Connect");
      JButton getAllButton = new JButton("Get All");
      JButton addButton = new JButton("Add");
      JButton deleteButton = new JButton("Delete");
     JButton updateButton = new JButton("Update Address");
       // c.setSize(300,);
        JPanel d = new JPanel(new GridLayout(2,4,3,3));
        JPanel e = new JPanel(new FlowLayout());
        JLabel lb = new JLabel("Client Started");
        lb.setMaximumSize(new Dimension(400,10));
        JTextArea t1 =new JTextArea();
       // JTextArea t1 =new JTextArea();
        JTextArea t2 =new JTextArea();
        t1.setMinimumSize(new Dimension(40,10));
        JTextArea t3 =new JTextArea();
        JTextArea t4 =new JTextArea();
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
        c.add(lb,BorderLayout.PAGE_END);

       // c.setBackground(Color.GREEN);
        d.setBackground(Color.GRAY);
        add(c, BorderLayout.CENTER);
        add(new JScrollPane(a),BorderLayout.PAGE_END);
    }


}

