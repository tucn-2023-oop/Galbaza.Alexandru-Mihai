package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {
    private Connection connection;

    public EquipmentDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Equipment getEquipmentById(int equipmentId) {
        Equipment equipment = null;
        String query = "SELECT * FROM Equipment WHERE equipment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, equipmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                equipment = mapResultSetToEquipment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipment;
    }

    public List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        String query = "SELECT * FROM Equipment";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Equipment equipment = mapResultSetToEquipment(resultSet);
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    public void addEquipment(Equipment equipment) {
        String query = "INSERT INTO Equipment (role, farmer_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, equipment.getRole());
            preparedStatement.setInt(2, equipment.getFarmerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEquipment(Equipment equipment) {
        String query = "UPDATE Equipment SET role = ?, farmer_id = ? WHERE equipment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, equipment.getRole());
            preparedStatement.setInt(2, equipment.getFarmerId());
            preparedStatement.setInt(3, equipment.getEquipmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEquipment(int equipmentId) {
        String query = "DELETE FROM Equipment WHERE equipment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Equipment mapResultSetToEquipment(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(resultSet.getInt("equipment_id"));
        equipment.setRole(resultSet.getString("role"));
        equipment.setFarmerId(resultSet.getInt("farmer_id"));
        return equipment;
    }
}
