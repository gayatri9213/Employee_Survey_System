package com.labels;

import javax.swing.*;
/*\
* @Author : Somanshu Bendale
* */
public class AllLabelsLoginPage extends JLabel
{
    static int LOGINPAGELABELWIDTH=100;
    static int LOGINPAGELABELHEIGHT =30;
    public JLabel putUsernameLabel()
    {
       JLabel userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(40,20,LOGINPAGELABELWIDTH, LOGINPAGELABELHEIGHT);
        return userNameLabel;
    }

    public JLabel putPasswordLabel()
    {
       JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40,70,LOGINPAGELABELWIDTH, LOGINPAGELABELHEIGHT);
        return passwordLabel;
    }


}
