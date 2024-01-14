package com.example.farm2.Controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PremiumAdvertisementController {



    @FXML
    private void handleClaimNowButton(ActionEvent event) {
        // Close the premium advertisement window
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();

        // Open the provided link in the default browser
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
