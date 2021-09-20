package AdminPackage;


import com.util.UtilityFunctions;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Editprofile extends JFrame implements ActionListener {



    JPasswordField p1, p2;

    JLabel NameLabel, PhnoLabel, RoleLabel,  Password, EmailLabel;

    JTextField  Namet, Phno,Email;

    JComboBox Rolecb;
    JFrame f;
    JPanel panel;
    private JButton  deleteb,reset, update;

    String Username,  Pno, Rolval, Gval, pwd, email;
    String outputString,em,nm,pass,phno,rnm,passw;


    PreparedStatement p;
    Connection conn;

    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);



    public Editprofile() {
        f = new JFrame("Edit Profile");
        panel = new JPanel();
        panel.setBounds(40, 40, 600, 600);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        EmailLabel  = new JLabel("Email:");
        EmailLabel .setBounds(80, 70, 200, 30);
        Email = new JTextField();
        Email.setBounds(300, 70, 200, 30);

        NameLabel= new JLabel("Name:");
        NameLabel.setBounds(80, 110, 200, 30);
        Namet= new JTextField();
        Namet.setBounds(300, 110, 200, 30);


        RoleLabel = new JLabel("Role:");
        RoleLabel.setBounds(80, 150, 200, 30);
        String user[] = {"Admin", "Manager", "Developer"};
        Rolecb = new JComboBox(user);
        Rolecb.setBounds(300, 150, 200, 30);

        PhnoLabel = new JLabel("Phone No:");
        PhnoLabel.setBounds(80, 190, 200, 30);
        Phno = new JTextField();
        Phno.setBounds(300, 190, 200, 30);



        Password = new JLabel("Password:");
        Password.setBounds(80, 270, 200, 30);
        p1 = new JPasswordField();
        p1.setBounds(300, 270, 200, 30);

        ;
        update= new JButton("Update");
        update.setBounds(150, 400, 100, 30);

        reset = new JButton("Reset");
        reset.setBounds(300, 400, 100, 30);

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



        Email.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                showdetails();
            }
            @Override
            public void keyReleased(KeyEvent e) {  }
            @Override
            public void keyTyped(KeyEvent e) {}

        });
        panel.add(update);
        update.addActionListener(this);

        panel.add(reset);
        reset.addActionListener(this);


        f.add(panel);
        f.setSize(700, 700);
        f.setLayout(null);
        f.setVisible(true);
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent event) {

        if(event.getSource()==update)
        {
            updatemethod();
        }


        else if(event.getSource()==reset)
        {
            Rolecb.setSelectedIndex(0);

            Email.setText("");
            Namet.setText("");

            Phno.setText("");
            p1.setText("");
            p2.setText("");
        }



    }




    public void getValuefromGui() {

        Username = Namet.getText();
        Pno = Phno.getText();

        email = Email.getText();
        System.out.println(email);


        Rolval = Rolecb.getSelectedItem().toString();

        pwd = String.valueOf(p1.getPassword());


    }
    public void setvaluestogui()
    {
        Namet.setText(nm);;
        Phno.setText(phno);

        Email.setText(em);
        Rolecb.setSelectedItem(rnm);

        passw=String.valueOf(pass);
        outputString=UtilityFunctions.encryptDecrypt(passw);
        p1.setText(outputString);

    }

    public void showdetails() {
        getValuefromGui();

        try {
            Connection connection= UtilityFunctions.createConnection();
            p = connection.prepareStatement("select email from users where email='" + email+ "'    ");
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                em = rs.getString("email");

                System.out.println(em);

            }
            if(email.equals(em))
            {

                p = connection.prepareStatement("select * from users where email='" + email + "'    ");
                ResultSet rs1 = p.executeQuery();

                while (rs1.next()) {
                    nm=rs1.getString("username");
                    em = rs1.getString("email");
                    rnm = rs1.getString("role");
                    phno = rs1.getString("phno");
                    pass = rs1.getString("password");


                    setvaluestogui();



                }

            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatemethod()
    {
        getValuefromGui();
        System.out.println("pwd is:"+pwd);
        if (PATTERN.matcher(pwd).matches()) {
            System.out.print("The Password " + pwd + " is valid" + "\n");

            // if(pwd.equals(Cpwd)) {

            if (email.matches(reg)) {
                System.out.println("Given email-id is valid");

                if (Pno.matches("\\d{10}")) {
                    System.out.println("Valid Mobile NO");


                    try {
                        Connection connection= UtilityFunctions.createConnection();
                        outputString=UtilityFunctions.encryptDecrypt(pwd);
                        //encryptdecryptpwd(pwd);
                        String up = "update users set username='" + Namet.getText() + "',email='" + Email.getText() + "' ,role='" + Rolecb.getSelectedItem().toString() + "',phno='" + Phno.getText() + "',password='" + outputString + "' where email='" +  Email.getText() + "' ";
                        p = connection.prepareStatement(up);
                        p.execute();
                        JOptionPane.showMessageDialog(f,
                                "User Details Updated Successfully");
                        System.out.println("Record updated Successfully ");
                        f.setVisible(false);


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

           /* } else {
                System.out.println("password not matched");
                JOptionPane.showMessageDialog(f, "Password not match to Confirm Password",
                        "Confirm Password", JOptionPane.ERROR_MESSAGE);
            }*/


        } else {
            JOptionPane.showMessageDialog(f, "Please enter correct Password",
                    "Check Password", JOptionPane.ERROR_MESSAGE);
        }

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Editprofile e=new Editprofile();


    }



}
