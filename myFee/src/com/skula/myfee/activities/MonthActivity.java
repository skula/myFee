package com.skula.myfee.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.MonthListAdapter;
import com.skula.myfee.models.Month;
import com.skula.myfee.services.DatabaseService;


public class MonthActivity extends Activity {

	private MonthListAdapter listAdapter;
	private ExpandableListView expListView;
	private DatabaseService dbs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.month_layout);
		
		dbs = new DatabaseService(this);
		dbs.bouchon();
		listAdapter = new MonthListAdapter(this, dbs.getCategoriesDetails(), dbs.getFeesByCategories());
		expListView = (ExpandableListView) findViewById(R.id.month_list);
		expListView.setAdapter(listAdapter);	
		
		Month mon = dbs.getCurrentMonthDetails();
		TextView lab = (TextView) findViewById(R.id.month_label);
		lab.setText(mon.getLabel());
		TextView tot = (TextView) findViewById(R.id.month_total);
		tot.setText(mon.getTotal().replace(".", ",")+ " €");
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
	        	intent = new Intent(this, FeeActivity.class);
	            startActivity(intent);
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