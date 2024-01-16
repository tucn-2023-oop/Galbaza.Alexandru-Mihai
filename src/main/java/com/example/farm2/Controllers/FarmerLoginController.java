package com.example.farm2.Controllers;// FarmerLoginController.java
import com.example.farm2.DAO.FarmerDAO;
import com.example.farm2.Classes.Farmer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class FarmerLoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML//this one handles a button! Fancy!
    private void handleLoginButton() {
        String username = usernameField.getText();//here it does the logic
        String password = passwordField.getText();

        FarmerDAO farmersDAO = new FarmerDAO();
        Farmer authenticatedFarmer = farmersDAO.authenticateUser(username, password);

        if (authenticatedFarmer != null) {
            openMainWindow(authenticatedFarmer);//here it opens a new window!
        } else {
            openErrorWindow("Invalid username or password.");
        }
    }

    private void openErrorWindow(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/error.fxml"));
            Scene scene = new Scene(loader.load());

            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.setTitle("Error");
            errorStage.setScene(scene);

            ErrorController errorController = loader.getController();
            errorController.setErrorMessage(errorMessage);

            errorStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    private void openMainWindow(Farmer farmer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/main_window.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setScene(scene);

            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setLoggedInFarmer(farmer);
            mainWindowController.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }
}