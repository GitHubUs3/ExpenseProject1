package com.skillstorm.beans;

import java.io.Serializable;

public class Expense implements Serializable {
	private int expenseId;
	private String name;
	private String reason;
	private String notes;
	private int status; 
	
	
	public Expense() {
		super();
	}

	public Expense(int expenseId, String name, String reason, String notes, int status) {
		super();
		this.expenseId = expenseId;
		this.name = name;
		this.reason = reason;
		this.notes = notes;
		this.status = status;
	}

	public Expense(String name, int status) {
		super();
		this.name = name;
		this.status = status;
	}

	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", name=" + name + ", reason=" + reason + ", notes=" + notes
				+ ", status=" + status + "]";
	}
}
