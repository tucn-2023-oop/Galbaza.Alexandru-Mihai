package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    private Connection connection;

    public IncomeDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Income getIncomeById(int incomeId) {
        Income income = null;
        String query = "SELECT * FROM Income WHERE income_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, incomeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                income = mapResultSetToIncome(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return income;
    }

    public List<Income> getAllIncome() {
        List<Income> incomes = new ArrayList<>();
        String query = "SELECT * FROM Income";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Income income = mapResultSetToIncome(resultSet);
                incomes.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    public void addIncome(Income income) {
        String query = "INSERT INTO Income (farmer_id, income_type, amount, income_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, income.getFarmerId());
            preparedStatement.setString(2, income.getIncomeType());
            preparedStatement.setBigDecimal(3, income.getAmount());
            preparedStatement.setDate(4, java.sql.Date.valueOf(income.getIncomeDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIncome(Income income) {
        String query = "UPDATE Income SET farmer_id = ?, income_type = ?, amount = ?, income_date = ? WHERE income_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, income.getFarmerId());
            preparedStatement.setString(2, income.getIncomeType());
            preparedStatement.setBigDecimal(3, income.getAmount());
            preparedStatement.setDate(4, java.sql.Date.valueOf(income.getIncomeDate()));
            preparedStatement.setInt(5, income.getIncomeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIncome(int incomeId) {
        String query = "DELETE FROM Income WHERE income_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, incomeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Income mapResultSetToIncome(ResultSet resultSet) throws SQLException {
        Income income = new Income();
        income.setIncomeId(resultSet.getInt("income_id"));
        income.setFarmerId(resultSet.getInt("farmer_id"));
        income.setIncomeType(resultSet.getString("income_type"));
        income.setAmount(resultSet.getBigDecimal("amount"));
        income.setIncomeDate(resultSet.getDate("income_date").toLocalDate());
        return income;
    }
}
