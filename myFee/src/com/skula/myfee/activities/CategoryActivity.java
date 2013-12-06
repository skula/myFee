package com.skula.myfee.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.ColorAdapter;
import com.skula.myfee.constants.Cnst;
import com.skula.myfee.models.Category;
import com.skula.myfee.services.DatabaseService;


public class CategoryActivity extends Activity {
	private DatabaseService dbs;
	private Category cat;
	private boolean modeCrea;
	
	private Button btnCancel;
	private Button btnAdd;
	private Button btnMod;
	private Button btnDel;
	private EditText label;
	private Spinner color;
	private EditText budget;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_layout);
		this.cat = new Category();
		dbs = new DatabaseService(this);
		
		color = (Spinner) findViewById(R.id.category_color);
		color.setAdapter(new ColorAdapter(this, R.id.color_list_item_ref, Cnst.COLORS));
		label = (EditText)findViewById(R.id.category_label);
		budget = (EditText)findViewById(R.id.category_budget);
		
		btnCancel = (Button) findViewById(R.id.category_btnCancel);
		btnAdd = (Button) findViewById(R.id.category_btnAdd);
		btnMod = (Button) findViewById(R.id.category_btnMod);
		btnDel = (Button) findViewById(R.id.category_btnDel);
		
		String id = getIntent().getExtras().getString("categoryId");
		if(id == null){
			handleCreateMode();
		}else{
			handleModifyMode(id);
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
	
	private void handleModifyMode(String id){
		btnAdd.setVisibility(View.GONE);
		cat = dbs.getCategory(id);
		label.setText(cat.getLabel());
		budget.setText(cat.getBudget());
		
		int index = 0;
		for(; index<Cnst.COLORS.length; index++){
			if(Cnst.COLORS[index].equals(cat.getColor())){
				break;
			}
		}
		//color.setSelection(index);
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