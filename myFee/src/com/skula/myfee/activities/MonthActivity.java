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

	MonthListAdapter listAdapter;
	ExpandableListView expListView;
	private DatabaseService dbs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.month_layout);

		//listAdapter = new MonthListAdapter(this, AntCnst.MENU_HEADERS, AntCnst.MENU_ITEMS);
		expListView = (ExpandableListView) findViewById(R.id.month_list);
		//expListView.setAdapter(listAdapter);
		
		dbs = new DatabaseService(this);
		dbs.bouchon();
		
		Map<String, List<Fee>> cats = dbs.getFeesByCategories();
		

		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {

				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				
			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				return false;
			}
		});
	}
}