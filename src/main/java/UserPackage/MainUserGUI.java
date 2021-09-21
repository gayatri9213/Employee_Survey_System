package UserPackage;

import LoginPage.LoginPage;
import com.buttons.AllUserInputButtons;
import com.framesAndPanels.AllFramesAndPanels;
import com.labels.AllLabels;
import com.mysql.cj.protocol.Resultset;

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

import static com.util.UtilityFunctions.createConnection;

public class MainUserGUI {

    public int catId,qIdOne,qIdTwo,qIdThree,qIdFour,qIdFive;
    public int categoryId;
    String categoryName;
    Connection con = createConnection();
    ArrayList<Navigator> fetch = new ArrayList<Navigator>();

    public JButton feedbackButton;
    public JButton nextButton;
    public JButton secondNextButton;
    public JButton thirdNextButton;
    public JButton fourthNextButton;
    public JButton fifthNextButton;
    public JButton prevButton;
    public JButton submitButton;
    public JButton resetButton;
    public JComboBox categoryCombo;

    public JFrame queFrame;
    public JPanel mainPanel;
    public JPanel secondPanel;
    public JPanel thirdPanel;
    public JPanel fourthPanel;
    public JPanel fifthPanel;
    public JPanel topPanel;
    public JPanel buttonPanel;
    public JPanel blankPanel;

