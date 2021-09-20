package CreateSurvey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddQue extends JFrame
{
    public JLabel EnterQuestion;
    public JTextField EnterQuestionTextField;
    public JButton SaveButton;
    public JFrame frame;
    public JPanel panel;
    public String str1;
    public java.sql.Connection c;
    public Statement s;
    public PreparedStatement p;
    private int categoryId;
    public int question_id;
    public void setSelectedCategoryId(int categoryIdRec)
    {
        categoryId=categoryIdRec;
    }
    public AddQue()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(30,30,720,400);
        frame = new JFrame();
        frame.setTitle("Add Question");
        frame.setBounds(100, 100, 800, 500);
        frame.setLocation(0,0);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(panel);
        panel.setLayout(null);

        EnterQuestion = new JLabel("Enter Question");
        EnterQuestionTextField = new JTextField();
        SaveButton = new JButton("Save");

        EnterQuestion.setBounds(70, 50, 100, 30);
        EnterQuestionTextField.setBounds(200, 50, 200, 30);
        SaveButton.setBounds(150, 100, 100, 30);

        panel.add(EnterQuestion);
        panel.add(EnterQuestionTextField);
        panel.add(SaveButton);

        SaveButton.addActionListener(new ActionListener(  )
        {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_mgmt", "Aress", "Aress@aress123");
                    System.out.println("Connected to Database");
                    s = c.createStatement();
                    String Key[]= {"question_id"};
                    String str = " insert into questions(question) values('" + EnterQuestionTextField.getText() + "')";

                    PreparedStatement p = c.prepareStatement(str,Key);
                    int b= p.executeUpdate();

                    ResultSet rs=p.getGeneratedKeys();
                    if(rs.next())
                        question_id=rs.getInt(1);


                    boolean x = false;
                    if (b>0) {
                        System.out.println("Record Successfully Inserted");

                        String str1 = " insert into category_question(category_id,question_id) values('"+categoryId+"','"+question_id+"')";
                        PreparedStatement p1 = c.prepareStatement(str1);
                        p1.execute();
                        System.out.println("Record Successfully Inserted | many to many table");
                        c.close();
                    }
                    else
                        System.out.println("Insert Failed");
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, "Successfull Insertion");
                //new MainClass();
                frame.setVisible(false);


            }        });
        panel.setVisible(true);
        frame.setVisible(true);

    }

}

