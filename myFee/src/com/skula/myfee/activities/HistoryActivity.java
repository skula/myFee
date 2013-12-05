package com.skula.myfee.activities;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.HistoryListAdapter;
import com.skula.myfee.models.Fee;
import com.skula.myfee.models.Month;
import com.skula.myfee.services.DatabaseService;


public class HistoryActivity extends Activity {

	private HistoryListAdapter listAdapter;
	private ExpandableListView expListView;
	private DatabaseService dbs;
	private List<Month> headers;
	private Map<String, List<Fee>> childs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.histo_layout);
		
		dbs = new DatabaseService(this);
		//dbs.bouchon();
		headers = dbs.getMonthsDetails();
		childs = dbs.getFeesByMonths();
		
		listAdapter = new HistoryListAdapter(this, headers, childs);
		expListView = (ExpandableListView) findViewById(R.id.histo_list);
		expListView.setAdapter(listAdapter);

		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Fee f = childs.get(headers.get(groupPosition).getLabel()).get(childPosition);					
				Intent intent = new Intent(v.getContext(), FeeActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("feeId", f.getId());
				intent.putExtras(mBundle);
				startActivity(intent);
				return false;
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.history, menu);
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
	        case R.id.budget:
				intent = new Intent(this, BudgetActivity.class);
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