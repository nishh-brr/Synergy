import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SignUpPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a VBox layout for the sign-up form
        VBox signUpForm = new VBox();
        signUpForm.setAlignment(Pos.CENTER);
        signUpForm.setSpacing(10);
        signUpForm.setPadding(new Insets(20));

        // Create labels and fields for the sign-up form
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // Create Sign Up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px;");

        // Handle Sign Up Button Action
        signUpButton.setOnAction(e -> {
            // For now, we simply transition to the dashboard after clicking Sign Up
            switchToDashboard(primaryStage);
        });

        // Add components to the form
        signUpForm.getChildren().addAll(new Label("Sign Up"), nameField, emailField, passwordField, signUpButton);

        // Set up the scene for the sign-up page
        Scene signUpScene = new Scene(signUpForm, 400, 300);
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(signUpScene);
        primaryStage.show();
    }

    // Switch to the Dashboard after successful sign up
    private void switchToDashboard(Stage primaryStage) {
        Dashboard dashboard = new Dashboard();
        dashboard.start(primaryStage);  // Reuse the same Stage for the dashboard
    }

    public static void main(String[] args) {
        launch(args);
    }
}
