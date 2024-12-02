import javax.swing.*;
import java.sql.*;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginAndDashboardApp extends JFrame {

    // Swing components for login
    private JTextField fname, lname, mail;
    private JButton submit;

    // Constructor to initialize Swing components (Login)
    public LoginAndDashboardApp() {
        setTitle("User Login");
        setSize(620, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
        
        JLabel jLabel1 = new JLabel("User Sign Up");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
        
        JLabel jLabel2 = new JLabel("First Name");
        JLabel jLabel3 = new JLabel("Last Name");
        JLabel jLabel4 = new JLabel("Email");

        fname = new JTextField();
        lname = new JTextField();
        mail = new JTextField();
        
        submit = new JButton("Sign Up");
        submit.addActionListener(e -> submitActionPerformed());

        // Layout setup for Swing Login form
        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                    .addComponent(fname)
                    .addComponent(lname)
                    .addComponent(mail, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fname, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lname, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(jLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mail, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addGap(28)
                .addComponent(submit)
                .addGap(76))
        );

        this.add(jPanel1);
    }

    // Action on Sign Up button click
    private void submitActionPerformed() {
        String fn, ln, em, query;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:MySQL://localhost:3307/java_user_db";
            String user = "root";
            String pass = "";

            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();

            if (fname.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First Name is required", "Dialog", JOptionPane.ERROR_MESSAGE);
            } else if (lname.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Last Name is required", "Dialog", JOptionPane.ERROR_MESSAGE);
            } else if (mail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email is required", "Dialog", JOptionPane.ERROR_MESSAGE);
            } else {
                fn = fname.getText();
                ln = lname.getText();
                em = mail.getText();
                query = "INSERT INTO user (first_name, last_name, email) VALUES ('" + fn + "','" + ln + "','" + em + "')";
                st.executeUpdate(query);
                con.close();
                this.setVisible(false);  // Hide login screen
                launchDashboard();  // Show dashboard
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to launch JavaFX Dashboard
    private void launchDashboard() {
        SwingUtilities.invokeLater(() -> {
            new Thread(() -> {
                JFXPanel fxPanel = new JFXPanel(); // Required to initialize JavaFX in Swing
                javax.swing.JFrame frame = new javax.swing.JFrame("Dashboard");
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(fxPanel);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                Application.launch(Dashboard1.class);
            }).start();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginAndDashboardApp().setVisible(true);
        });
    }
}

class Dashboard1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        // Top Bar (User info + App Title)
        HBox topBar = createTopBar();
        borderPane.setTop(topBar);

        // Side Bar (Menu with Buttons)
        VBox sideBar = createSideBar(borderPane);
        borderPane.setLeft(sideBar);

        // Center Content Area (Dynamic based on button clicks)
        StackPane contentArea = new StackPane();
        contentArea.getChildren().add(new Label("Welcome to the Dashboard!"));
        borderPane.setCenter(contentArea);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Modern Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Create Top Bar (Header with user info and title)
    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #2C3E50;");
        topBar.setPadding(new javafx.geometry.Insets(10));
        topBar.setSpacing(10);

        ImageView profileImage = new ImageView(new Image("https://www.w3schools.com/w3images/avatar2.png"));
        profileImage.setFitHeight(40);
        profileImage.setFitWidth(40);

        Label userName = new Label("Jane Doe");
        userName.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        HBox userBox = new HBox(10, profileImage, userName);
        userBox.setAlignment(Pos.CENTER_LEFT);

        Label appTitle = new Label("My Dashboard");
        appTitle.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(userBox, spacer, appTitle);
        return topBar;
    }

    // Create Side Bar with Buttons
    private VBox createSideBar(BorderPane borderPane) {
        VBox sideBar = new VBox();
        sideBar.setStyle("-fx-background-color: #34495E;");
        sideBar.setPrefWidth(200);
        sideBar.setPadding(new javafx.geometry.Insets(10));
        sideBar.setSpacing(20);

        Button homeButton = createMenuButton("Home", borderPane);
        Button settingsButton = createMenuButton("Settings", borderPane);
        Button profileButton = createMenuButton("Profile", borderPane);

        sideBar.getChildren().addAll(homeButton, settingsButton, profileButton);
        return sideBar;
    }

    private Button createMenuButton(String text, BorderPane borderPane) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> onMenuButtonClick(text, borderPane));
        return button;
    }

    private void onMenu
