import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class logggg extends Application {

    private Scene loginScene;
    private Scene dashboardScene;

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window
        primaryStage.setTitle("Application");

        // Create Login Page
        GridPane loginGrid = createLoginPage(primaryStage);

        // Create Dashboard Page
        BorderPane dashboardPane = createDashboardPage();
        dashboardScene = new Scene(dashboardPane, 800, 600);

        // Set the initial scene to the login page
        loginScene = new Scene(loginGrid, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private GridPane createLoginPage(Stage primaryStage) {
        // Layout for login page
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Full Name Field
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Enter your full name");
        fullNameField.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
        grid.add(fullNameField, 0, 0);

        // Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter a strong password (8+ characters)");
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
        grid.add(passwordField, 0, 1);

        // Email Field
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email address");
        emailField.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
        grid.add(emailField, 0, 2);

        // Terms of Service CheckBox
        CheckBox termsCheckBox = new CheckBox("I agree to the terms and services");
        termsCheckBox.setStyle("-fx-font-size: 12px;");
        grid.add(termsCheckBox, 0, 3);

        // Sign Up Button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
        signUpButton.setOnAction(e -> {
            String fullName = fullNameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            boolean termsAccepted = termsCheckBox.isSelected();

            // Validation checks
            if (fullName.isEmpty() || password.isEmpty() || email.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
                return;
            }

            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid email address!");
                return;
            }

            if (!termsAccepted) {
                showAlert(Alert.AlertType.ERROR, "Error", "You must agree to the terms and services.");
                return;
            }

            // Redirect to the dashboard
            showAlert(Alert.AlertType.INFORMATION, "Success", "Sign-up successful! Welcome, " + fullName + "!");
            primaryStage.setScene(dashboardScene);
        });
        grid.add(signUpButton, 0, 4);

        return grid;
    }

    private BorderPane createDashboardPage() {
        // Main layout: BorderPane
        BorderPane borderPane = new BorderPane();

        // Top Bar (User info + App Title)
        HBox topBar = createTopBar();
        borderPane.setTop(topBar);

        // Side Bar (Menu with Buttons)
        VBox sideBar = new VBox();
        StackPane contentArea = new StackPane();
        VBox finalSideBar = createSideBar(contentArea); // Pass contentArea for dynamic updates
        borderPane.setLeft(finalSideBar);

        // Center Content Area (Dynamic based on button clicks)
        borderPane.setCenter(contentArea);
        contentArea.getChildren().add(new Label("Welcome to the Dashboard!"));

        return borderPane;
    }

    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #2C3E50;");
        topBar.setPadding(new javafx.geometry.Insets(10));
        topBar.setSpacing(10);

        // User Info (profile image and name)
        ImageView profileImage = new ImageView(new Image("https://www.w3schools.com/w3images/avatar2.png"));
        profileImage.setFitHeight(40);
        profileImage.setFitWidth(40);

        Label userName = new Label("Jane Doe");
        userName.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        HBox userBox = new HBox(10, profileImage, userName);
        userBox.setAlignment(Pos.CENTER_LEFT);

        // App Title
        Label appTitle = new Label("My Dashboard");
        appTitle.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(userBox, spacer, appTitle);

        return topBar;
    }

    private VBox createSideBar(StackPane contentArea) {
        VBox sideBar = new VBox();
        sideBar.setStyle("-fx-background-color: #34495E;");
        sideBar.setPrefWidth(200);
        sideBar.setPadding(new javafx.geometry.Insets(10));
        sideBar.setSpacing(20);

        // Menu Buttons
        Button homeButton = createMenuButton("Home", contentArea);
        Button settingsButton = createMenuButton("Settings", contentArea);
        Button profileButton = createMenuButton("Profile", contentArea);

        // Add buttons to the side bar
        sideBar.getChildren().addAll(homeButton, settingsButton, profileButton);

        return sideBar;
    }

    private Button createMenuButton(String text, StackPane contentArea) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(e -> onMenuButtonClick(text, contentArea));
        return button;
    }

    private void onMenuButtonClick(String section, StackPane contentArea) {
        Label content = new Label("You selected: " + section);
        content.setStyle("-fx-font-size: 20px; -fx-text-fill: #2C3E50;");

        contentArea.getChildren().clear();
        contentArea.getChildren().add(content);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
