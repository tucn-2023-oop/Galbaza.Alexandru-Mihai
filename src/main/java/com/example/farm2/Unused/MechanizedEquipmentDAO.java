package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.MechanizedEquipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanizedEquipmentDAO {
    private Connection connection;

    public MechanizedEquipmentDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MechanizedEquipment getMechanizedEquipmentById(int mechanizedId) {
        MechanizedEquipment mechanizedEquipment = null;
        String query = "SELECT * FROM Mechanized_Equipment WHERE mechanized_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, mechanizedId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                mechanizedEquipment = mapResultSetToMechanizedEquipment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mechanizedEquipment;
    }

    public List<MechanizedEquipment> getAllMechanizedEquipment() {
        List<MechanizedEquipment> mechanizedEquipments = new ArrayList<>();
        String query = "SELECT * FROM Mechanized_Equipment";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MechanizedEquipment mechanizedEquipment = mapResultSetToMechanizedEquipment(resultSet);
                mechanizedEquipments.add(mechanizedEquipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mechanizedEquipments;
    }

    public void addMechanizedEquipment(MechanizedEquipment mechanizedEquipment) {
        String query = "INSERT INTO Mechanized_Equipment (equipment_name, role, fuel_consumption, fuel_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mechanizedEquipment.getEquipmentName());
            preparedStatement.setString(2, mechanizedEquipment.getRole());
            preparedStatement.setBigDecimal(3, mechanizedEquipment.getFuelConsumption());
            preparedStatement.setString(4, mechanizedEquipment.getFuelType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMechanizedEquipment(MechanizedEquipment mechanizedEquipment) {
        String query = "UPDATE Mechanized_Equipment SET equipment_name = ?, role = ?, fuel_consumption = ?, fuel_type = ? WHERE mechanized_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mechanizedEquipment.getEquipmentName());
            preparedStatement.setString(2, mechanizedEquipment.getRole());
            preparedStatement.setBigDecimal(3, mechanizedEquipment.getFuelConsumption());
            preparedStatement.setString(4, mechanizedEquipment.getFuelType());
            preparedStatement.setInt(5, mechanizedEquipment.getMechanizedId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMechanizedEquipment(int mechanizedId) {
        String query = "DELETE FROM Mechanized_Equipment WHERE mechanized_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, mechanizedId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MechanizedEquipment mapResultSetToMechanizedEquipment(ResultSet resultSet) throws SQLException {
        MechanizedEquipment mechanizedEquipment = new MechanizedEquipment();
        mechanizedEquipment.setMechanizedId(resultSet.getInt("mechanized_id"));
        mechanizedEquipment.setEquipmentName(resultSet.getString("equipment_name"));
        mechanizedEquipment.setRole(resultSet.getString("role"));
        mechanizedEquipment.setFuelConsumption(resultSet.getBigDecimal("fuel_consumption"));
        mechanizedEquipment.setFuelType(resultSet.getString("fuel_type"));
        return mechanizedEquipment;
    }
}
