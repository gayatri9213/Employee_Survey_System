package CreateSurvey;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;

import CreateSurvey.MainClass;
import com.util.UtilityFunctions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/*
* @Author: Moiza Attar
* */
public class AddCat extends JFrame {

    JLabel EnterCategory;
    JTextField EnterCategoryTextField;
    JButton SaveButton;
    JFrame frame;
    public java.sql.Connection c;
    public Statement s;
    public PreparedStatement p;
    //long tempCatLong =0L;

    public AddCat() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(30,30,720,400);
        frame = new JFrame();
        setTitle("Add Category");
        frame.setBounds(100, 100, 800, 500);
        frame.setLocation(0,0);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(panel);
        panel.setLayout(null);

        EnterCategory = new JLabel("Enter Category");
        EnterCategoryTextField = new JTextField();
        SaveButton = new JButton("Save");

        EnterCategory.setBounds(70, 50, 100, 30);
        EnterCategoryTextField.setBounds(200, 50, 200, 30);
        SaveButton.setBounds(150, 100, 100, 30);

        panel.add(EnterCategory);
        panel.add(EnterCategoryTextField);
        panel.add(SaveButton);

        SaveButton.addActionListener(new ActionListener(  )
        {
            ResultSet resultset;
            public void actionPerformed(ActionEvent e) {
                try {

                    Connection connection= UtilityFunctions.createConnection();
                    String str = " insert into categories(category_name) values('" + EnterCategoryTextField.getText() + "')";
                    PreparedStatement p = connection.prepareStatement(str);

                    boolean x = false;
                    if (x == p.execute()) {
                       System.out.println("Record Successfully Inserted");
                    }
                    else
                       System.out.println("Insert Failed");
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, "Successfull Insertion");
                frame.setVisible(false);

            }        });
        panel.setVisible(true);
        frame.setVisible(true);
    }


}




