import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class logs extends javax.swing.JFrame {

    public logs() {
        initComponents();
    }

    private void initComponents() {
        // Your existing code for initializing components
        // (Ensure the components are added here)
    }

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {
        String fn, ln, em, query;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details
            String url = "jdbc:mysql://localhost:3307/java_user_db";
            String user = "root";
            String pass = "";

            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();

            if ("".equals(fname.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "First Name is required", "Dialog", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(lname.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Last Name is required", "Dialog", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(mail.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Email is required", "Dialog", JOptionPane.ERROR_MESSAGE);
            } else {
                fn = fname.getText();
                ln = lname.getText();
                em = mail.getText();

                // Insert data into the database
                query = "INSERT INTO user (first_name, last_name, email) VALUES ('" + fn + "','" + ln + "','" + em + "')";
                st.executeUpdate(query);
                con.close();

                // If signup is successful, redirect to the Dashboard
                JOptionPane.showMessageDialog(new JFrame(), "User Registered Successfully");
                redirectToDashboard();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void redirectToDashboard() {
        // Close the login window
        this.dispose();

        // Open the Dashboard window
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration (do not modify)
    private javax.swing.JTextField fname;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField mail;
    private javax.swing.JButton submit;
    // End of variables declaration
}
