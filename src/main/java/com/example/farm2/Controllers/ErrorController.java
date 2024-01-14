package com.example.farm2.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorController {
    @FXML
    private Label errorMessageLabel;

    public void setErrorMessage(String errorMessage) {
        errorMessageLabel.setText(errorMessage);
    }
}