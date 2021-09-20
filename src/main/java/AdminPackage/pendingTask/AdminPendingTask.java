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

public class AdminPendingTask implements InitiateComponents
{
    //private JFrame adminpendingtaskFrame = new JFrame("Admin Pending Tasks");
    public JPanel pendingTaskMainPanel = new JPanel();
    private JPanel upperPanel=new JPanel();
    private JTable table;
    public AdminPendingTask() throws SQLException, ClassNotFoundException {
        initComponents();

    }
    public void initComponents() throws SQLException, ClassNotFoundException {
        //adminpendingtaskFrame.setLayout(null);
        //adminpendingtaskFrame.setExtendedState(adminpendingtaskFrame.MAXIMIZED_BOTH);
        //adminpendingtaskFrame.setVisible(true);
        pendingTaskMainPanel.setVisible(true);
        pendingTaskMainPanel.setLayout(null);
        pendingTaskMainPanel.setBounds(251,120,1450,629);
        pendingTaskMainPanel.setBackground(Color.LIGHT_GRAY);
        //adminpendingtaskFrame.add(pendingTaskMainPanel);
        upperPanel= new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(0,0,1450,100);

        pendingTaskMainPanel.add(upperPanel);

        JLabel filterlabel = new JLabel("Filter :");
        filterlabel.setBackground(Color.white);
        filterlabel.setBounds(35,40,60,40);
        upperPanel.add(filterlabel);


        final JTextField filterTextField = new JTextField("");
        filterTextField.setBounds(85,40,250,40);
        filterTextField.setBackground(Color.white);


        upperPanel.add(filterTextField);

        JButton findFilterButton = new JButton("Find");
        findFilterButton.setBounds(340,40,60,40);
        upperPanel.add(findFilterButton);

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
            model.addRow(new Object[]{allUsersDataFetching.getInt(1), allUsersDataFetching.getString(2), allUsersDataFetching.getString(3), allUsersDataFetching.getLong(4)});
        }
        table = new JTable(model);
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        JScrollPane filterTableScrollPane =new JScrollPane(table);
        filterTableScrollPane.setBounds(2,101,1345,525);
        pendingTaskMainPanel.add(filterTableScrollPane);

        findFilterButton.addActionListener(e -> {
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
    /*public Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/survey_system", "Aress", "Aress@aress123");
        System.out.println("connection established");
        return conn;
    }*/

    /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new AdminPendingTask();
    }*/
}
