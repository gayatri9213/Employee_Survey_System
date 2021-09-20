package ManagerPackage;

import LoginPage.LoginPage;
import com.util.UtilityFunctions;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.regex.Pattern;

public class MainManagerGUI extends JFrame
{
    JFrame frame;
    JPanel buttonPanel, topPanel, mainPanel, editPanel, reportPanel;
    JButton View_Report, Edit_Profile;
    JLabel title;
    JPasswordField tfPassword;
    JLabel id,name,email,phone,password,address,gender,role_name;
    JTextArea tadd;
    JTextField tfId,tfName,tfEmail,tfPhone;
    JRadioButton male, female;
    JComboBox role_combo;
    JFrame f;
    private JButton reset, update;
    String Username, Addr, Pno, Rolval, Gval, pwd, Email, Cpwd,gender1,id1,id2;
    String outputString,inputString,em,nm,pass,phno,rnm,gen,addr,passw;
    PreparedStatement p;


    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);
    /***********************************************************************/

    public MainManagerGUI()    {
        frame = new JFrame("Manager Module");
        buttonPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        editPanel = new JPanel();
        reportPanel = new JPanel();
        JScrollBar scroll = new JScrollBar(JScrollBar.HORIZONTAL);
        View_Report = new JButton("View Report");
        Edit_Profile = new JButton("Edit Profile");
        // Panels Code
        frame.setLayout(null);
        frame.setExtendedState(MAXIMIZED_BOTH);
        scroll.setBounds(1421, 120, 20, 629);
        topPanel.setBounds(250, 0, 1450, 120);
        topPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(0, 0, 250, 710);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(null);
        mainPanel.setBounds(251, 120, 1451, 629);
        mainPanel.setBackground(Color.WHITE);
        // Buttons Code
        View_Report.setBounds(30, 150, 180, 40);
        View_Report.setBackground(Color.lightGray);
        Edit_Profile.setBounds(30, 230, 180, 40);
        Edit_Profile.setBackground(Color.lightGray);
        //Label Code
        title = new JLabel("MANAGER ");
        title.setBounds(50, 100, 100, 30);
        title.setFont(new Font("Verdana", Font.PLAIN, 32));
        title.setForeground(new Color(255, 255, 255));
        // Add Panels and Buttons on the Frame
        buttonPanel.add(View_Report);
        buttonPanel.add(Edit_Profile);
        topPanel.add(title);
        frame.add(mainPanel);
        frame.add(editPanel);
        frame.add(reportPanel);
        frame.add(topPanel);
        frame.add(buttonPanel);
        frame.add(scroll);
        frame.setSize(400, 400);
        frame.pack();
        frame.setVisible(true);
        View_Report.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Report();
            }
        });
        Edit_Profile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                editPanel.setVisible(true);
                Edit();
            }
        });
    }
    public void Edit()    {
        editPanel.setBounds(251, 120, 1451, 629);
        editPanel.setBackground(Color.lightGray);
        editPanel.setLayout(null);
        editPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel EditTitle = new JLabel("Edit Profile");
        EditTitle.setBounds(400, 50, 200, 50);
        EditTitle.setVerticalAlignment(SwingConstants.TOP);
        EditTitle.setFont(new Font("Verdana", Font.PLAIN, 30));
        editPanel.add(EditTitle);

        id = new JLabel("Id");
        id.setBounds(100, 150, 200, 30);
        id.setFont(new Font("Verdana", Font.PLAIN, 20));
        editPanel.add(id);

        tfId = new JTextField(10);
        tfId.setBounds(210, 150, 200, 30);
        tfId.setFont(new Font("Verdana", Font.PLAIN, 15));
        editPanel.add(tfId);
        String Id=tfId.getText();

        name = new JLabel("Name");
        name.setBounds(100, 200, 200, 30);
        name.setFont(new Font("Verdana", Font.PLAIN, 20));
        editPanel.add(name);

        tfName = new JTextField(10);
        tfName.setBounds(210, 200, 200, 30);
        tfName.setFont(new Font("Verdana", Font.PLAIN, 15));
        editPanel.add(tfName);

        email = new JLabel("Email");
        email.setBounds(550, 150, 200, 30);
        email.setFont(new Font("Verdana", Font.PLAIN, 20));
        editPanel.add(email);

        tfEmail = new JTextField(10);
        tfEmail.setBounds(660, 150, 200, 30);
        tfEmail.setFont(new Font("Verdana", Font.PLAIN, 15));
        editPanel.add(tfEmail);

        password = new JLabel("Password");
        password.setBounds(550, 200, 200, 30);
        password.setFont(new Font("Verdana", Font.PLAIN, 20));
        editPanel.add(password);

        tfPassword = new JPasswordField(10);
        tfPassword.setBounds(660, 200, 200, 30);
        tfPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        editPanel.add(tfPassword);

        phone = new JLabel("Phone No.");
        phone.setBounds(100, 250, 200, 30);
        phone.setFont(new Font("Verdana", Font.PLAIN, 20));
        editPanel.add(phone);

        tfPhone = new JTextField(10);
        tfPhone.setBounds(210, 250, 200, 30);
        tfPhone.setFont(new Font("Verdana", Font.PLAIN, 15));
        editPanel.add(tfPhone);

        role_name = new JLabel("Role");
        role_name.setBounds(550, 250, 200, 30);
        role_name.setFont(new Font("Verdana", Font.PLAIN, 20));
        editPanel.add(role_name);

        String role[] = {"Admin", "Manager", "developer"};
        role_combo = new JComboBox(role);
        role_combo.setFont(new Font("Arial", Font.PLAIN, 15));
        role_combo.setBounds(660, 250, 200, 30);
        editPanel.add(role_combo);

        gender = new JLabel("Gender");
        gender.setBounds(100, 300, 200, 30);
        gender.setFont(new Font("Verdana", Font.PLAIN, 20));
        // editPanel.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Verdana", Font.PLAIN, 20));
        male.setSelected(true);
        male.setBackground(Color.lightGray);
        male.setBounds(210, 300, 100, 30);
        //editPanel.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Verdana", Font.PLAIN, 20));
        female.setSelected(false);
        female.setBackground(Color.lightGray);
        female.setBounds(310, 300, 100, 30);
        // editPanel.add(female);

        address = new JLabel("Address");
        address.setBounds(550, 300, 200, 30);
        address.setFont(new Font("Verdana", Font.PLAIN, 20));
        //  editPanel.add(address);

        tadd = new JTextArea();
        tadd.setFont(new Font("Verdana", Font.PLAIN, 15));
        tadd.setBounds(660, 300, 200, 30);
        tadd.setLineWrap(true);
        //editPanel.add(tadd);

        update= new JButton("Update");
        update.setBounds(400, 450, 100, 30);
        update.setFont(new Font("Verdana", Font.PLAIN, 14));
        reset = new JButton("Reset");
        reset.setBounds(550, 450, 100, 30);
        reset.setFont(new Font("Verdana", Font.PLAIN, 14));

        tfId.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                showdetails();
            }
            @Override
            public void keyReleased(KeyEvent e) { showdetails(); }
            @Override
            public void keyTyped(KeyEvent e) {}

        });
        editPanel.add(update);
        update.addActionListener(new ActionListener()        {
            public void actionPerformed(ActionEvent e)
            {
                updatemethod();
            }
        });
        editPanel.add(reset);
        reset.addActionListener(new ActionListener()        {
            public void actionPerformed(ActionEvent e)
            {
                tfId.setText("");
                role_combo.setSelectedIndex(0);
                male.setSelected(false);
                female.setSelected(false);
                tfEmail.setText("");
                tfName.setText("");
                tadd.setText("");
                tfPhone.setText("");
                tfPassword.setText("");
            }
        });
    }

    public void Report()    {
        reportPanel.setBounds(251, 120, 1451, 629);
        reportPanel.setBackground(Color.WHITE);
        reportPanel.setLayout(null);

        JLabel EditTitle = new JLabel("Report");
        EditTitle.setBounds(500, 10, 100, 20);
        EditTitle.setVerticalAlignment(SwingConstants.TOP);
        reportPanel.add(EditTitle);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_mgmt", "Aress", "Aress@aress123");
            //reportPanel.setSize(1350,800);
            reportPanel.setBounds(251, 120, 1000, 800);

            HashMap para=new HashMap();
            reportPanel.removeAll();
            reportPanel.repaint();;
            reportPanel.revalidate();

            FileInputStream in=new FileInputStream(new File("C:\\Users\\user\\eclipse-workspace\\hello\\ManagerReport.jrxml"));
            JasperDesign jd= JRXmlLoader.load(in);

            String sql;
            sql = " select survey_responses.emp_id,survey_responses.manager_id,users.username,questions.question,categories.category_name,survey_responses.rating,survey_responses.comment from survey_responses,users,categories,questions where users.user_id=survey_responses.manager_id and categories.category_id=survey_responses.cat_id and questions.question_id=survey_responses.question_id and users.username='harshad'";
            JRDesignQuery newQuery=new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint j= JasperFillManager.fillReport(jr, para, connection);


            JRViewer v = new JRViewer(j);
            reportPanel.setLayout(new BorderLayout());
            reportPanel.add(v);

            //JasperViewer.viewReport(j, false);
            FileOutputStream os=new FileOutputStream(new File("D:\\reports\\Manager report.pdf"));
            JasperExportManager.exportReportToPdfStream(j, os);


        } catch (Exception ae) {

            ae.printStackTrace();

        }

    }


    public void encryptdecryptpwd(String str) {
        // Define XOR key
        // Any character value will work
        char xorKey = 'P';

        // Define String to store encrypted/decrypted String
        outputString = "";
        //inputString = pwd;
        inputString=str;
        //System.out.println("instr is:"+inputString);
        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every character in string
        for (int i = 0; i < len; i++) {
            outputString = outputString + Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }

        // System.out.println(outputString);

    }
    public void getValuefromGui() {
        id1=tfId.getText();
        Username = tfName.getText();
        Pno = tfPhone.getText();
        Addr = tadd.getText();
        Email = tfEmail.getText();
        if (male.isSelected())
            Gval = "Male";
        else if (female.isSelected())
            Gval = "Female";
        Rolval = role_combo.getSelectedItem().toString();

        pwd = String.valueOf(tfPassword.getPassword());
        // Cpwd = String.valueOf(p2.getPassword());

    }
    public void setvaluestogui()    {
        tfId.setText(id2);
        tfName.setText(nm);
        tfPhone.setText(phno);
        tadd.setText(addr);
        tfEmail.setText(em);
        gender.setText(gen);
        role_combo.setSelectedItem(rnm);
        System.out.println(rnm);
        passw=String.valueOf(pass);
        encryptdecryptpwd(passw);
        tfPassword.setText(outputString);

    }
    public void showdetails() {
        getValuefromGui();

        try {
            Connection connection= UtilityFunctions.createConnection();
           p = connection.prepareStatement("select user_id from users where user_id='" + id1+ "'  and role='manager'  ");
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                id2 = rs.getString("user_id");

                //System.out.println(em);

            }


            if(id1.equals(id2))
            {
                connection= UtilityFunctions.createConnection();
                p = connection.prepareStatement("select * from users where user_id='" + id1 + "' and role='manager'   ");
                ResultSet rs1 = p.executeQuery();

                while (rs1.next()) {
                    id2=rs1.getString("user_id");
                    nm=rs1.getString("username");
                    em = rs1.getString("email");
                    pass = rs1.getString("password");
                    phno = rs1.getString("phno");
                    rnm = rs1.getString("role");
                    System.out.println(rnm);

                    setvaluestogui();

                    /* System.out.println(nm + "\t\t" + em
                            + "\t\t"+"   " + pass + "\t\t " + phno+"\t\t"+"  "+rnm+"\t\t"+"  "+gen+"\t\t"+"  "+addr);*/

                }

            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatemethod()    {
        getValuefromGui();
        System.out.println("pwd is:"+pwd);
        if (PATTERN.matcher(pwd).matches()) {
            System.out.print("The Password " + pwd + " is valid" + "\n");

            if (Email.matches(reg)) {
                System.out.println("Given email-id is valid");

                if (Pno.matches("\\d{10}")) {
                    System.out.println("Valid Mobile NO");


                    try {
                        Connection connection= UtilityFunctions.createConnection();
                        encryptdecryptpwd(pwd);
                        String up = "update users set username='" + tfName.getText() + "',email='" + tfEmail.getText() + "' ,password='" + outputString + "',phno='" + tfPhone.getText()+ "' where user_id='" +  tfId.getText() + "' ";
                        p = connection.prepareStatement(up);
                        p.execute();
                        JOptionPane.showMessageDialog(f,
                                "User Details Updated Successfully");
                        System.out.println("Record updated Successfully ");
                        editPanel.setVisible(false);


                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "Please Enter Correct Mobile no",
                            "Check Password", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(f, "Please Enter Correct Email ID",
                        "Check Password", JOptionPane.ERROR_MESSAGE);
            }



        } else {
            JOptionPane.showMessageDialog(f, "Please enter correct Password",
                    "Check Password", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        new MainManagerGUI();
    }



}
