/*
  on clicking button new frame open to create and update record
 */
package AdminPackage;

import AdminPackage.pendingTask.AdminPendingTask;

import CreateSurvey.MainClass;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.regex.Pattern;


public class MainAdminGUI extends JFrame {
    JFrame frame;
    JPanel buttonPanel, topPanel, mainPanel, editPanel, createPanel, pendingPanel,reportPanel;
    JButton Create, Editprofile,Createsurvey,Pendingtask,Report1;
    JLabel title;









    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);



    public MainAdminGUI ()
    {
        frame = new JFrame("Admin Module");
        buttonPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        editPanel = new JPanel();
        createPanel = new JPanel();
        pendingPanel=new JPanel();
        reportPanel=new JPanel();
        JScrollBar scroll = new JScrollBar(JScrollBar.HORIZONTAL);
        Create = new JButton("Create User");
        Editprofile = new JButton("Edit Profile");
        Createsurvey = new JButton("Create Survey");
        Pendingtask = new JButton("Pending Task");

        Report1 = new JButton("View Report");




        frame.setLayout(null);
        frame.setExtendedState(MAXIMIZED_BOTH);
        scroll.setBounds(1421, 120, 20, 629);
        topPanel.setBounds(250, 0, 1450, 120);
        topPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(0, 0, 250, 710);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(null);
        mainPanel.setBounds(251, 120, 1451, 629);
        mainPanel.setBackground(Color.WHITE);



        Create.setBounds(30, 150, 180, 40);
        Create.setBackground(Color.lightGray);
        Editprofile.setBounds(30, 230, 180, 40);
        Editprofile.setBackground(Color.lightGray);
        Createsurvey.setBounds(30, 310, 180, 40);
        Createsurvey.setBackground(Color.lightGray);
        Pendingtask.setBounds(30, 390, 180, 40);
        Pendingtask.setBackground(Color.lightGray);
        Report1.setBounds(30, 460, 180, 40);
        Report1.setBackground(Color.lightGray);


        //Label Code
        title = new JLabel("ADMIN ");
        title.setBounds(50, 100, 100, 30);
        title.setFont(new Font("Verdana", Font.PLAIN, 32));
        title.setForeground(new Color(255, 255, 255));

        // Add Panels and Buttons on the Frame
        buttonPanel.add(Create);
        buttonPanel.add(Editprofile);
        buttonPanel.add(Createsurvey);
        buttonPanel.add(Pendingtask);
        buttonPanel.add(Report1);
        topPanel.add(title);
        frame.add(pendingPanel);
        //pendingPanel.setVisible(false);
        frame.add(mainPanel);
        frame.add(editPanel);
        frame.add(createPanel);
        frame.add(topPanel);
        frame.add(buttonPanel);
        frame.add(reportPanel);
        frame.add(scroll);
        frame.setSize(400, 400);
        frame.pack();
        frame.setVisible(true);

        Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Createu();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Editprofile
                .addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        try {
                            Editu();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

        Pendingtask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminPendingTask adminPendingTask=new AdminPendingTask();


                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Createsurvey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    survey();
                } catch (SQLException |ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        Report1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Report();
            }
        });



        Createsurvey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });


    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MainAdminGUI gui = new MainAdminGUI();

    }


    public void Createu() throws SQLException, ClassNotFoundException {
        Createuser cu=new Createuser();


    }

    public void Editu() throws SQLException, ClassNotFoundException {
        Editprofile ep=new Editprofile();

    }

    public void survey() throws SQLException, ClassNotFoundException {
        MainClass s = new MainClass();

    }

    public void Report()    {
        reportPanel.setBounds(251, 120, 1451, 629);
        reportPanel.setBackground(Color.WHITE);
        reportPanel.setLayout(null);

        JLabel EditTitle = new JLabel("Report");
        EditTitle.setBounds(500, 10, 100, 20);
        EditTitle.setVerticalAlignment(SwingConstants.TOP);
        reportPanel.add(EditTitle);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_mgmt", "Aress", "Aress@aress123");
            //reportPanel.setSize(1350,800);
            reportPanel.setBounds(251, 120, 1000, 800);

            HashMap para=new HashMap();
            reportPanel.removeAll();
            reportPanel.repaint();;
            reportPanel.revalidate();

            FileInputStream in=new FileInputStream(new File("C:\\Users\\user\\eclipse-workspace\\hello\\AdminReport.jrxml"));
            JasperDesign jd= JRXmlLoader.load(in);

            String sql;
            sql = " select survey_responses.emp_id,survey_responses.manager_id,users.username,questions.question,categories.category_name,survey_responses.rating,survey_responses.comment from survey_responses,users,categories,questions where users.user_id=survey_responses.manager_id and categories.category_id=survey_responses.cat_id and questions.question_id=survey_responses.question_id ;";
            JRDesignQuery newQuery=new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint j= JasperFillManager.fillReport(jr, para, connection);


            JRViewer v = new JRViewer(j);
            reportPanel.setLayout(new BorderLayout());
            reportPanel.add(v);

            //JasperViewer.viewReport(j, false);
            FileOutputStream os=new FileOutputStream(new File("D:\\reports\\Admin report.pdf"));
            JasperExportManager.exportReportToPdfStream(j, os);


        } catch (Exception ae) {

            ae.printStackTrace();

        }

    }




}