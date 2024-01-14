package com.example.farm2.DAO;

import com.example.farm2.Classes.FarmerLeaderboardEntry;
import com.example.farm2.DbConnector;
import com.example.farm2.Classes.Livestock;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivestockDAO {
    private Connection connection;

    public LivestockDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Livestock getLivestockByNameTypeAndFarmerId(String livestockName, String type, int farmerId) {
        Livestock livestock = null;
        String query = "SELECT * FROM Livestock WHERE livestock_name = ? AND type = ? AND farmer_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, livestockName);
            preparedStatement.setString(2, type);
            preparedStatement.setInt(3, farmerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                livestock = mapResultSetToLivestock(resultSet);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return livestock;
    }

    public List<Livestock> getAllLivestockByFarmerId(int farmerId) {
        List<Livestock> livestockList = new ArrayList<>();
        String query = "SELECT * FROM Livestock WHERE farmer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, farmerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Livestock livestock = mapResultSetToLivestock(resultSet);
                livestockList.add(livestock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livestockList;
    }

    public void addLivestock(Livestock livestock) {
        String query = "INSERT INTO Livestock (livestock_name, type, quantity, farmer_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, livestock.getLivestockName());
            preparedStatement.setString(2, livestock.getType());
            preparedStatement.setInt(3, livestock.getQuantity());
            preparedStatement.setInt(4, livestock.getFarmerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLivestock(int livestockId) {
        String query = "DELETE FROM Livestock WHERE livestock_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, livestockId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Livestock mapResultSetToLivestock(ResultSet resultSet) throws SQLException {
        Livestock livestock = new Livestock();
        livestock.setLivestockId(resultSet.getInt("livestock_id"));
        livestock.setLivestockName(resultSet.getString("livestock_name"));
        livestock.setType(resultSet.getString("type"));
        livestock.setQuantity(resultSet.getInt("quantity"));
        livestock.setFarmerId(resultSet.getInt("farmer_id"));
        return livestock;
    }

    public void addOrUpdateLivestock(Livestock livestock) {
        String query = "SELECT * FROM Livestock WHERE livestock_name = ? AND type = ? AND farmer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, livestock.getLivestockName());
            preparedStatement.setString(2, livestock.getType());
            preparedStatement.setInt(3, livestock.getFarmerId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Livestock with the same name, type, and farmer_id already exists
                int existingQuantity = resultSet.getInt("quantity");
                int newQuantity = existingQuantity + livestock.getQuantity();

                updateLivestockQuantity(livestock.getLivestockName(), livestock.getType(), newQuantity, livestock.getFarmerId());
            } else {
                // Livestock doesn't exist, add a new entry
                addLivestock(livestock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sellLivestock(Livestock livestock, int soldQuantity) {
        String query = "SELECT * FROM Livestock WHERE livestock_name = ? AND type = ? AND farmer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, livestock.getLivestockName());
            preparedStatement.setString(2, livestock.getType());
            preparedStatement.setInt(3, livestock.getFarmerId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Livestock with the same name, type, and farmer_id exists
                int existingQuantity = resultSet.getInt("quantity");
                int newQuantity = existingQuantity - soldQuantity;

                if (newQuantity >= 0) {
                    updateLivestockQuantity(livestock.getLivestockName(), livestock.getType(), newQuantity, livestock.getFarmerId());
                } else {
                    // Handle error, attempting to sell more than available
                    showErrorAlert("Error", "Attempting to sell more livestock than available.");
                }
            } else {
                // Livestock doesn't exist, handle error
                showErrorAlert("Error", "Attempting to sell non-existent livestock.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "An error occurred while selling livestock.");
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void updateLivestockQuantity(String livestockName, String type, int quantity, int farmerId) {
        String updateQuery = "UPDATE Livestock SET quantity = ? WHERE livestock_name = ? AND type = ? AND farmer_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, quantity);
            updateStatement.setString(2, livestockName);
            updateStatement.setString(3, type);
            updateStatement.setInt(4, farmerId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<FarmerLeaderboardEntry> getLeaderboardData() {
        List<FarmerLeaderboardEntry> leaderboardData = new ArrayList<>();
        String query = "SELECT f.first_name, " +
                "SUM(CASE WHEN l.type='Livestock' THEN l.quantity ELSE 0 END) AS livestock_count, " +
                "SUM(CASE WHEN l.type='Poultry' THEN l.quantity ELSE 0 END) AS poultry_count, " +
                "SUM(CASE WHEN l.type='Equines' THEN l.quantity ELSE 0 END) AS equines_count, " +
                "SUM(CASE WHEN l.type='Misc' THEN l.quantity ELSE 0 END) AS misc_count, " +
                "SUM(l.quantity) AS total_count " +
                "FROM Livestock l " +
                "JOIN Farmers f ON l.farmer_id = f.farmer_id " +
                "GROUP BY l.farmer_id, f.first_name " +
                "ORDER BY f.first_name";


        try (Connection connection = DbConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FarmerLeaderboardEntry entry = new FarmerLeaderboardEntry();
                entry.setFarmerName(resultSet.getString("first_name"));
                entry.setLivestockCount(resultSet.getInt("livestock_count"));
                entry.setPoultryCount(resultSet.getInt("poultry_count"));
                entry.setEquinesCount(resultSet.getInt("equines_count"));
                entry.setMiscCount(resultSet.getInt("misc_count"));
                entry.setTotalCount(resultSet.getInt("total_count"));
                leaderboardData.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leaderboardData;
    }

}