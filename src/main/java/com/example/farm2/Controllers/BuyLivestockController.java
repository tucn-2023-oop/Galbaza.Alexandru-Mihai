package com.example.farm2.Controllers;// BuyLivestockController.java

import com.example.farm2.Classes.Farmer;
import com.example.farm2.Classes.Livestock;
import com.example.farm2.DAO.LivestockDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class BuyLivestockController {
    private Farmer loggedInFarmer;

    @FXML
    private TextField livestockNameField;

    @FXML
    private TextField typeField;


    @FXML
    private TextField quantityField;

    @FXML
    private void handleBuyButton() {
        String livestockName = livestockNameField.getText();
        String type = typeField.getText();

        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if(quantity<=0) throw new NumberFormatException();
            Livestock newLivestock = new Livestock(
                    livestockName,
                    type,
                    quantity,
                    loggedInFarmer.getFarmerId()
            );

            LivestockDAO livestockDAO = new LivestockDAO();
            livestockDAO.addOrUpdateLivestock(newLivestock);

            showAlert("Success", "Livestock purchased successfully!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid quantity.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while buying livestock.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleSellButton() {
        try {
            String livestockName = livestockNameField.getText();
            String type = typeField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            if(quantity<=0) throw new NumberFormatException();

            LivestockDAO daoInstance=new LivestockDAO();
            Livestock existingLivestock = daoInstance.getLivestockByNameTypeAndFarmerId(livestockName, type, loggedInFarmer.getFarmerId());

            if (existingLivestock != null && existingLivestock.getQuantity() >= quantity) {
                // Use LivestockDAO.sellLivestock() method for selling
                daoInstance.sellLivestock(existingLivestock, quantity);
                showAlert("Success", "Livestock sold successfully.", Alert.AlertType.CONFIRMATION);
            } else {
                showAlert("Error", "Invalid quantity or livestock not found.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please check your entries.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setLoggedInFarmer(Farmer loggedInFarmer) {
        this.loggedInFarmer = loggedInFarmer;
    }
}
