package com.skula.myfee.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.BudgetAdapter;
import com.skula.myfee.activities.dialogs.AmountDialog;
import com.skula.myfee.models.Budget;
import com.skula.myfee.services.DatabaseService;


public class BudgetActivity extends Activity {
	private ListView listView;
	private BudgetAdapter bAdapter;
	private DatabaseService dbs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.budget_layout);
		dbs = new DatabaseService(this);
		dbs.bouchon();
		listView = (ListView) findViewById(R.id.budget_list);
		Budget array[] = dbs.getBudgetDetails();
		bAdapter = new BudgetAdapter(this, R.layout.budget_item_layout, array);
		listView.setAdapter(bAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.budget, menu);
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
			case R.id.graphics:
	        	intent = new Intent(this, GraphicActivity.class);
	            startActivity(intent);
	            return true;
	        case R.id.categories:
				intent = new Intent(this, CategoryListActivity.class);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}