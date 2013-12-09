package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.myfee.activities.dialogs.GraphicDialog;
import com.skula.myfee.activities.views.GraphicView;



public class GraphicActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GraphicDialog gd = new GraphicDialog(this);
		gd.show();
	}
	
	public void init(String type, String unit, String count){
		setContentView(new GraphicView(this));
	}
}