package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.skula.activities.myfee.R;


public class CategoryListActivity extends Activity {
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories_layout);
		
		listView = (ListView) findViewById(R.id.categories_list);
	}
}