package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;
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
}