    public JLabel createDateLabel;
    public JLabel publishDateLabel;
    public JLabel closeDateLabel;
    public JLabel questionLabel1;
    public JLabel questionLabel2;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new MainUserGUI();
    }

    public MainUserGUI() throws ClassNotFoundException, SQLException {
        AllUserInputButtons userInputButtonsCallingObj = new AllUserInputButtons();
        AllFramesAndPanels userFramesAndPanelsObj = new AllFramesAndPanels();
        AllLabels labelsObj = new AllLabels();

        String selected = "-- SELECTED --";
        categoryCombo = new JComboBox();
        categoryCombo.addItem(selected);
        categoryCombo.setSelectedIndex(0);
        categoryCombo.setVisible(false);
        categoryCombo.setSize(50, 50);
        categoryCombo.setBounds(10, 10, 200, 25);

        // Frames and panels code
        queFrame = userFramesAndPanelsObj.putFrame();
        mainPanel = userFramesAndPanelsObj.putMainPanel();
        secondPanel=userFramesAndPanelsObj.putSecondPanel();
        thirdPanel=userFramesAndPanelsObj.putThirdPanel();
        fourthPanel=userFramesAndPanelsObj.putFourthPanel();
        fifthPanel= userFramesAndPanelsObj.putFifthPanel();
        blankPanel=userFramesAndPanelsObj.putBlankPanel();
        topPanel = userFramesAndPanelsObj.putTopPanel();
        buttonPanel = userFramesAndPanelsObj.putButtonPanel();

        // Buttons Code
        feedbackButton = userInputButtonsCallingObj.putFeedbackButton();
        nextButton = userInputButtonsCallingObj.putNextButton();
        secondNextButton=userInputButtonsCallingObj.putSecondButton() ;
        thirdNextButton=userInputButtonsCallingObj.putThirdButton();
        fourthNextButton=userInputButtonsCallingObj.putFourthButton();
        fifthNextButton=userInputButtonsCallingObj.putFifthButton();
        prevButton = userInputButtonsCallingObj.putPrevButton();
        submitButton = userInputButtonsCallingObj.putSubmitButton();
        resetButton = userInputButtonsCallingObj.putResetButton();

        // Labels Code
        createDateLabel = labelsObj.putCreateDate();
        publishDateLabel = labelsObj.putPublishDate();
        closeDateLabel = labelsObj.putCloseDate();
        questionLabel1 = labelsObj.putPublishDate();
        questionLabel2 = labelsObj.putPublishDate();

        // Add Panels and Buttons on the Frame
        queFrame.add(mainPanel);
        queFrame.add(secondPanel);
        queFrame.add(thirdPanel);
        queFrame.add(fourthPanel);
        queFrame.add(fifthPanel);
        queFrame.add(blankPanel);
        queFrame.add(topPanel);
        queFrame.add(buttonPanel);

        buttonPanel.add(feedbackButton);
        mainPanel.add(nextButton);
        secondPanel.add(secondNextButton);
        thirdPanel.add(thirdNextButton);
        fourthPanel.add(fourthNextButton);
        fifthPanel.add(fifthNextButton);
        mainPanel.add(prevButton);

        topPanel.add(createDateLabel);
        topPanel.add(publishDateLabel);
        topPanel.add(closeDateLabel);

        mainPanel.add(categoryCombo);
        mainPanel.add(questionLabel1);

        queFrame.setSize(400, 400);
        queFrame.pack();
        queFrame.setVisible(true);

        feedbackButton.addActionListener(e -> {
            mainPanel.setVisible(true);
            categoryCombo.setVisible(true);
            addComboBox();
        });

        categoryCombo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                Resultset dates;
                fetch.clear();
                ResultSet fetchId;
                ResultSet fetchQuestions;
                try {
                    fetchId = con.createStatement().executeQuery("select category_id from categories where category_name = '" + (String) categoryCombo.getSelectedItem() + "'");
                    fetchId.next();
                    catId = fetchId.getInt(1);

                    fetchQuestions = con.createStatement().executeQuery("select questions.question_id,questions.question from questions join category_question on (questions.question_id=category_question.question_id) join survey_responses on (survey_responses.question_id = category_question.question_id) where cat_id = '" + catId + "'  ");
                    while (fetchQuestions.next()) {
                       Navigator nav = new Navigator();
                        nav.setFetchedQuestionId(fetchQuestions.getInt(1));
                        nav.setFetchedQuestion(fetchQuestions.getString(2));
                        fetch.add(nav);
                    }

                    JTextField textFieldId = new JTextField();
                    textFieldId.setBounds(30,50,100,40);
                    textFieldId.setText(String.valueOf(fetch.get(0).getFetchedQuestionId()));
                    textFieldId.setEditable(false);
                    mainPanel.add(textFieldId);

                    JTextField textField = new JTextField();
                    textField.setBounds(150,50,600,40);
                    textField.setText(fetch.get(0).getFetchedQuestion());
                    textField.setEditable(false);
                    mainPanel.add(textField);

                    JSlider slider1 = new JSlider();
                    slider1.setBounds(150,120,600,40);
                    slider1.setMajorTickSpacing(1);
                    slider1.setPaintLabels(true);
                    System.out.println(slider1.getValue());
                    mainPanel.add(slider1);
/*

                    dates = (Resultset) con.createStatement().executeQuery("select publish_date,close_date from survey_responses where category_id = '"+catId+"");
                    ((ResultSet) dates).next();
                    String publishDate = ((ResultSet) dates).getString(1);
                    String closeDate = ((ResultSet) dates).getString(2);

                    JTextField publishField = new JTextField();
                    publishField.setBounds(200,20,100,20);
                    publishField.setText(publishDate);
                    publishField.setVisible(true);
                    publishField.setEditable(false);
                    topPanel.add(publishField);

                    JTextField closeField = new JTextField();
                    closeField.setBounds(200,25,100,20);
                    closeField.setText(closeDate);
                    closeField.setVisible(true);
                    closeField.setEditable(false);
                    topPanel.add(closeField);
*/
                    con.createStatement().execute(" UPDATE survey_responses SET emp_id= '"+LoginPage.LOGIN_USERID +"',rating = '"+slider1.getValue()+"',comment = 'none' WHERE question_id= '"+qIdOne+"'");

                } catch (SQLException ex) {
                        ex.printStackTrace();
                     }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondPanel.setVisible(true);
                mainPanel.setVisible(false);

                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,40);
                textFieldId.setText(String.valueOf(fetch.get(1).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                secondPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(1).getFetchedQuestion());
                textField.setEditable(false);
                secondPanel.add(textField);

                JSlider slider2 = new JSlider();
                slider2.setBounds(150,120,600,40);
                slider2.setMaximum(5);
                slider2.setMajorTickSpacing(1);
                slider2.setPaintLabels(true);
                secondPanel.add(slider2);
                qIdTwo=fetch.get(1).getFetchedQuestionId();

                try {
                    con.createStatement().execute(" UPDATE survey_responses SET emp_id= '"+LoginPage.LOGIN_USERID +"',rating = '"+slider2.getValue()+"',comment = 'none' WHERE question_id= '"+qIdTwo+"'");
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

                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,40);
                textFieldId.setText(String.valueOf(fetch.get(2).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                thirdPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(2).getFetchedQuestion());
                textField.setEditable(false);
                thirdPanel.add(textField);

                JSlider slider3 = new JSlider();
                slider3.setBounds(150,120,600,40);
                slider3.setMaximum(5);
                slider3.setMajorTickSpacing(1);
                slider3.setPaintLabels(true);
                thirdPanel.add(slider3);
                qIdThree=fetch.get(2).getFetchedQuestionId();

                try {
                    con.createStatement().execute(" UPDATE survey_responses SET emp_id= '"+LoginPage.LOGIN_USERID +"',rating = '"+slider3.getValue()+"',comment = 'none' WHERE question_id= '"+qIdThree+"'");
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

                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,40);
                textFieldId.setText(String.valueOf(fetch.get(3).getFetchedQuestionId()));
                textFieldId.setEditable(false);
                fourthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(3).getFetchedQuestion());
                textField.setEditable(false);
                fourthPanel.add(textField);

                JSlider slider4 = new JSlider();
                slider4.setBounds(150,120,600,40);
                slider4.setMaximum(5);
                slider4.setMajorTickSpacing(1);
                slider4.setPaintLabels(true);
                fourthPanel.add(slider4);
                qIdFour=fetch.get(3).getFetchedQuestionId();

                try {
                    con.createStatement().execute(" UPDATE survey_responses SET emp_id= '"+LoginPage.LOGIN_USERID +"',rating = '"+slider4.getValue()+"',comment = 'none' WHERE question_id= '"+qIdFour+"'");
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

                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,40);
                textFieldId.setText(String.valueOf(fetch.get(4).getFetchedQuestionId()));
                System.out.println(fetch.get(4).getFetchedQuestionId());
                textFieldId.setEditable(false);
                fifthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(4).getFetchedQuestion());
                textField.setEditable(false);
                fifthPanel.add(textField);

                JSlider slider5 = new JSlider();
                slider5.setBounds(150,120,600,40);
                slider5.setMaximum(5);
                slider5.setMajorTickSpacing(1);
                slider5.setPaintLabels(true);
                fifthPanel.add(slider5);
                qIdFive=fetch.get(4).getFetchedQuestionId();

                blankPanel.setVisible(true);
                fifthPanel.setVisible(false);

                JOptionPane.showMessageDialog(fifthPanel,"Your Response is Submitted Successfully");

                try {
                    con.createStatement().execute(" UPDATE survey_responses SET emp_id= '"+LoginPage.LOGIN_USERID +"',rating = '"+slider5.getValue()+"',comment = 'none' WHERE question_id= '"+qIdFive+"'");
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
            Connection con = createConnection();

            ResultSet fetchCategoryId = con.createStatement().executeQuery("select distinct cat_id from survey_responses where '" + currentDate + "' between publish_date and close_date;");
            while (fetchCategoryId.next()) {
                categoryId = fetchCategoryId.getInt(1);
                fetchCategory = con.createStatement().executeQuery("select category_name from categories where category_id = " + categoryId + " ");
                while (fetchCategory.next()) {
                    categoryName = fetchCategory.getString(1);
                    categoryCombo.addItem(categoryName);
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
