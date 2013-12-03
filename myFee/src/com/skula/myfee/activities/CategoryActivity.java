package com.skula.myfee.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.models.Category;
import com.skula.myfee.services.DatabaseService;


public class CategoryActivity extends Activity {
	private DatabaseService dbs;
	private String id;
	private boolean modeCrea;
	
	private Button btnCancel;
	private Button btnAdd;
	private Button btnMod;
	private Button btnDel;
	private EditText label;
	private TextView color;
	private EditText budget;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_layout);
		
		dbs = new DatabaseService(this);
		
		label = (EditText)findViewById(R.id.category_label);
		color = (TextView)findViewById(R.id.category_color);
		budget = (EditText)findViewById(R.id.category_budget);
		
		btnCancel = (Button) findViewById(R.id.category_btnCancel);
		btnAdd = (Button) findViewById(R.id.category_btnAdd);
		btnMod = (Button) findViewById(R.id.category_btnMod);
		btnDel = (Button) findViewById(R.id.category_btnDel);
		
		id = getIntent().getExtras().getString("caegoryId");
		if(id == null){
			handleCreateMode();
		}else{
			handleModifyMode();
		}
		
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
								
			}
		});	

		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
								
			}
		});	
		
		btnMod.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
								
			}
		});	
		
		btnDel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
								
			}
		});	
	}
	
	private void handleCreateMode(){
		btnMod.setVisibility(View.GONE);
		btnDel.setVisibility(View.GONE);
	}
	
	private void handleModifyMode(){
		btnAdd.setVisibility(View.GONE);
		Category cat = dbs.getCategory(id);
		label.setText(cat.getLabel());
		budget.setText(cat.getBudget());
		int col = Color.parseColor(cat.getColor());
		color.setBackgroundColor(col);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.category, menu);
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
	        case R.id.categories:
				intent = new Intent(this, CategoryListActivity.class);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}