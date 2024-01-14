package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.Planting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlantingDAO {
    private Connection connection;

    public PlantingDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlanting(Planting planting) {
        String sql = "INSERT INTO Planting (crop_id, farmer_id, acreage, date_planted) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planting.getCropId());
            statement.setInt(2, planting.getFarmerId());
            statement.setInt(3, planting.getAcreage());
            statement.setDate(4, java.sql.Date.valueOf(planting.getDatePlanted()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlanting(Planting planting) {
        String sql = "UPDATE Planting SET crop_id = ?, farmer_id = ?, acreage = ?, date_planted = ? WHERE planting_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planting.getCropId());
            statement.setInt(2, planting.getFarmerId());
            statement.setInt(3, planting.getAcreage());
            statement.setDate(4, java.sql.Date.valueOf(planting.getDatePlanted()));
            statement.setInt(5, planting.getPlantingId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlanting(int plantingId) {
        String sql = "DELETE FROM Planting WHERE planting_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, plantingId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Planting getPlantingById(int plantingId) {
        String sql = "SELECT * FROM Planting WHERE planting_id = ?";
        Planting planting = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, plantingId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                planting = extractPlantingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planting;
    }

    public List<Planting> getAllPlantings() {
        String sql = "SELECT * FROM Planting";
        List<Planting> plantings = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Planting planting = extractPlantingFromResultSet(resultSet);
                plantings.add(planting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantings;
    }

    private Planting extractPlantingFromResultSet(ResultSet resultSet) throws SQLException {
        Planting planting = new Planting();
        planting.setPlantingId(resultSet.getInt("planting_id"));
        planting.setCropId(resultSet.getInt("crop_id"));
        planting.setFarmerId(resultSet.getInt("farmer_id"));
        planting.setAcreage(resultSet.getInt("acreage"));
        planting.setDatePlanted(resultSet.getDate("date_planted").toLocalDate());

        return planting;
    }
}
