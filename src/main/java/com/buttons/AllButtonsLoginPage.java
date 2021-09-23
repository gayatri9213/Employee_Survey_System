package com.buttons;

import javax.swing.*;
import java.awt.*;
/*
* @Author : Somanshu Bendale
*   */
public class AllButtonsLoginPage extends JButton {
    static int LOGINPAGEBUTTONWIDTH=120;
    static int LOGINPAGEBUTTONHEIGHT=30;
    static int BUTTONFONTSIZE=15;
    static Font FONTSTYLE=new Font("serif",Font.BOLD,BUTTONFONTSIZE);

    private void colorSet(JButton tempColorSetObj)
    {

        tempColorSetObj.setBackground(Color.BLACK);
        tempColorSetObj.setForeground(Color.WHITE);

    }
    public JButton putLoginButton()
    {
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(40,140,LOGINPAGEBUTTONWIDTH,LOGINPAGEBUTTONHEIGHT);
        loginButton.setFont(FONTSTYLE);
        colorSet(loginButton);


        return loginButton;
    }
    public JButton putResetButton()
    {
        JButton resetButton=new JButton("Reset");
        resetButton.setBounds(180,140,LOGINPAGEBUTTONWIDTH,LOGINPAGEBUTTONHEIGHT);
        resetButton.setFont(FONTSTYLE);
        colorSet(resetButton);


        return resetButton;
    }
    public JButton putExitButton()
    {
        JButton exitButton=new JButton("Exit");
        exitButton.setBounds(320,140,LOGINPAGEBUTTONWIDTH,LOGINPAGEBUTTONHEIGHT);
        exitButton.setFont(FONTSTYLE);
        colorSet(exitButton);
        return exitButton;
    }
}
