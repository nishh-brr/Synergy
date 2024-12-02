package com.myapp.ui;

import com.myapp.controllers.MainApp;
import javafx.stage.Stage;

public class Dashboard1 {

    private MainApp mainApp;

    public Dashboard1(MainApp mainApp, boolean isLoggedIn) {
        this.mainApp = mainApp;
    }

    public void showDashboard(Stage primaryStage) {
        // Code to display the dashboard UI
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }
}
