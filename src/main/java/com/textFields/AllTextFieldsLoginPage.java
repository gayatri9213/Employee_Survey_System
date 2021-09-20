package com.textFields;

import javax.swing.*;

public class AllTextFieldsLoginPage extends JTextField {
    static int LOGINPAGETEXTFIELDWIDTH=250;
    static  int LOGINPAGETEXTFIELDHEIGHT=30;
    public JTextField putUsernameTextField()
    {
        JTextField userNameField=new JTextField();
        userNameField.setBounds(150,20,LOGINPAGETEXTFIELDWIDTH,LOGINPAGETEXTFIELDHEIGHT);
        return userNameField;
    }

    public JPasswordField putPasswordField()
    {
       JPasswordField passwordField=new JPasswordField();
        passwordField.setBounds(150,70,LOGINPAGETEXTFIELDWIDTH,LOGINPAGETEXTFIELDHEIGHT);
        return passwordField;
    }
}
