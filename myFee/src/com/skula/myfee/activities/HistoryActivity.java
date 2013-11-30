package com.skula.myfee.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.HistoryListAdapter;
import com.skula.myfee.services.DatabaseService;


public class HistoryActivity extends Activity {

	private HistoryListAdapter listAdapter;
	private ExpandableListView expListView;
	private DatabaseService dbs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.histo_layout);
		
		dbs = new DatabaseService(this);
		
		listAdapter = new HistoryListAdapter(this, dbs.getMonthsDetails(), dbs.getFeesByMonths());
		expListView = (ExpandableListView) findViewById(R.id.histo_list);
		expListView.setAdapter(listAdapter);
		
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