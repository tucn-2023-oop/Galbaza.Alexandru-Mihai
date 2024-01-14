package com.example.farm2.Unused;

import com.example.farm2.Unused.Crop;
import com.example.farm2.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CropsDAO {
    private Connection connection;

    public CropsDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Crop getCropById(int cropId) {
        Crop crop = null;
        String query = "SELECT * FROM Crops WHERE crop_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cropId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                crop = mapResultSetToCrop(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crop;
    }

    public List<Crop> getAllCrops() {
        List<Crop> crops = new ArrayList<>();
        String query = "SELECT * FROM Crops";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Crop crop = mapResultSetToCrop(resultSet);
                crops.add(crop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crops;
    }

    public void addCrop(Crop crop) {
        String query = "INSERT INTO Crops (crop_name, planting_date, harvest_date, quantity, farmer_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, crop.getCropName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(crop.getPlantingDate()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(crop.getHarvestDate()));
            preparedStatement.setInt(4, crop.getQuantity());
            preparedStatement.setInt(5, crop.getFarmerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCrop(Crop crop) {
        String query = "UPDATE Crops SET crop_name = ?, planting_date = ?, harvest_date = ?, quantity = ?, farmer_id = ? WHERE crop_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, crop.getCropName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(crop.getPlantingDate()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(crop.getHarvestDate()));
            preparedStatement.setInt(4, crop.getQuantity());
            preparedStatement.setInt(5, crop.getFarmerId());
            preparedStatement.setInt(6, crop.getCropId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCrop(int cropId) {
        String query = "DELETE FROM Crops WHERE crop_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cropId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Crop mapResultSetToCrop(ResultSet resultSet) throws SQLException {
        Crop crop = new Crop();
        crop.setCropId(resultSet.getInt("crop_id"));
        crop.setCropName(resultSet.getString("crop_name"));
        crop.setPlantingDate(resultSet.getDate("planting_date").toLocalDate());
        crop.setHarvestDate(resultSet.getDate("harvest_date").toLocalDate());
        crop.setQuantity(resultSet.getInt("quantity"));
        crop.setFarmerId(resultSet.getInt("farmer_id"));
        return crop;
    }
}
