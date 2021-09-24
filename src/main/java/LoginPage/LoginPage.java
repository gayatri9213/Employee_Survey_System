package LoginPage;

import AdminPackage.MainAdminGUI;
import ManagerPackage.MainManagerGUI;
import UserPackage.MainUserGUI;
import com.buttons.AllButtonsLoginPage;
import com.framesAndPanels.AllFrameLoginPage;
import com.framesAndPanels.AllFramesAndPanels;
import com.labels.AllLabelsLoginPage;
import com.textFields.AllTextFieldsLoginPage;
import com.util.InitiateComponents;
import com.util.UtilityFunctions;

import javax.swing.*;
import java.sql.*;

import static com.util.UtilityFunctions.encryptDecrypt;
/*
@Author: Somanshu Bendale
* */
public class

LoginPage implements InitiateComponents
{

private JFrame loginFrame;

private JLabel userNameLabel,passwordLabel;
private JTextField userNameField;
private JPasswordField passwordField;
private JButton loginButton,resetButton,exitButton;
private PreparedStatement preparedStatement;
private ResultSet roleNameFetching;
public JFrame queFrame;

public static int LOGIN_USERID=0;



public LoginPage() {
    initComponents();
}

@Override
public void initComponents(){

    AllButtonsLoginPage buttonsCallingObj = new AllButtonsLoginPage();
    AllLabelsLoginPage labelsCallingObj=new AllLabelsLoginPage();
    AllTextFieldsLoginPage textFieldsCallingObj = new AllTextFieldsLoginPage();
    AllFramesAndPanels userFramesAndPanelsObj =new AllFramesAndPanels();

    loginFrame=new AllFrameLoginPage().putLoginPageFrame();
    queFrame=userFramesAndPanelsObj.putFrame();

    // Calling created label methods from AllLabelsLoginPage.java

    userNameLabel=labelsCallingObj.putUsernameLabel();
    loginFrame.add(userNameLabel);

    passwordLabel=labelsCallingObj.putPasswordLabel();
    loginFrame.add(passwordLabel);

    //Calling created TextFields methods from AllTextFieldsLoginPage.java

    userNameField=textFieldsCallingObj.putUsernameTextField();
    loginFrame.add(userNameField);

    passwordField=textFieldsCallingObj.putPasswordField();
    loginFrame.add(passwordField);

    // Calling all created button methods from AllButtonsLoginPage.java

    loginButton= buttonsCallingObj.putLoginButton();
    loginFrame.add(loginButton);
    loginButton.addActionListener(e -> {

        //calling login button actionListener method
        loginButtonActionListener();
    });


    resetButton= buttonsCallingObj.putResetButton();
    loginFrame.add(resetButton);
    resetButton.addActionListener(e -> {

        //calling reset button actionListener method
        resetButtonActionListener();
    });


    exitButton= buttonsCallingObj.putExitButton();
    loginFrame.add(exitButton);

    exitButton.addActionListener(e -> {
        //calling exit button actionListener method
        exitButtonActionListener();

    });


}

    private void exitButtonActionListener()
    {
        //Exiting button showing confirmDialoogue
        int a=JOptionPane.showConfirmDialog(loginFrame,"Are you sure ?","Exit" +
                "",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(a==JOptionPane.YES_OPTION)
        {
            //using system exit for closing the frame
            System.exit(0);
        }
    }

    private void resetButtonActionListener()
    {
        //clearing text which is displaying on frame
        userNameField.setText("");
        passwordField.setText("");
    }

    private void loginButtonActionListener()
    {
        try{
            //getting connection from UtilityFunctions
            Connection connection= UtilityFunctions.createConnection();

            //getting data which user entered
            String username = userNameField.getText();
            String password= UtilityFunctions.encryptDecrypt( new String(passwordField.getPassword()));


            preparedStatement = connection.prepareStatement("select role,user_id from users where username= ? and password= ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //ResultSet idFetching ;
            roleNameFetching = preparedStatement.executeQuery();

            if(roleNameFetching.next())
            {
                    //fetching role and user_id of the user which is logining in
                    String role_name= roleNameFetching.getString("role");
                    System.out.println(role_name);
                    //LOGIN_USERID required for filling survey purpose
                    LOGIN_USERID=roleNameFetching.getInt("user_id");

                    // performing role based authentication
                    if(role_name.equalsIgnoreCase("admin"))
                    {
                        new MainAdminGUI();
                        loginFrame.setVisible(false);
                    }
                    else if(role_name.equalsIgnoreCase("developer"))
                    {
                        new MainUserGUI();
                        loginFrame.setVisible(false);
                        
                    }
                    else if(role_name.equalsIgnoreCase("manager"))
                    {


                        new MainManagerGUI();
                        loginFrame.setVisible(false);
                    }

            }
            else
            {
                // showing error that entered username and password is invalid
                JOptionPane.showMessageDialog(loginFrame,"Enter Valid username and password","Invalid Login",JOptionPane.ERROR_MESSAGE);

            }
        }
        catch (SQLException | ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
    }


public static void main(String[] arg) throws SQLException, ClassNotFoundException {
    new LoginPage();
}
}