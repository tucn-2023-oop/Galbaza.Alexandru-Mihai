package com.example.farm2.DAO;

import com.example.farm2.DbConnector;
import com.example.farm2.Classes.Farmer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmerDAO {
    private Connection connection;

    public FarmerDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Farmer authenticateUser(String username, String password) {
        String query = "SELECT * FROM Farmers WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToFarmer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Authentication failed
    }
    public Farmer getFarmerById(int farmerId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Farmers WHERE farmer_id = ?")) {
            statement.setInt(1, farmerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToFarmer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Farmer> getAllFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Farmers");

            while (resultSet.next()) {
                farmers.add(mapResultSetToFarmer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmers;
    }

    public void addFarmer(Farmer farmer) { //might be used later
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Farmers (first_name, last_name, contact_number, address) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, farmer.getFirstName());
            statement.setString(2, farmer.getLastName());
            statement.setString(3, farmer.getContactNumber());
            statement.setString(4, farmer.getAddress());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add more CRUD methods if needed

    private Farmer mapResultSetToFarmer(ResultSet resultSet) throws SQLException {
        Farmer farmer = new Farmer();
        farmer.setFarmerId(resultSet.getInt("farmer_id"));
        farmer.setFirstName(resultSet.getString("first_name"));
        farmer.setLastName(resultSet.getString("last_name"));
        farmer.setContactNumber(resultSet.getString("contact_number"));
        farmer.setAddress(resultSet.getString("address"));
        return farmer;
    }
}
