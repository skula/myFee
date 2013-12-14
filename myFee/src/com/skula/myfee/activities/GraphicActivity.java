package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.myfee.activities.dialogs.GraphicDialog;
import com.skula.myfee.activities.views.GraphicView;
import com.skula.myfee.models.CurveGraphic;
import com.skula.myfee.services.DatabaseService;



public class GraphicActivity extends Activity {
	private GraphicView graphicView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		graphicView = new GraphicView(this);
		setContentView(graphicView);
		
		DatabaseService dbs = new DatabaseService(this); 
	
		CurveGraphic g = dbs.getGraphByWeek(40,50);
		
		graphicView.init(g);
		
		//GraphicDialog gd = new GraphicDialog(this);
		//gd.show();
	}
	
	public void init(String type, String unit, String count){
		DatabaseService dbs = new DatabaseService(this); 
		CurveGraphic g = dbs.getGraphByWeek(40,50);
		graphicView.init(g);
	}
}