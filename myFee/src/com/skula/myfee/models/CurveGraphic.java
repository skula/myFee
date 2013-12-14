package com.skula.myfee.models;

import java.util.List;
import java.util.Map;

public class CurveGraphic {
	private String title;
	private double yMax;
	private int count;
	private Map<String, List<TimeUnit>> timeUnits;
	
	public CurveGraphic(){
	}
	
	public CurveGraphic(String title, Map<String, List<TimeUnit>> timeUnits) {
		this.title = title;
		this.timeUnits = timeUnits;
		this.yMax = 0.0;
		for(String c: timeUnits.keySet()){
			this.count = timeUnits.get(c).size();
			for(TimeUnit tu : timeUnits.get(c)){
				if(yMax<tu.getValue()){
					yMax=tu.getValue();
				}
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getyMax() {
		return yMax;
	}

	public void setyMax(double yMax) {
		this.yMax = yMax;
	}

	public Map<String, List<TimeUnit>> getTimeUnits() {
		return timeUnits;
	}

	public void setTimeUnits(Map<String, List<TimeUnit>> timeUnits) {
		
		for(String c: timeUnits.keySet()){
			this.count = timeUnits.get(c).size();
			for(TimeUnit tu : timeUnits.get(c)){
				if(yMax<tu.getValue()){
					yMax=tu.getValue();
				}
			}
		}
		this.timeUnits = timeUnits;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
