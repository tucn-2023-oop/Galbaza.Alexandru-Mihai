package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.Harvest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HarvestDAO {
    private Connection connection;

    public HarvestDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Harvest getHarvestById(int harvestId) {
        Harvest harvest = null;
        String query = "SELECT * FROM Harvest WHERE harvest_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, harvestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                harvest = mapResultSetToHarvest(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harvest;
    }

    public List<Harvest> getAllHarvests() {
        List<Harvest> harvests = new ArrayList<>();
        String query = "SELECT * FROM Harvest";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Harvest harvest = mapResultSetToHarvest(resultSet);
                harvests.add(harvest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harvests;
    }

    public void addHarvest(Harvest harvest) {
        String query = "INSERT INTO Harvest (crop_id, farmer_id, quantity_harvested, date_harvested) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, harvest.getCropId());
            preparedStatement.setInt(2, harvest.getFarmerId());
            preparedStatement.setInt(3, harvest.getQuantityHarvested());
            preparedStatement.setObject(4, harvest.getDateHarvested());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHarvest(Harvest harvest) {
        String query = "UPDATE Harvest SET crop_id = ?, farmer_id = ?, quantity_harvested = ?, date_harvested = ? WHERE harvest_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, harvest.getCropId());
            preparedStatement.setInt(2, harvest.getFarmerId());
            preparedStatement.setInt(3, harvest.getQuantityHarvested());
            preparedStatement.setObject(4, harvest.getDateHarvested());
            preparedStatement.setInt(5, harvest.getHarvestId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHarvest(int harvestId) {
        String query = "DELETE FROM Harvest WHERE harvest_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, harvestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Harvest mapResultSetToHarvest(ResultSet resultSet) throws SQLException {
        Harvest harvest = new Harvest();
        harvest.setHarvestId(resultSet.getInt("harvest_id"));
        harvest.setCropId(resultSet.getInt("crop_id"));
        harvest.setFarmerId(resultSet.getInt("farmer_id"));
        harvest.setQuantityHarvested(resultSet.getInt("quantity_harvested"));
        harvest.setDateHarvested(resultSet.getDate("date_harvested").toLocalDate());
        return harvest;
    }
}
