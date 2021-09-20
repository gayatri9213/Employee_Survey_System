package com.framesAndPanels;

import javax.swing.*;
import java.awt.*;

public class AllFrameLoginPage extends JFrame {
    JFrame loginFrame;
    public JFrame putLoginPageFrame()
    {
        loginFrame=new JFrame("Survey Login");
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setBackground(Color.white);
        loginFrame.setLayout(null);
        loginFrame.setResizable(false);
        loginFrame.pack();

        loginFrame.setVisible(true);
        loginFrame.setSize(500,250);
        loginFrame.setLocation(550,300);
        return loginFrame;
    }

}
