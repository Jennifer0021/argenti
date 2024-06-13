package controllers;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Login {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
