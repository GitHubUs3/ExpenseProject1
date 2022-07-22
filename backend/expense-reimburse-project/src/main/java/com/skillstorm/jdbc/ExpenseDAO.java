package com.skillstorm.jdbc;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.beans.Expense;

public class ExpenseDAO {

	private Connection connection;
	LinkedList<Expense> data = new LinkedList<>();

	public ExpenseDAO() throws SQLException, ClassNotFoundException {
		// Establish a Connection to the database
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/expense";
		String username = "root";
		String password = "root";
		this.connection = DriverManager.getConnection(url, username, password);
	}

	// CRUD
	public Expense create(Expense expense) throws SQLException {
		String query = "insert into Expense(name, reason, notes, status) values (?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, expense.getName());
		statement.setString(2, expense.getReason());
		statement.setString(3, expense.getNotes());
		statement.setInt(4, expense.getStatus());
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();

		rs.next();
		int generatedId = rs.getInt(1);
		expense.setExpenseId(generatedId);

		return expense;
	}

	public List<Expense> read() throws SQLException {
		
		List<Expense> e = new ArrayList();
		Statement statement = connection.createStatement();
		String query = "select expenseId, name, reason, status from Expense";
		
		ResultSet res = statement.executeQuery(query);
		
		while(res.next()) {
			Expense expense = new Expense();
			int id = res.getInt("expenseId");
			String name = res.getString("name");
			String reason = res.getString("reason");
			int status = res.getInt("status");
			expense.setExpenseId(id);
			expense.setName(name);
			expense.setReason(reason);
			expense.setStatus(status);
			e.add(expense);
		}
		return e;
	}

	public boolean update(Expense expense, int id) throws SQLException {

		String query = "update expense SET name = ? , reason = ?, notes = ?, status = ? WHERE expenseId = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, expense.getName());
		statement.setString(2, expense.getReason());
		statement.setString(3, expense.getNotes());
		statement.setInt(4, expense.getStatus());
		statement.setInt(5, id);
		
		return statement.executeUpdate() == 1;
	}
	
	public boolean delete(int id) throws SQLException {
		String query = "delete from expense WHERE expenseId = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		
		return statement.executeUpdate() == 1;
	}

}
