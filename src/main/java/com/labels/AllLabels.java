package com.labels;

import javax.swing.*;

public class AllLabels extends JLabel{
    JLabel createDateLabel = new JLabel("Creation Date :");
    JLabel publishDateLabel=new JLabel("Publish Date :");
    JLabel closeDateLabel=new JLabel("Close Date :");
    JLabel closeDate=new JLabel();
    JLabel publishDate=new JLabel();
    JLabel createDate=new JLabel();

    public JLabel putCreateDate() {
        createDateLabel.setBounds(10,1,100,10);
        return createDateLabel;
    }

    public JLabel putPublishDate(){
        publishDateLabel.setBounds(10,20,100,10);
        return publishDateLabel;
    }

    public JLabel putCloseDate(){
        closeDateLabel.setBounds(10,41,100,10);
        return closeDateLabel;
    }

    public JLabel closeDate(){
        closeDate.setBounds(200,41,100,10);
        return closeDate;
    }

    public JLabel createDate(){
        createDate.setBounds(200,41,100,10);
        return createDate;
    }

    public JLabel publishDate(){
        publishDate.setBounds(200,41,100,10);
        return publishDate;
    }
}
