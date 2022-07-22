package com.skillstorm.beans;

public class ReimburseStatus {
	private int id;
	private String status;
	
	public ReimburseStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public ReimburseStatus() {
		super();
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public void setStatus(String i) {
		this.status = i;
	}
	
	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "ReimburseStatus [id=" + id + ", status=" + status + "]";
	}
}
