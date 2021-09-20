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

class reportsGeneration extends JFrame implements ActionListener {


    private Object ClassNotFoundException;
    private Object SQLException;

    public void actionPerformed(ActionEvent e) {

    }



    public void PanelExample() throws ClassNotFoundException, SQLException {
        JFrame queFrame= new JFrame("Survey");
        JPanel buttonPanel=new JPanel();
        JPanel topPanel = new JPanel();

        JPanel mainPanel = new JPanel();

        JScrollBar scroll = new JScrollBar(JScrollBar.HORIZONTAL);
        JButton ReportButton=new JButton("Show Reports");


        // Panels Code
        queFrame.setLayout(null);
        queFrame.setExtendedState(MAXIMIZED_BOTH);
        mainPanel.setLayout(null);

        scroll.setBounds(1421,120,20,   629);

        topPanel.setBounds(0,0,1450,119);
        topPanel.setBackground(Color.lightGray);



        buttonPanel.setBounds(0,120,250,800);
        buttonPanel.setBackground(Color.gray);

        mainPanel.setBounds(251,120,1450,800);
        mainPanel.setBackground(Color.WHITE);


        // Buttons Code
        ReportButton.setBounds(50,110,120,30);
        ReportButton.setBackground(Color.lightGray);



        // Add Panels and Buttons on the Frame
        buttonPanel.add(ReportButton);

        // Add ComboBox


        queFrame.add(mainPanel);
        queFrame.add(topPanel);

        queFrame.add(buttonPanel);
        queFrame.add(scroll);


        queFrame.setSize(400,400);
        queFrame.pack();
        queFrame.setVisible(true);
        mainPanel.setVisible(true);

        // ActionListners and ItemListener
        ReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/default", "root", "Riddhi@24");
                    mainPanel.setSize(1350,800);
                    HashMap para=new HashMap();
                    mainPanel.removeAll();
                    mainPanel.repaint();;
                    mainPanel.revalidate();

                    FileInputStream in=new FileInputStream(new File("C:\\Users\\user\\eclipse-workspace\\project\\EmpReport.jrxml"));
                    JasperDesign jd=JRXmlLoader.load(in);

                    String sql;
                    sql = "select * from employee";
                    JRDesignQuery newQuery=new JRDesignQuery();
                    newQuery.setText(sql);
                    jd.setQuery(newQuery);

                    JasperReport jr= JasperCompileManager.compileReport(jd);
                    JasperPrint j=JasperFillManager.fillReport(jr, para, connection);


                    JRViewer v = new JRViewer(j);
                    mainPanel.setLayout(new BorderLayout());
                    mainPanel.add(v);








                    //JasperViewer.viewReport(j, false);
                    FileOutputStream os=new FileOutputStream(new File("D:\\reports\\demo1.pdf"));
                    JasperExportManager.exportReportToPdfStream(j, os);


                } catch (Exception ae) {
                    JOptionPane.showMessageDialog(null, e);
                  ae.printStackTrace();

                }

            }


        });
        
        


    }



}

class CommanGuiManual
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        reportsGeneration reportsGeneration =new reportsGeneration();
        reportsGeneration.PanelExample();
    }
}