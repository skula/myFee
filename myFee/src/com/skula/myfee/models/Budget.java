package com.skula.myfee.models;

public class Budget {
	private String category;
	private String color;
	private String total;
	private String goal;
	private String difference;
	private String percent;
	
	public Budget() {
	}

	public Budget(String category, String color, String total, String goal,
			String difference, String percent) {
		this.category = category;
		this.color = color;
		this.total = total;
		this.goal = goal;
		this.difference = difference;
		this.percent = percent;
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

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
}
