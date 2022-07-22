package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Expense;
import com.skillstorm.jdbc.ExpenseDAO;

@WebServlet(urlPatterns = "/list-form")
public class ListFormServlet extends HttpServlet {
	
	private ExpenseDAO expense;
	
	@Override
	public void init() throws ServletException {
		try {
			expense = new ExpenseDAO();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// read
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		try {
			resp.getWriter().print(new ObjectMapper().writeValueAsString(expense.read()));
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	// update
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		InputStream requestBody = req.getInputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		Expense test = objectMapper.readValue(requestBody, Expense.class);	

		if(id != null) {
			int param = Integer.parseInt(id);
			try {
				expense.update(test, param);
				System.out.println("update");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		else {
			try {
				expense.create(test);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		System.out.println(test);	

		resp.setStatus(201); // resp is committed

	}
	
	// Delete
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int param = Integer.parseInt(id);
		if(id != null) {
			try {
				expense.delete(param);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
