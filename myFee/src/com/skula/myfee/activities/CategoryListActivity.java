package com.skula.myfee.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.CategorySimpleAdapter;
import com.skula.myfee.services.DatabaseService;


public class CategoryListActivity extends Activity {
	private ListView listView;
	private CategorySimpleAdapter catAdapter;
	private DatabaseService dbs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories_layout);
		dbs = new DatabaseService(this);
		listView = (ListView) findViewById(R.id.categories_list);
		
		catAdapter = new CategorySimpleAdapter(this, R.layout.category_item_simple_layout, dbs.getSimpleCategories());
		listView.setAdapter(catAdapter);
		
		Button btnAdd = (Button) findViewById(R.id.categories_btnAdd);
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), CategoryActivity.class);
	            startActivity(intent);				
			}
		});
	}
}