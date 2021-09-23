package com.framesAndPanels;

import javax.swing.*;
import java.awt.*;
/*
* @Author : Mayur Pardeshi
* */
public class AllFramesAndPanels extends JFrame {

    JFrame queFrame= new JFrame("Survey");
    JPanel buttonPanel=new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel=new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel secondPanel = new JPanel();
    JPanel thirdPanel = new JPanel();
    JPanel fourthPanel = new JPanel();
    JPanel fifthPanel = new JPanel();
    JPanel commentPanel = new JPanel();

    public JFrame putFrame(){
        queFrame.setLayout(null);
        queFrame.setExtendedState(MAXIMIZED_BOTH);
        return queFrame;
    }

    public JPanel putCommentPanel(){
        commentPanel.setLayout(null);
        commentPanel.setBounds(251,120,1450,629);
        commentPanel.setBackground(Color.WHITE);
        return commentPanel;
    }

    public JPanel putMainPanel(){
        mainPanel.setLayout(null);
        mainPanel.setBounds(251,120,1450,629);
        mainPanel.setBackground(Color.WHITE);
        return mainPanel;
    }

    public JPanel putSecondPanel(){
        secondPanel.setLayout(null);
        secondPanel.setBounds(251,120,1450,629);
        secondPanel.setBackground(Color.WHITE);
        return secondPanel;
    }

    public JPanel putThirdPanel(){
        thirdPanel.setLayout(null);
        thirdPanel.setBounds(251,120,1450,629);
        thirdPanel.setBackground(Color.WHITE);
        return thirdPanel;
    }

    public JPanel putFourthPanel(){
        fourthPanel.setLayout(null);
        fourthPanel.setBounds(251,120,1450,629);
        fourthPanel.setBackground(Color.WHITE);
        return fourthPanel;
    }

    public JPanel putFifthPanel(){
        fifthPanel.setLayout(null);
        fifthPanel.setBounds(251,120,1450,629);
        fifthPanel.setBackground(Color.WHITE);
        return fifthPanel;
    }

    public JPanel putTopPanel() {
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1450,119);
        topPanel.setBackground(Color.lightGray);
        return topPanel;
    }

    public JPanel putBottomPanel(){
        bottomPanel.setBounds(0,750,1450,300);
        bottomPanel.setBackground(Color.lightGray);
        return bottomPanel;
    }

    public JPanel putButtonPanel(){
        buttonPanel.setBounds(0,120,250,629);
        buttonPanel.setBackground(Color.gray);
        return buttonPanel;
    }
}
