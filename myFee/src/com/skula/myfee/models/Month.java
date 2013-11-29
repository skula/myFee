package com.skula.myfee.models;

public class Month {
	private String label;
	private String total;

	public Month() {
	}

	public Month(String label, String total) {
		this.label = label;
		this.total = total;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
