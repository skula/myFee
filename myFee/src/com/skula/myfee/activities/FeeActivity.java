package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.CategoryAdapter;
import com.skula.myfee.services.DatabaseService;


public class FeeActivity extends Activity {
	private DatabaseService dbs;
	private ListView listView;
	private CategoryAdapter catAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fee_layout);
		
		dbs = new DatabaseService(this);
		
		listView = (ListView) findViewById(R.id.fee_categories);
		catAdapter = new CategoryAdapter(this, dbs.getCategories(), listView);
		
		listView.setAdapter(catAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);	
		//int i = listView.getCheckedItemPosition();
	}
}