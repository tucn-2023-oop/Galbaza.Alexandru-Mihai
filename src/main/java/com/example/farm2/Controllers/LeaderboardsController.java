package com.example.farm2.Controllers;

import com.example.farm2.Classes.FarmerLeaderboardEntry;
import com.example.farm2.DAO.LivestockDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;

public class LeaderboardsController {

    @FXML
    private TableView<FarmerLeaderboardEntry> leaderboardsTableView;

    @FXML
    private TableColumn<FarmerLeaderboardEntry, String> nameColumn;

    @FXML
    private TableColumn<FarmerLeaderboardEntry, Integer> livestockColumn;

    @FXML
    private TableColumn<FarmerLeaderboardEntry, Integer> poultryColumn;

    @FXML
    private TableColumn<FarmerLeaderboardEntry, Integer> equinesColumn;

    @FXML
    private TableColumn<FarmerLeaderboardEntry, Integer> miscColumn;

    @FXML
    private TableColumn<FarmerLeaderboardEntry, Integer> totalColumn;

    @FXML
    private Button nameSortButton;

    @FXML
    private Button livestockSortButton;

    @FXML
    private Button poultrySortButton;

    @FXML
    private Button equinesSortButton;

    @FXML
    private Button miscSortButton;

    @FXML
    private Button totalSortButton;

    private LivestockDAO livestockDAO;

    private ObservableList<FarmerLeaderboardEntry> leaderboardData;

    private Comparator<FarmerLeaderboardEntry> currentComparator;

    @FXML
    private Label crownLabel;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void markFarmerWithMostAnimals(ObservableList<FarmerLeaderboardEntry> leaderboardData) {
        FarmerLeaderboardEntry farmerWithMostAnimals = leaderboardData.stream()
                .max(Comparator.comparingInt(FarmerLeaderboardEntry::getTotalCount))
                .orElse(null);

        if (farmerWithMostAnimals != null) {
            farmerWithMostAnimals.setHasCrown(true);
            crownLabel.setText("👑 " + farmerWithMostAnimals.getFarmerName());  // Display the crown next to the name
        }
    }

    public LeaderboardsController() {
        this.livestockDAO = new LivestockDAO();
        this.currentComparator = Comparator.comparing(FarmerLeaderboardEntry::getFarmerName);
    }

    @FXML
    public void initialize() {
        configureTableColumns();
        loadLeaderboardData();
    }

    private void configureTableColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("farmerName"));
        livestockColumn.setCellValueFactory(new PropertyValueFactory<>("livestockCount"));
        poultryColumn.setCellValueFactory(new PropertyValueFactory<>("poultryCount"));
        equinesColumn.setCellValueFactory(new PropertyValueFactory<>("equinesCount"));
        miscColumn.setCellValueFactory(new PropertyValueFactory<>("miscCount"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalCount"));
    }

    private void loadLeaderboardData() {
        leaderboardData = FXCollections.observableArrayList();

        List<FarmerLeaderboardEntry> entries = livestockDAO.getLeaderboardData();
        leaderboardData.addAll(entries);

        markFarmerWithMostAnimals(leaderboardData);

        leaderboardsTableView.setItems(leaderboardData);
    }

    @FXML
    private void sortByName() {
        sortData(Comparator.comparing(FarmerLeaderboardEntry::getFarmerName));
    }

    @FXML
    private void sortByLivestock() {
        sortData(Comparator.comparingInt(FarmerLeaderboardEntry::getLivestockCount));
    }

    @FXML
    private void sortByPoultry() {
        sortData(Comparator.comparingInt(FarmerLeaderboardEntry::getPoultryCount));
    }

    @FXML
    private void sortByEquines() {
        sortData(Comparator.comparingInt(FarmerLeaderboardEntry::getEquinesCount));
    }

    @FXML
    private void sortByMisc() {
        sortData(Comparator.comparingInt(FarmerLeaderboardEntry::getMiscCount));
    }

    @FXML
    private void sortByTotal() {
        sortData(Comparator.comparingInt(FarmerLeaderboardEntry::getTotalCount));
    }

    private void sortData(Comparator<FarmerLeaderboardEntry> comparator) {
        if (currentComparator == comparator.reversed()) {
            // If already reversed, sort ascending
            currentComparator = comparator;
        } else {
            // If not reversed, sort descending
            currentComparator = comparator.reversed();
        }

        leaderboardData.sort(currentComparator);
    }
}
