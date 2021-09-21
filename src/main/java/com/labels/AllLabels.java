package com.labels;

import javax.swing.*;

public class AllLabels extends JLabel{
    JLabel createDateLabel = new JLabel("Publish Date :");
    JLabel publishDateLabel=new JLabel("Create Date :");
    JLabel closeDateLabel=new JLabel("Close Date :");

    public JLabel putCreateDate() {
        createDateLabel.setBounds(10,12,100,10);
        return createDateLabel;
    }

    public JLabel putPublishDate(){
        publishDateLabel.setBounds(10,20,100,10);
        return publishDateLabel;
    }

    public JLabel putCloseDate(){
        closeDateLabel.setBounds(10,38,100,10);
        return closeDateLabel;
    }
}


/*


                        JTextField comment =new JTextField();
                        comment.setBounds(150,200,600,60);
                        comment.setVisible(false);
                        mainPanel.add(comment);
                        if(slider1.getValue() < 2){
                            comment.setVisible(true);
                        }



 */