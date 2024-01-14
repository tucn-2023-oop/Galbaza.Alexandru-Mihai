package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.ManualEquipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManualEquipmentDAO {
    private Connection connection;

    public ManualEquipmentDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ManualEquipment getManualEquipmentById(int manualId) {
        ManualEquipment manualEquipment = null;
        String query = "SELECT * FROM Manual_Equipment WHERE manual_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, manualId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                manualEquipment = mapResultSetToManualEquipment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manualEquipment;
    }

    public List<ManualEquipment> getAllManualEquipment() {
        List<ManualEquipment> manualEquipments = new ArrayList<>();
        String query = "SELECT * FROM Manual_Equipment";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ManualEquipment manualEquipment = mapResultSetToManualEquipment(resultSet);
                manualEquipments.add(manualEquipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manualEquipments;
    }

    public void addManualEquipment(ManualEquipment manualEquipment) {
        String query = "INSERT INTO Manual_Equipment (equipment_id, equipment_name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, manualEquipment.getEquipmentId());
            preparedStatement.setString(2, manualEquipment.getEquipmentName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateManualEquipment(ManualEquipment manualEquipment) {
        String query = "UPDATE Manual_Equipment SET equipment_name = ? WHERE manual_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, manualEquipment.getEquipmentName());
            preparedStatement.setInt(2, manualEquipment.getManualId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteManualEquipment(int manualId) {
        String query = "DELETE FROM Manual_Equipment WHERE manual_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, manualId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ManualEquipment mapResultSetToManualEquipment(ResultSet resultSet) throws SQLException {
        ManualEquipment manualEquipment = new ManualEquipment();
        manualEquipment.setManualId(resultSet.getInt("manual_id"));
        manualEquipment.setEquipmentId(resultSet.getInt("equipment_id"));
        manualEquipment.setEquipmentName(resultSet.getString("equipment_name"));
        // Add more mappings if needed for specific fields
        return manualEquipment;
    }
}
