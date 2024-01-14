package com.example.farm2.Controllers;// FarmerLoginController.java
import com.example.farm2.DAO.FarmerDAO;
import com.example.farm2.Classes.Farmer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static java.lang.System.exit;

public class MainWindowController {
    private Stage primaryStage;
    private Farmer loggedInFarmer;

    @FXML
    private void handleLogoutButton() {
        // Prompt the user if they really want to log out
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Log out and go back to the login window
            openLoginWindow();
        }
    }

    @FXML
    private void handleBuyLivestockButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/buy_livestock_window.fxml"));
            Scene scene = new Scene(loader.load());

            Stage buyLivestockStage = new Stage();
            buyLivestockStage.setTitle("Buy Livestock");
            buyLivestockStage.initModality(Modality.WINDOW_MODAL);
            buyLivestockStage.initOwner(primaryStage);
            buyLivestockStage.setScene(scene);

            BuyLivestockController buyLivestockController = loader.getController();
            buyLivestockController.setLoggedInFarmer(loggedInFarmer);

            buyLivestockStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setLoggedInFarmer(Farmer loggedInFarmer) {
        this.loggedInFarmer = loggedInFarmer;
    }

    private void openLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/farmer_login.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setScene(scene);

            FarmerLoginController farmerLoginController = loader.getController();
            farmerLoginController.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLeaderboardsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/leaderboards_window.fxml"));
            Scene scene = new Scene(loader.load());

            Stage leaderboardsStage = new Stage();
            leaderboardsStage.setTitle("Leaderboards");
            leaderboardsStage.initModality(Modality.WINDOW_MODAL);
            leaderboardsStage.initOwner(primaryStage);
            leaderboardsStage.setScene(scene);

            LeaderboardsController leaderboardsController = loader.getController();
            leaderboardsController.setPrimaryStage(leaderboardsStage);

            leaderboardsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDisplayLivestockButton(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the Livestock List window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/livestock_list_window.fxml"));
            Parent root = loader.load();

            // Create the controller for the Livestock List window
            LivestockListController livestockListController = loader.getController();
            livestockListController.setLoggedInFarmer(loggedInFarmer);

            livestockListController.loadLivestockData();

            // Set up the stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Livestock List");
            stage.setScene(new Scene(root));

            // Show the stage
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading the window
        }
    }

    @FXML
    private void handleGoPremiumButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/premium_advertisement.fxml"));
            Parent root = loader.load();

            // Create the controller for the Premium Advertisement window
            PremiumAdvertisementController premiumAdvertisementController = loader.getController();

            // Set up the stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Go Premium");
            stage.setScene(new Scene(root));

            // Show the stage
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading the window
        }
    }
}
