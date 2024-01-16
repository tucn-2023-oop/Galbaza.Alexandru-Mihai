package com.example.farm2.Controllers;

import com.example.farm2.Classes.Farmer;
import com.example.farm2.Classes.Livestock;
import com.example.farm2.DAO.LivestockDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class LivestockListController {

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TableView<Livestock> livestockTableView;

    @FXML
    private TableColumn<Livestock, String> nameColumn;

    @FXML
    private TableColumn<Livestock, String> typeColumn;

    @FXML
    private TableColumn<Livestock, Integer> quantityColumn;

    private Farmer loggedInFarmer;
    private LivestockDAO livestockDAO;

    public LivestockListController() {
        this.livestockDAO = new LivestockDAO();
    }

    @FXML
    public void initialize() {
        // Set up the cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("livestockName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Populate the typeComboBox with distinct types
        List<String> distinctTypes = new ArrayList<>();
        distinctTypes.add("No filter active");
        distinctTypes.add("Livestock");
        distinctTypes.add("Poultry");
        distinctTypes.add("Equines");
        distinctTypes.add("Misc");
        typeComboBox.setItems(FXCollections.observableArrayList(distinctTypes));
    }

    @FXML
    public void handleTypeSelection() {
        // Handle the type selection event
        String selectedType = typeComboBox.getValue();

        // Filter the table based on the selected type
        ObservableList<Livestock> filteredLivestock = FXCollections.observableArrayList();
        List<Livestock> allLivestock = livestockDAO.getAllLivestockByFarmerId(loggedInFarmer.getFarmerId());

        if (selectedType == null || selectedType.equals("No filter active") || selectedType.isEmpty()) {
            // If no type selected, show all livestock
            filteredLivestock.addAll(allLivestock);
        } else {
            // Filter livestock based on selected type
            for (Livestock livestock : allLivestock) {
                if (livestock.getType().equalsIgnoreCase(selectedType)) {
                    filteredLivestock.add(livestock);
                }
            }
        }

        livestockTableView.setItems(filteredLivestock);
    }
    public void loadLivestockData() {
        ObservableList<Livestock> livestockList = FXCollections.observableArrayList();

        // Use LivestockDAO.getAllLivestockByFarmerId() to get the livestock of the logged-in farmer
        List<Livestock> farmerLivestock = livestockDAO.getAllLivestockByFarmerId(loggedInFarmer.getFarmerId());

        livestockList.addAll(farmerLivestock);

        livestockTableView.setItems(livestockList);
    }
    public void setLoggedInFarmer(Farmer loggedInFarmer) {
        this.loggedInFarmer = loggedInFarmer;
    }
}
