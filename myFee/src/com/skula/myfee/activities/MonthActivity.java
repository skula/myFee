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
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.MonthListAdapter;
import com.skula.myfee.activities.dialogs.AmountDialog;
import com.skula.myfee.models.Category;
import com.skula.myfee.models.Fee;
import com.skula.myfee.models.Month;
import com.skula.myfee.services.DatabaseService;


public class MonthActivity extends Activity {

	private MonthListAdapter listAdapter;
	private ExpandableListView expListView;
	private DatabaseService dbs;
	private List<Category> headers;
	private Map<String, List<Fee>> childs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.month_layout);
		
		dbs = new DatabaseService(this);
		//dbs.bouchon();
		headers = dbs.getCategoriesDetails();
		childs = dbs.getFeesByCategories();
		
		listAdapter = new MonthListAdapter(this, headers, childs);
		expListView = (ExpandableListView) findViewById(R.id.month_list);
		expListView.setAdapter(listAdapter);	
		
		Month mon = dbs.getCurrentMonthDetails();
		TextView lab = (TextView) findViewById(R.id.month_label);
		lab.setText(mon.getLabel());
		TextView tot = (TextView) findViewById(R.id.month_total);
		tot.setText(mon.getTotal().replace(".", ",")+ " €");

		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Fee f = childs.get(headers.get(groupPosition).getLabel()).get(childPosition);					
				Intent intent = new Intent(v.getContext(), FeeActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("feeId", f.getId());
				intent.putExtras(mBundle);
				try{
				startActivity(intent);
				} catch(Exception e){
					e.getMessage();
				}
				return false;
			}
		});
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