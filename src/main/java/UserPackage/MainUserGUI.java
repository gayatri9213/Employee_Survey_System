package UserPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import LoginPage.LoginPage;
import com.buttons.AllUserInputButtons;
import com.framesAndPanels.AllFramesAndPanels;
import com.labels.AllLabels;

// Utility Class
import static com.util.UtilityFunctions.createConnection;

/*
* @Author: Mayur Pardeshi
* */



public class MainUserGUI extends JFrame {

    ArrayList<Responses> fetch = new ArrayList<Responses>();

    // constant width and height for radio buttons
    final int RADIOY =120, RADIOW =100, RADIOH = 40;

    private int categoryId,catId;
    private int resp,secResp,thirdResp,fourthResp,fifthResp;
    private static int selectedItem;
    private String categoryName,publishDate,closeDate,createDate;
    Connection con = createConnection();

    private JButton feedbackButton,nextButton,secondNextButton,thirdNextButton,fourthNextButton,fifthNextButton,submitButton;
    private JPanel mainPanel,secondPanel,thirdPanel,fourthPanel,fifthPanel,topPanel,buttonPanel,commentPanel;
    private JLabel publishDateLabel,closeDateLabel;
    private JTextField commentArea;

    private JRadioButton respOne,respTwo,respThree,respFive,respFour;
    private JComboBox categoryCombo;
    private JFrame queFrame;
    private ButtonGroup group;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new MainUserGUI();
    }

    public MainUserGUI() throws ClassNotFoundException, SQLException {

        // Creating Objects of Frame, panel class
        AllUserInputButtons userInputButtonsCallingObj = new AllUserInputButtons();
        AllFramesAndPanels userFramesAndPanelsObj = new AllFramesAndPanels();
        AllLabels labelsObj = new AllLabels();

        // initializing radio buttons
        respOne = new JRadioButton("Poor");
        respTwo=new JRadioButton("Average");
        respThree=new JRadioButton("Good");
        respFour=new JRadioButton("Very Good");
        respFive=new JRadioButton("Outstanding");

        // radio button
        String selected = "-- SELECTED --";
        categoryCombo = new JComboBox();
        categoryCombo.addItem(selected);
        categoryCombo.setSelectedIndex(0);
        categoryCombo.setVisible(false);
        categoryCombo.setSize(50, 50);
        categoryCombo.setBounds(10, 10, 200, 25);

        // Frames and panels
        queFrame = userFramesAndPanelsObj.putFrame();
        mainPanel = userFramesAndPanelsObj.putMainPanel();
        commentPanel = userFramesAndPanelsObj.putCommentPanel();
        secondPanel=userFramesAndPanelsObj.putSecondPanel();
        thirdPanel=userFramesAndPanelsObj.putThirdPanel();
        fourthPanel=userFramesAndPanelsObj.putFourthPanel();
        fifthPanel= userFramesAndPanelsObj.putFifthPanel();
        topPanel = userFramesAndPanelsObj.putTopPanel();
        buttonPanel = userFramesAndPanelsObj.putButtonPanel();

        // Buttons
        feedbackButton = userInputButtonsCallingObj.putFeedbackButton();
        nextButton = userInputButtonsCallingObj.putNextButton();
        secondNextButton=userInputButtonsCallingObj.putSecondButton() ;
        thirdNextButton=userInputButtonsCallingObj.putThirdButton();
        fourthNextButton=userInputButtonsCallingObj.putFourthButton();
        fifthNextButton=userInputButtonsCallingObj.putFifthButton();
        submitButton = userInputButtonsCallingObj.putSubmitButton();

        // Labels
        publishDateLabel = labelsObj.putPublishDate();
        closeDateLabel = labelsObj.putCloseDate();

        // Add Panels and Buttons on the Frame
        queFrame.add(mainPanel);
        queFrame.add(secondPanel);
        queFrame.add(thirdPanel);
        queFrame.add(fourthPanel);
        queFrame.add(fifthPanel);
        queFrame.add(topPanel);
        queFrame.add(buttonPanel);
        queFrame.add(commentPanel);

        buttonPanel.add(feedbackButton);

        secondPanel.add(secondNextButton);
        thirdPanel.add(thirdNextButton);
        fourthPanel.add(fourthNextButton);
        fifthPanel.add(fifthNextButton);
        submitButton.setVisible(false);
        commentPanel.add(submitButton);

        topPanel.add(publishDateLabel);
        topPanel.add(closeDateLabel);

        mainPanel.add(categoryCombo);
        nextButton.setEnabled(false);
        mainPanel.add(nextButton);

        queFrame.setSize(400, 400);
        queFrame.pack();
        queFrame.setVisible(true);

        feedbackButton.addActionListener(e -> {
            mainPanel.setVisible(true);
            categoryCombo.setVisible(true);
            addComboBox();
        });

        categoryCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        feedbackButton.setEnabled(false);
                        nextButton.setEnabled(true);
                        selectedItem = categoryCombo.getSelectedIndex();
                        fetch.clear();
                        ResultSet fetchId,publishAndCloseDate;
                        ResultSet fetchQuestions;

                        // fetch category_id based on option selected on the combobox
                        fetchId = con.createStatement().executeQuery("select category_id from categories where category_name = '" + (String) categoryCombo.getSelectedItem() + "'");
                        fetchId.next();
                        catId = fetchId.getInt(1);

                        // fetch questions based on option selected on combobx and store into the ArrayList
                        fetchQuestions = con.createStatement().executeQuery("select questions.question_id,questions.question from questions join category_question on (questions.question_id=category_question.question_id) join survey_responses on (survey_responses.question_id = category_question.question_id) where cat_id = '" + catId + "'  ");
                        while (fetchQuestions.next()) {
                            Responses respOne = new Responses();
                            respOne.setFetchedQuestionId(fetchQuestions.getInt(1));
                            respOne.setFetchedQuestion(fetchQuestions.getString(2));
                            fetch.add(respOne);
                        }

                        ///// DISPLAY PUBLISH AND CLOSE DATE ON TOP OF THE PANEL
                        publishAndCloseDate = con.createStatement().executeQuery("select creation_date,publish_date,close_date from survey_responses where cat_id = '"+catId+"'");
                        publishAndCloseDate.next();
                        createDate=publishAndCloseDate.getString(1);
                        publishDate = publishAndCloseDate.getString(2);
                        closeDate = publishAndCloseDate.getString(3);

                        //Add question, question_id and radio buttons on the panel
                        JTextField textFieldId = new JTextField();
                        textFieldId.setBounds(30,50,100,RADIOH);
                        textFieldId.setText(String.valueOf(fetch.get(0).getFetchedQuestionId()));
                        textFieldId.setEditable(false);
                        mainPanel.add(textFieldId);

                        JTextField textField = new JTextField();
                        textField.setBounds(150,50,600,RADIOH);
                        textField.setText(fetch.get(0).getFetchedQuestion());
                        textField.setEditable(false);
                        mainPanel.add(textField);

                        mainPanel.add(respOne);
                        mainPanel.add(respTwo);
                        mainPanel.add(respThree);
                        mainPanel.add(respFour);
                        mainPanel.add(respFive);

                        respOne.setBounds(150,RADIOY,RADIOW,RADIOH);
                        respTwo.setBounds(260,RADIOY,RADIOW,RADIOH);
                        respThree.setBounds(370,RADIOY,RADIOW,RADIOH);
                        respFour.setBounds(480,RADIOY,RADIOW,RADIOH);
                        respFive.setBounds(590,RADIOY,RADIOW,RADIOH);

                        group = new ButtonGroup();
                        group.add(respOne);
                        group.add(respTwo);
                        group.add(respThree);
                        group.add(respFour);
                        group.add(respFive);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondPanel.setVisible(true);
                mainPanel.setVisible(false);

                //Add question, question_id and radio buttons on the panel
                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,RADIOH);
                textFieldId.setText(String.valueOf(fetch.get(1).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                secondPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,RADIOH);
                textField.setText(fetch.get(1).getFetchedQuestion());
                textField.setEditable(false);
                secondPanel.add(textField);

                secondPanel.add(respOne);
                secondPanel.add(respTwo);
                secondPanel.add(respThree);
                secondPanel.add(respFour);
                secondPanel.add(respFive);

                respOne.setBounds(150,RADIOY,RADIOW,RADIOH);
                respTwo.setBounds(260,RADIOY,RADIOW,RADIOH);
                respThree.setBounds(370,RADIOY,RADIOW,RADIOH);
                respFour.setBounds(480,RADIOY,RADIOW,RADIOH);
                respFive.setBounds(590,RADIOY,RADIOW,RADIOH);

                group = new ButtonGroup();
                group.add(respOne);
                group.add(respTwo);
                group.add(respThree);
                group.add(respFour);
                group.add(respFive);

                if (respOne.isSelected()){
                    resp = 1;
                } else if (respTwo.isSelected()) {
                    resp= 2;
                }else if (respThree.isSelected()) {
                    resp= 3;
                } else if (respFour.isSelected()) {
                    resp= 4;
                } else if(respFive.isSelected()){
                    resp= 5;}

                int qIdOne=fetch.get(0).getFetchedQuestionId();

                try {
                    //   CHECK IF emp_id and question_id COMBINATION ALREADY AVAILABLE UPDATE RESPONSE, OTHERWISE INSERT DATA
                    con.createStatement().execute(" INSERT INTO survey_responses (emp_id,question_id,cat_id,rating,creation_date,publish_date,close_date) VALUES ('"+LoginPage.LOGIN_USERID+"' , '"+qIdOne+"' , '"+catId+"' , '"+resp+"' , '"+createDate+"', '"+publishDate+"' , '"+closeDate+"') ON  DUPLICATE KEY UPDATE rating = '"+resp+"'") ;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        secondNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thirdPanel.setVisible(true);
                secondPanel.setVisible(false);

                //Add question, question_id and radio buttons on the panel
                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,RADIOH);
                textFieldId.setText(String.valueOf(fetch.get(2).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                thirdPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,RADIOH);
                textField.setText(fetch.get(2).getFetchedQuestion());
                textField.setEditable(false);
                thirdPanel.add(textField);

                thirdPanel.add(respOne);
                thirdPanel.add(respTwo);
                thirdPanel.add(respThree);
                thirdPanel.add(respFour);
                thirdPanel.add(respFive);

                respOne.setBounds(150,RADIOY,RADIOW,RADIOH);
                respTwo.setBounds(260,RADIOY,RADIOW,RADIOH);
                respThree.setBounds(370,RADIOY,RADIOW,RADIOH);
                respFour.setBounds(480,RADIOY,RADIOW,RADIOH);
                respFive.setBounds(590,RADIOY,RADIOW,RADIOH);

                group = new ButtonGroup();
                group.add(respOne);
                group.add(respTwo);
                group.add(respThree);
                group.add(respFour);
                group.add(respFive);

                if (respOne.isSelected()){
                    secResp= 1;
                } else if (respTwo.isSelected()) {
                    secResp= 2;
                }else if (respThree.isSelected()) {
                    secResp= 3;
                } else if (respFour.isSelected()) {
                    secResp= 4;
                } else if(respFive.isSelected()){
                    secResp= 5;}

                int qIdTwo=fetch.get(1).getFetchedQuestionId();

                try {
                    //   CHECK IF emp_id and question_id COMBINATION ALREADY AVAILABLE UPDATE RESPONSE, OTHERWISE INSERT DATA
                    con.createStatement().execute(" INSERT INTO survey_responses (emp_id,question_id,cat_id,rating,creation_date,publish_date,close_date) VALUES ('"+LoginPage.LOGIN_USERID+"' , '"+qIdTwo+"' , '"+catId+"' , '"+secResp+"' ,'"+createDate+"' ,'"+publishDate+"' , '"+closeDate+"') ON  DUPLICATE KEY UPDATE rating = '"+secResp+"'");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        thirdNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fourthPanel.setVisible(true);
                thirdPanel.setVisible(false);

                //Add question, question_id and radio buttons on the panel
                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,RADIOH);
                textFieldId.setText(String.valueOf(fetch.get(3).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                fourthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,RADIOH);
                textField.setText(fetch.get(3).getFetchedQuestion());
                textField.setEditable(false);
                fourthPanel.add(textField);

                fourthPanel.add(respOne);
                fourthPanel.add(respTwo);
                fourthPanel.add(respThree);
                fourthPanel.add(respFour);
                fourthPanel.add(respFive);

                respOne.setBounds(150,RADIOY,RADIOW,RADIOH);
                respTwo.setBounds(260,RADIOY,RADIOW,RADIOH);
                respThree.setBounds(370,RADIOY,RADIOW,RADIOH);
                respFour.setBounds(480,RADIOY,RADIOW,RADIOH);
                respFive.setBounds(590,RADIOY,RADIOW,RADIOH);

                group = new ButtonGroup();
                group.add(respOne);
                group.add(respTwo);
                group.add(respThree);
                group.add(respFour);
                group.add(respFive);

                if (respOne.isSelected()){
                    thirdResp= 1;
                } else if (respTwo.isSelected()) {
                    thirdResp= 2;
                }else if (respThree.isSelected()) {
                    thirdResp= 3;
                } else if (respFour.isSelected()) {
                    thirdResp= 4;
                } else if(respFive.isSelected()){
                    thirdResp= 5;}

                int qIdThree=fetch.get(2).getFetchedQuestionId();
                try {
                    //   CHECK IF emp_id and question_id COMBINATION ALREADY AVAILABLE UPDATE RESPONSE, OTHERWISE INSERT DATA
                    con.createStatement().execute(" INSERT INTO survey_responses (emp_id,question_id,cat_id,rating,creation_date,publish_date,close_date) VALUES ('"+LoginPage.LOGIN_USERID+"' , '"+qIdThree+"' , '"+catId+"' , '"+thirdResp+"' , '"+createDate+"', '"+publishDate+"' , '"+closeDate+"') ON  DUPLICATE KEY UPDATE rating = '"+thirdResp+"'") ;
                     } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        fourthNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fifthPanel.setVisible(true);
                fourthPanel.setVisible(false);

                //Add question, question_id and radio buttons on the panel
                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,RADIOH);
                textFieldId.setText(String.valueOf(fetch.get(4).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                fifthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,RADIOH);
                textField.setText(fetch.get(4).getFetchedQuestion());
                textField.setEditable(false);
                fifthPanel.add(textField);

                fifthPanel.add(respOne);
                fifthPanel.add(respTwo);
                fifthPanel.add(respThree);
                fifthPanel.add(respFour);
                fifthPanel.add(respFive);

                respOne.setBounds(150,RADIOY,RADIOW,RADIOH);
                respTwo.setBounds(260,RADIOY,RADIOW,RADIOH);
                respThree.setBounds(370,RADIOY,RADIOW,RADIOH);
                respFour.setBounds(480,RADIOY,RADIOW,RADIOH);
                respFive.setBounds(590,RADIOY,RADIOW,RADIOH);

                group = new ButtonGroup();
                group.add(respOne);
                group.add(respTwo);
                group.add(respThree);
                group.add(respFour);
                group.add(respFive);

                if (respOne.isSelected()){
                    fourthResp= 1;
                } else if (respTwo.isSelected()) {
                    fourthResp= 2;
                }else if (respThree.isSelected()) {
                    fourthResp= 3;
                } else if (respFour.isSelected()) {
                    fourthResp= 4;
                } else if(respFive.isSelected()){
                    fourthResp= 5;}

                int qIdFour=fetch.get(3).getFetchedQuestionId();
                try {
                    //   CHECK IF emp_id and question_id COMBINATION ALREADY AVAILABLE UPDATE RESPONSE, OTHERWISE INSERT DATA
                    con.createStatement().execute(" INSERT INTO survey_responses (emp_id,question_id,cat_id,rating,creation_date,publish_date,close_date) VALUES ('"+LoginPage.LOGIN_USERID+"' , '"+qIdFour+"' , '"+catId+"' , '"+fourthResp+"' , '"+createDate+"' , '"+publishDate+"' , '"+closeDate+"') ON  DUPLICATE KEY UPDATE rating = '"+fourthResp+"'") ;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        fifthNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fifthPanel.setVisible(true);
                fourthPanel.setVisible(false);

                //Add question, question_id and radio buttons on the panel
                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,RADIOH);
                textFieldId.setText(String.valueOf(fetch.get(4).getFetchedQuestionId()));
                fifthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,RADIOH);
                textField.setText(fetch.get(4).getFetchedQuestion());
                textField.setEditable(false);
                fifthPanel.add(textField);

                fifthPanel.add(respOne);
                fifthPanel.add(respTwo);
                fifthPanel.add(respThree);
                fifthPanel.add(respFour);
                fifthPanel.add(respFive);

                respOne.setBounds(150,RADIOY,RADIOW,RADIOH);
                respTwo.setBounds(260,RADIOY,RADIOW,RADIOH);
                respThree.setBounds(370,RADIOY,RADIOW,RADIOH);
                respFour.setBounds(480,RADIOY,RADIOW,RADIOH);
                respFive.setBounds(590,RADIOY,RADIOW,RADIOH);

                group = new ButtonGroup();
                group.add(respOne);
                group.add(respTwo);
                group.add(respThree);
                group.add(respFour);
                group.add(respFive);

                if (respOne.isSelected()){
                    fifthResp= 1;
                } else if (respTwo.isSelected()) {
                    fifthResp= 2;
                }else if (respThree.isSelected()) {
                    fifthResp= 3;
                } else if (respFour.isSelected()) {
                    fifthResp= 4;
                } else if(respFive.isSelected()){
                    fifthResp= 5;}

                int qIdFive=fetch.get(4).getFetchedQuestionId();
                try {
                    //   CHECK IF emp_id and question_id COMBINATION ALREADY AVAILABLE UPDATE RESPONSE, OTHERWISE INSERT DATA
                    con.createStatement().execute(" INSERT INTO survey_responses (emp_id,question_id,cat_id,rating,creation_date,publish_date,close_date) VALUES ('"+LoginPage.LOGIN_USERID+"' , '"+qIdFive+"' , '"+catId+"' , '"+fifthResp+"' , '"+createDate+"' , '"+publishDate+"' , '"+closeDate+"') ON  DUPLICATE KEY UPDATE rating = '"+fifthResp+"'") ;
                    ResultSet avgRating = con.createStatement().executeQuery("select avg(rating) from survey_responses where emp_id = '"+LoginPage.LOGIN_USERID+"'");
                    avgRating.next();
                        int average = avgRating.getInt(1);

                    // CHECK IF RATING IS LESS THAN 3 GO TO THE NEXT PANEL,OTHERWISE SUBMIT RESPONSE
                    if(average < 3){
                        fifthPanel.setVisible(false);
                        commentPanel.setVisible(true);
                        submitButton.setVisible(true);

                        JTextField commentReq = new JTextField();
                        commentReq.setBounds(30,50,600,RADIOH);
                        commentReq.setText("Your Ratings are below Average, do you have any other Suggestions ?");
                        commentReq.setEditable(false);
                        commentPanel.add(commentReq);
                        commentPanel.setVisible(false);

                        commentPanel.setVisible(true);
                        commentArea = new JTextField();
                        commentArea.setBounds(30,130,600,80);
                        commentPanel.add(commentArea);
                    }else{
                        JOptionPane.showMessageDialog(fifthPanel,"Your Response is Submitted Successfully");
                        mainPanel.setVisible(true);
                        commentPanel.setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comments = commentArea.getText();
                try {
                    // store comment in table for selected category ID

                    con.createStatement().execute(" UPDATE survey_responses SET comment = '"+comments+"' WHERE emp_id = '"+LoginPage.LOGIN_USERID+"' ");
                    JOptionPane.showMessageDialog(fifthPanel,"Your Response is Submitted Successfully");
                    mainPanel.setVisible(true);
                    commentPanel.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void addComboBox(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dateFormat.format(now);
        ResultSet fetchCategory;

        try {

            // Code for adding disting category name (within given date) in combobox

            ResultSet fetchCategoryId = con.createStatement().executeQuery("select distinct cat_id from survey_responses where '" + currentDate + "' between publish_date and close_date;");
            while (fetchCategoryId.next()) {
                categoryId = fetchCategoryId.getInt(1);
                fetchCategory = con.createStatement().executeQuery("select category_name from categories where category_id = " + categoryId + " ");
                while (fetchCategory.next()) {
                    categoryName = fetchCategory.getString(1);
                    categoryCombo.addItem(categoryName);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


