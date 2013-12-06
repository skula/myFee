package com.skula.myfee.models;

import java.util.List;

public class RingGraphic {
	private String title;
	private List<Category> categories;
	
	public RingGraphic(){
	}
	
	public RingGraphic(String title, List<Category> categories) {
		this.title = title;
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
