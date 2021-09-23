package AdminPackage.pendingTask;

import com.util.InitiateComponents;
import com.util.UtilityFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;
import java.util.regex.PatternSyntaxException;
/*
* @Author : Somanshu Bendale
*  */
public class AdminPendingTask implements InitiateComponents
{

    public JPanel pendingTaskMainPanel = new JPanel();
    private JPanel upperPanel=new JPanel();
    private JTable table;
    public AdminPendingTask() throws SQLException, ClassNotFoundException {
        initComponents();

    }
    public void initComponents() throws SQLException, ClassNotFoundException {
        //setting up main panel
        pendingTaskMainPanel.setVisible(true);
        pendingTaskMainPanel.setLayout(null);
        pendingTaskMainPanel.setBounds(251,120,1450,629);
        pendingTaskMainPanel.setBackground(Color.LIGHT_GRAY);

        //creating main panel for adding textfield and filter button
        upperPanel= new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0,0,1450,100);

        pendingTaskMainPanel.add(upperPanel);




        final JTextField filterTextField = new JTextField("");
        filterTextField.setBounds(85,40,250,40);
        filterTextField.setBackground(Color.white);


        upperPanel.add(filterTextField);

        JButton findFilterButton = new JButton("Find");
        findFilterButton.setBounds(340,40,60,40);
        upperPanel.add(findFilterButton);

        //creating model to display  table amd perform filter operation on it
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Developer-id");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("phone Number");

        Connection conn= UtilityFunctions.createConnection();

        ResultSet allUsersDataFetching;
        PreparedStatement pstm = conn.prepareStatement("select users.user_id, users.username,users.email,users.phno from users left join survey_responses on users.user_id=survey_responses.emp_id where survey_responses.emp_id is null and users.role='developer'");
        allUsersDataFetching = pstm.executeQuery();
        while(allUsersDataFetching.next())
        {
            // adding the row which is not present in the survey_response table
            model.addRow(new Object[]{allUsersDataFetching.getInt(1), allUsersDataFetching.getString(2), allUsersDataFetching.getString(3), allUsersDataFetching.getLong(4)});
        }
        table = new JTable(model);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        JScrollPane filterTableScrollPane =new JScrollPane(table);
        filterTableScrollPane.setBounds(2,101,1345,525);
        pendingTaskMainPanel.add(filterTableScrollPane);


        findFilterButton.addActionListener(e -> {
            //checking the regex pattern to perform search operation on model
            String text = filterTextField.getText();
            if(text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch(PatternSyntaxException pse) {
                    System.out.println("Bad regex pattern");
                }
            }
        });




    }

}
