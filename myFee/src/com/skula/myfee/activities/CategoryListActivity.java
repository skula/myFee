package com.skula.myfee.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.CategorySimpleAdapter;
import com.skula.myfee.models.Category;
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
		
		listView.setOnItemClickListener(new OnItemClickListener() {
	

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				/*Category cat = (Category) arg0.getItem(arg2);
				Intent intent = new Intent(arg1.getContext(), CategoryActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("categoryId", cat.getId());
				intent.putExtras(mBundle);
				startActivity(intent);*/
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.categories, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.month:
	        	intent = new Intent(this, MonthActivity.class);
	            startActivity(intent);
	            return true;
	        case R.id.history:
	        	intent = new Intent(this, HistoryActivity.class);
	            startActivity(intent);
	            return true;
	         case R.id.budget:
				intent = new Intent(this, BudgetActivity.class);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}