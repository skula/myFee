package com.skula.myfee.activities;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.MonthListAdapter;
import com.skula.myfee.models.Fee;
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
		
		listAdapter = new MonthListAdapter(this, dbs.getCategoriesDetails(), dbs.getFeesByCategories());
		expListView = (ExpandableListView) findViewById(R.id.month_list);
		expListView.setAdapter(listAdapter);		

		
	}
}