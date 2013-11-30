package com.skula.myfee.models;

public class Fee {
	private String id;
	private String label;
	private String amount;
	private String category;
	private String date;
	private String color;

	public Fee() {
	}

	public Fee(String id, String label, String amount, String category, String date) {
		this.label = label;
		this.amount = amount;
		this.category = category;
		this.date = date;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
