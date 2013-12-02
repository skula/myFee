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
		
		listView = (ListView) findViewById(R.id.budget_list);
		Budget array[] = dbs.getBudgetDetails();
		bAdapter = new BudgetAdapter(this, R.layout.budget_item_layout, array);
		listView.setAdapter(bAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.month, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.add_fee:
				AmountDialog ad = new AmountDialog(this);
				ad.show();
	            return true;
	        case R.id.history:
	        	intent = new Intent(this, HistoryActivity.class);
	            startActivity(intent);
	            return true;
	        case R.id.budget:
	            return true;
	        case R.id.parameters:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}