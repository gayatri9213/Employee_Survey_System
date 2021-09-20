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
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

import LoginPage.LoginPage;
import com.buttons.AllUserInputButtons;
import com.framesAndPanels.AllFramesAndPanels;
import com.labels.AllLabels;
import javafx.scene.control.Slider;

import static com.util.UtilityFunctions.createConnection;

public class MainUserGUI extends JFrame implements ActionListener, ItemListener {
    public int catId;

    ArrayList<Navigator> fetch = new ArrayList<Navigator>();
    ListIterator<Navigator> iterator;
    int size = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

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
    public JPanel bottomPanel;
    public JPanel buttonPanel;

    public JLabel createDateLabel;
    public JLabel publishDateLabel;
    public JLabel closeDateLabel;
    public JLabel closeDate;
    public JLabel createDate;
    public JLabel publishDate;
    public JLabel questionLabel1;
    public JLabel questionLabel2;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new MainUserGUI();
    }

    public MainUserGUI() throws ClassNotFoundException, SQLException {
        AllUserInputButtons userInputButtonsCallingObj = new AllUserInputButtons();
        AllFramesAndPanels userFramesAndPanelsObj = new AllFramesAndPanels();
        AllLabels labelsObj = new AllLabels();
        //     Navigator navigatorClass = new Navigator();

        categoryCombo = new JComboBox();
        categoryCombo.setSize(50, 50);
        categoryCombo.setBounds(10, 10, 200, 25);

        // Frames and panels code
        queFrame = userFramesAndPanelsObj.putFrame();
        mainPanel = userFramesAndPanelsObj.putMainPanel();
        secondPanel=userFramesAndPanelsObj.putSecondPanel();
        thirdPanel=userFramesAndPanelsObj.putThirdPanel();
        fourthPanel=userFramesAndPanelsObj.putFourthPanel();
        fifthPanel= userFramesAndPanelsObj.putFifthPanel();
        topPanel = userFramesAndPanelsObj.putTopPanel();
        bottomPanel = userFramesAndPanelsObj.putBottomPanel();
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
        createDate = labelsObj.createDate();
        closeDate = labelsObj.closeDate();
        publishDate = labelsObj.publishDate();
        questionLabel1 = labelsObj.putPublishDate();
        questionLabel2 = labelsObj.putPublishDate();

        // Add Panels and Buttons on the Frame
        queFrame.add(mainPanel);
        queFrame.add(secondPanel);
        queFrame.add(thirdPanel);
        queFrame.add(fourthPanel);
        queFrame.add(fifthPanel);
        queFrame.add(topPanel);
        queFrame.add(bottomPanel);
        queFrame.add(buttonPanel);

        buttonPanel.add(feedbackButton);
        bottomPanel.add(submitButton);
        bottomPanel.add(resetButton);
        mainPanel.add(nextButton);
        secondPanel.add(secondNextButton);
        thirdPanel.add(thirdNextButton);
        fourthPanel.add(fourthNextButton);
        fifthPanel.add(fifthNextButton);
        mainPanel.add(prevButton);

        topPanel.add(createDateLabel);
        topPanel.add(publishDateLabel);
        topPanel.add(closeDateLabel);

        topPanel.add(createDate);
        topPanel.add(publishDate);
        topPanel.add(closeDate);
        topPanel.add(createDate);
        topPanel.add(closeDate);
        topPanel.add(publishDate);
        mainPanel.add(categoryCombo);
        mainPanel.add(questionLabel1);

        queFrame.setSize(400, 400);
        queFrame.pack();
        queFrame.setVisible(true);

        feedbackButton.addActionListener(e -> {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String currentDate = dateFormat.format(now);
            ResultSet fetchCategory;
            try {
                Connection con = createConnection();
                ResultSet fetchCategoryId = con.createStatement().executeQuery("select distinct cat_id from survey_responses where '" + currentDate + "' between publish_date and close_date;");
                while (fetchCategoryId.next()) {
                    int categoryId = fetchCategoryId.getInt(1);
                    fetchCategory = con.createStatement().executeQuery("select category_name from categories where category_id = " + categoryId + " ");
                    while (fetchCategory.next()) {
                        String categoryName = fetchCategory.getString(1);
                        categoryCombo.addItem(categoryName);
                    }
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        categoryCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        Connection con = createConnection();
                        ResultSet fetchId = con.createStatement().executeQuery("select category_id from categories where category_name = '" + (String) categoryCombo.getSelectedItem() + "'");
                        fetchId.next();
                        catId = fetchId.getInt(1);
                        ResultSet fetchQuestions;

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
                        mainPanel.add(textFieldId);

                        JTextField textField = new JTextField();
                        textField.setBounds(150,50,600,40);
                        textField.setText(fetch.get(0).getFetchedQuestion());
                        textField.setEditable(false);
                        mainPanel.add(textField);

                        JSlider slider1 = new JSlider();
/*
                        int qIdSub = fetch.get(0).getFetchedQuestionId();
                        String qSub = fetch.get(0).getFetchedQuestion();
                        con.createStatement().execute("insert into survey_responses(emp_id,rating) values('"+login.LoginUsername+"','"+qSub+"') where catId='"+categoryCombo.getSelectedItem()+"'");
*/
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
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

                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,40);
                textFieldId.setText(String.valueOf(fetch.get(1).getFetchedQuestionId()));
                secondPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(1).getFetchedQuestion());
                textField.setEditable(false);
                secondPanel.add(textField);

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
                thirdPanel.add(textFieldId);
                int i = fetch.get(2).getFetchedQuestionId();

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(2).getFetchedQuestion());
                textField.setEditable(false);
                thirdPanel.add(textField);
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
                fourthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(3).getFetchedQuestion());
                textField.setEditable(false);
                fourthPanel.add(textField);
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
                fourthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(4).getFetchedQuestion());
                textField.setEditable(false);
                fourthPanel.add(textField);


            }
        });

        fifthNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fifthPanel.setVisible(true);
                fourthPanel.setVisible(false);

                JTextField textFieldId = new JTextField();
                textFieldId.setBounds(30,50,100,40);
                textFieldId.setText(String.valueOf(fetch.get(4).getFetchedQuestionId()));
                fifthPanel.add(textFieldId);

                JTextField textField = new JTextField();
                textField.setBounds(150,50,600,40);
                textField.setText(fetch.get(4).getFetchedQuestion());
                textField.setEditable(false);
                fifthPanel.add(textField);
            }
        });
    }
}

//  int x=30,y=5,width=250,height=25;
