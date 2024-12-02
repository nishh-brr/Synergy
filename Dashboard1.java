import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Dashboard1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main layout: BorderPane
        BorderPane borderPane = new BorderPane();

        // Top Bar (User info + App Title)
        HBox topBar = createTopBar();
        borderPane.setTop(topBar);

        // Side Bar (Menu with Buttons)
        VBox sideBar = createSideBar(borderPane);  // Pass borderPane for dynamic updates
        borderPane.setLeft(sideBar);

        // Center Content Area (Dynamic based on button clicks)
        StackPane contentArea = new StackPane();
        contentArea.getChildren().add(new Label("Welcome to the Dashboard!"));
        borderPane.setCenter(contentArea);

        // Create Scene and display the window
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

    // Create Side Bar with Buttons
    private VBox createSideBar(BorderPane borderPane) {
        VBox sideBar = new VBox();
        sideBar.setStyle("-fx-background-color: #34495E;");
        sideBar.setPrefWidth(200);
        sideBar.setPadding(new javafx.geometry.Insets(10));
        sideBar.setSpacing(20);

        // Menu Buttons
        Button homeButton = createMenuButton("Home", borderPane);
        Button settingsButton = createMenuButton("Settings", borderPane);
        Button profileButton = createMenuButton("Profile", borderPane);

        // Add buttons to the side bar
        sideBar.getChildren().addAll(homeButton, settingsButton, profileButton);

        return sideBar;
    }

    // Helper method to create a button with a modern style
    private Button createMenuButton(String text, BorderPane borderPane) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE); // Button will stretch to the full width
        button.setOnAction(e -> onMenuButtonClick(text, borderPane));
        return button;
    }

    // Handle menu button click event (change content in the center area)
    private void onMenuButtonClick(String section, BorderPane borderPane) {
        // Create content dynamically based on the button click
        StackPane contentArea = (StackPane) borderPane.getCenter();
        Label content = new Label("You selected: " + section);
        content.setStyle("-fx-font-size: 20px; -fx-text-fill: #2C3E50;");

        // Clear previous content and add new
        contentArea.getChildren().clear();
        contentArea.getChildren().add(content);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
