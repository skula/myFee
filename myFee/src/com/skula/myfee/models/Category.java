package com.skula.myfee.models;

public class Category {
	private String id;
	private String label;
	private String color;
	private String budget;
	private String total;
	private String percent;
	
	public Category() {
	}

	public Category(String id, String label, String color, String budget) {
		this.id = id;
		this.label = label;
		this.color = color;
		this.budget = budget;
	}
	
	public Category(String id, String label, String color, String budget, String total, String percent) {
		this.id = id;
		this.label = label;
		this.color = color;
		this.budget = budget;
		this.total = total;
		this.percent = percent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
}
