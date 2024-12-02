import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class home extends Application {

    private StackPane contentArea; // Central content area for switching panels

    @Override
    public void start(Stage stage) {
        // Main layout
        BorderPane root = new BorderPane();

        // Left Sidebar for Navigation
        VBox leftSidebar = createSidebar();
        root.setLeft(leftSidebar);

        // Top-Right Order History Section
        HBox topRight = createOrderHistorySection();
        root.setTop(topRight);

        // Center Content Area (User/Admin Panels)
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));
        contentArea.getChildren().add(new Label("Welcome to the Inventory System!"));
        root.setCenter(contentArea);

        // Set Scene and Show
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Modern Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates the left sidebar for navigation.
     */
    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(15));
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle("-fx-background-color: #2C3E50;");

        // Sidebar Buttons
        Button userPanelButton = new Button("User Panel");
        Button adminPanelButton = new Button("Admin Panel");

        // Modern Button Styles
        styleSidebarButton(userPanelButton);
        styleSidebarButton(adminPanelButton);

        // Event Handlers for Navigation
        userPanelButton.setOnAction(e -> showUserPanel());
        adminPanelButton.setOnAction(e -> showAdminPanel());

        sidebar.getChildren().addAll(userPanelButton, adminPanelButton);
        return sidebar;
    }

    /**
     * Styles the sidebar buttons.
     */
    private void styleSidebarButton(Button button) {
        button.setPrefWidth(150);
        button.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #1ABC9C; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #34495E; -fx-text-fill: white;"));
    }

    /**
     * Creates the top-right Order History section.
     */
    private HBox createOrderHistorySection() {
        HBox orderHistory = new HBox();
        orderHistory.setPadding(new Insets(10));
        orderHistory.setAlignment(Pos.CENTER_RIGHT);

        Label orderHistoryLabel = new Label("Order History");
        orderHistoryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        orderHistory.getChildren().add(orderHistoryLabel);
        return orderHistory;
    }

    /**
     * Displays the User Panel in the content area.
     */
    private void showUserPanel() {
        VBox userPanel = new VBox(15);
        userPanel.setPadding(new Insets(20));
        userPanel.setStyle("-fx-background-color: #ECF0F1;");

        Label dashboardLabel = new Label("User Dashboard");
        dashboardLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search products...");
        searchField.setStyle("-fx-font-size: 14px;");

        // Product List (Placeholder TableView)
        TableView<String> productTable = new TableView<>();
        productTable.setStyle("-fx-background-color: white;");

        // Buttons
        Button profileButton = new Button("Manage Profile");
        Button cartButton = new Button("View Cart");
        profileButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        cartButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");

        userPanel.getChildren().addAll(dashboardLabel, searchField, productTable, profileButton, cartButton);
        contentArea.getChildren().setAll(userPanel);
    }

    /**
     * Displays the Admin Panel in the content area.
     */
    private void showAdminPanel() {
        VBox adminPanel = new VBox(15);
        adminPanel.setPadding(new Insets(20));
        adminPanel.setStyle("-fx-background-color: #ECF0F1;");

        Label adminDashboardLabel = new Label("Admin Dashboard");
        adminDashboardLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Labels for Admin Features
        Button manageUsers = new Button("Manage Users");
        manageUsers.setStyle("-fx-background-color: #1260CC; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 15px;");
        
        
        Button manageInventory = new Button("Manage Inventory");
        manageInventory.setStyle("-fx-background-color: #1260CC; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 15px;");
        
        

        adminPanel.getChildren().addAll(adminDashboardLabel, manageUsers, manageInventory);
        contentArea.getChildren().setAll(adminPanel);
    }

    public static void main(String[] args) {
        launch(args);
    }

    VBox createhome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}