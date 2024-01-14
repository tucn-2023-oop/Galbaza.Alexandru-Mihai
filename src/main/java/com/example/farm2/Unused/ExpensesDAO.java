package com.example.farm2.Unused;

import com.example.farm2.DbConnector;
import com.example.farm2.Unused.Expenses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpensesDAO {
    private Connection connection;

    public ExpensesDAO() {
        try {
            connection = DbConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public Expenses getExpenseById(int expenseId) {
        Expenses expense = null;
        String query = "SELECT * FROM Expenses WHERE expense_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expenseId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    expense = mapResultSetToExpense(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expense;
    }

    public List<Expenses> getAllExpenses() {
        List<Expenses> expensesList = new ArrayList<>();
        String query = "SELECT * FROM Expenses";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Expenses expense = mapResultSetToExpense(resultSet);
                expensesList.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expensesList;
    }

    public void addExpense(Expenses expense) {
        String query = "INSERT INTO Expenses (farmer_id, expense_type, amount, expense_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expense.getFarmerId());
            statement.setString(2, expense.getExpenseType());
            statement.setBigDecimal(3, expense.getAmount());
            statement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateExpense(Expenses expense) {
        String query = "UPDATE Expenses SET farmer_id = ?, expense_type = ?, amount = ?, expense_date = ? WHERE expense_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expense.getFarmerId());
            statement.setString(2, expense.getExpenseType());
            statement.setBigDecimal(3, expense.getAmount());
            statement.setDate(4, java.sql.Date.valueOf(expense.getExpenseDate()));
            statement.setInt(5, expense.getExpenseId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteExpense(int expenseId) {
        String query = "DELETE FROM Expenses WHERE expense_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expenseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Expenses mapResultSetToExpense(ResultSet resultSet) throws SQLException {
        Expenses expense = new Expenses();
        expense.setExpenseId(resultSet.getInt("expense_id"));
        expense.setFarmerId(resultSet.getInt("farmer_id"));
        expense.setExpenseType(resultSet.getString("expense_type"));
        expense.setAmount(resultSet.getBigDecimal("amount"));
        expense.setExpenseDate(resultSet.getDate("expense_date").toLocalDate());
        return expense;
    }
}
