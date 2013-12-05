package com.skula.myfee.models;

public class TimeUnit {
	private int index;
	private double value;
	private String color;
	
	public TimeUnit() {
	}

	public TimeUnit(int index, double value, String color) {
		this.index = index;
		this.value = value;
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
