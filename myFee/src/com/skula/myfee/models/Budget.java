package com.skula.myfee.models;

public class Budget {
	private String category;
	private String color;
	private String total;
	private String goal;
	
	public Budget() {
	}

	public Budget(String category, String color, String total, String goal) {
		this.category = category;
		this.color = color;
		this.total = total;
		this.goal = goal;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
}
