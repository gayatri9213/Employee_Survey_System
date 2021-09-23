package AdminPackage;


import com.util.UtilityFunctions;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

/*
* @Author : Prachi Khalkar
* */
public class Createuser extends JFrame implements ActionListener {


    String userstatus;

    JPasswordField p1, p2;
    JPanel panel1;
    JLabel title, IDLabel, NameLabel, PhnoLabel, RoleLabel, gender, AddressLabel, Password, Confirmpass, EmailLabel;
    JTextArea Add;
    JTextField ID, Namet, Phno, Role, Email;
    JRadioButton male, female;
    JComboBox Rolecb;
    JFrame f;
    JPanel panel;
    private JButton sub, reset, cancel, btn;

    String Username, Addr, Pno, Rolval, Gval, pwd, email, Cpwd;
    String outputString, inputString;


    PreparedStatement p;
    Connection conn;


    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);
    private String Null;
    private String NUll;




    public Createuser() {
        f = new JFrame("Create New User");
        panel = new JPanel();
        panel.setBounds(40, 40, 600, 600);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        title = new JLabel("Enter User Details:");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setBounds(100, 30, 400, 30);

        NameLabel = new JLabel("Name:");
        NameLabel.setBounds(80, 70, 200, 30);
        Namet = new JTextField();
        Namet.setBounds(300, 70, 200, 30);


        RoleLabel = new JLabel("Role:");
        RoleLabel.setBounds(80, 110, 200, 30);
        String user[] = {"Admin", "Manager", "Developer"};
        Rolecb = new JComboBox(user);
        Rolecb.setBounds(300, 110, 200, 30);

        PhnoLabel = new JLabel("Phone No:");
        PhnoLabel.setBounds(80, 150, 200, 30);
        Phno = new JTextField();
        Phno.setBounds(300, 150, 200, 30);


        EmailLabel = new JLabel("Email:");
        EmailLabel.setBounds(80, 190, 200, 30);
        Email = new JTextField();
        Email.setBounds(300, 190, 200, 30);

        Password = new JLabel("Password:");
        Password.setBounds(80, 230, 200, 30);
        p1 = new JPasswordField();
        p1.setBounds(300, 230, 200, 30);

        Confirmpass = new JLabel("Confirm Password:");
        Confirmpass.setBounds(80, 300, 200, 30);
        p2 = new JPasswordField();
        p2.setBounds(300, 300, 200, 30);


        sub = new JButton("Submit");
        sub.setBounds(100, 450, 100, 30);
        reset = new JButton("Reset");
        reset.setBounds(250, 450, 100, 30);
        // cancel = new JButton("Cancel");
        // cancel.setBounds(400, 600, 100, 30);

        panel.add(title);
        panel.add(NameLabel);
        panel.add(Namet);
        panel.add(RoleLabel);
        panel.add(Rolecb);
        panel.add(PhnoLabel);
        panel.add(Phno);

        panel.add(EmailLabel);
        panel.add(Email);
        panel.add(Password);
        panel.add(p1);
        panel.add(Confirmpass);
        panel.add(p2);
        panel.add(sub);
        sub.addActionListener(this);
        panel.add(reset);
        reset.addActionListener(this);

        f.add(panel);
        f.setSize(700, 700);
        f.setLayout(null);
        f.setVisible(true);
        //   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == sub) {

            insert();


        } else if (event.getSource() == reset) {
            Rolecb.setSelectedIndex(0);

            Email.setText("");
            Namet.setText("");

            Phno.setText("");
            p1.setText("");
            p2.setText("");
        }

        else {

            JOptionPane.showMessageDialog(f, "Please enter the correct details",
                    "Check Details", JOptionPane.ERROR_MESSAGE);
        }


    }


    public void insert() {

        getValuefromGui();


        if (PATTERN.matcher(pwd).matches()) {
            System.out.print("The Password " + pwd + " is valid" + "\n");

            if (pwd.equals(Cpwd)) {

                if (email.matches(reg)) {
                    System.out.println("Given email-id is valid");

                    if (Pno.matches("\\d{10}")) {
                        System.out.println("Valid Mobile NO");


                        try {


                            Connection connection= UtilityFunctions.createConnection();
                            outputString=UtilityFunctions.encryptDecrypt(pwd);

                            String query = "insert into users (username,email,role,phno,password)  values('" + Username + "','" + email + "','" + Rolval + "','" +

                                    Pno + "','" + outputString + "')";

                            p = connection.prepareStatement(query);
                            boolean x = false;
                            if (x == p.execute()) {
                                System.out.println("Record Successfully Inserted");
                                JOptionPane.showMessageDialog(f,
                                        "New User Created Successfully");
                                f.setVisible(false);
                            } else {
                                System.out.println("Insert Failed");
                            }



                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please Enter Correct Mobile Number",
                                "Check Mobile Number", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(f, "Please Enter Correct Email ID",
                            "Check Email ID", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                System.out.println("password not matched");
                JOptionPane.showMessageDialog(f, "Password not match to Confirm Password",
                        "Confirm Password", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            JOptionPane.showMessageDialog(f, "Please enter correct Password",
                    "Check Password", JOptionPane.ERROR_MESSAGE);
        }


    }




    public void getValuefromGui() {

        Username = Namet.getText();
        Pno = Phno.getText();

        email = Email.getText();



        Rolval = Rolecb.getSelectedItem().toString();


        pwd = String.valueOf(p1.getPassword());
        Cpwd = String.valueOf(p2.getPassword());


    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Createuser e=new Createuser();


    }

}