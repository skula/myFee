package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.myfee.activities.dialogs.GraphicDialog;
import com.skula.myfee.activities.views.GraphicView;



public class GraphicActivity extends Activity {
	private GraphicView graphicView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		graphicView = new GraphicView(this);
		setContentView(graphicView);
		
		//GraphicDialog gd = new GraphicDialog(this);
		//gd.show();
	}
	
	public void init(String type, String unit, String count){
		// creation du Graphique
		// creation du DrawerService
		// graphicView.init(DrawerService);
	}
}