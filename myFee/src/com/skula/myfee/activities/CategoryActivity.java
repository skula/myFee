package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.models.Category;
import com.skula.myfee.services.DatabaseService;


public class CategoryActivity extends Activity {
	private DatabaseService dbs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_layout);
		
		dbs = new DatabaseService(this);
		Category cat = dbs.getCategory("1");
		
		TextView label = (TextView)findViewById(R.id.category_label);
		label.setText(cat.getLabel());
		
		TextView budget = (TextView)findViewById(R.id.category_budget);
		budget.setText(cat.getBudget().replace(",",".")+" €");
		
	}
}