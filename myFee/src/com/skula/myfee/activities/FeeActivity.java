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
import android.widget.ListView;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.CategoryAdapter;
import com.skula.myfee.models.Fee;
import com.skula.myfee.services.DatabaseService;


public class FeeActivity extends Activity {
	private DatabaseService dbs;
	private ListView listView;
	private CategoryAdapter catAdapter;
	
	private Button btnCancel;
	private Button btnAdd;
	private Button btnMod;
	private Button btnDel;
	private EditText label;
	private EditText date;
	private TextView amount;
	
	private String id;
	private String tmpAmount;
	private boolean modeCrea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fee_layout);
		
		dbs = new DatabaseService(this);

		listView = (ListView) findViewById(R.id.fee_categories);
		catAdapter = new CategoryAdapter(this, dbs.getCategories(), listView);
		
		listView.setAdapter(catAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);	
		//int i = listView.getCheckedItemPosition();
		
		btnCancel = (Button) findViewById(R.id.fee_btnCancel);
		btnAdd = (Button) findViewById(R.id.fee_btnAdd);
		btnMod = (Button) findViewById(R.id.fee_btnMod);
		btnDel = (Button) findViewById(R.id.fee_btnDel);
		
		label = (EditText) findViewById(R.id.fee_label);
		date = (EditText) findViewById(R.id.fee_date);
		amount = (TextView) findViewById(R.id.fee_amount);
		
		id = getIntent().getExtras().getString("feeId");
		tmpAmount = getIntent().getExtras().getString("feeAmount");
		if(id == null){
			handleCreateMode();
		}else{
			handleModifyMode();
		}
		
		amount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//AmountDialog ad = new AmountDialog(v.getContext());
				//ad.show();
			}
		});	
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					
			}
		});	

		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fee fee = new Fee();
				fee.setLabel(label.getText().toString());
				fee.setDate(date.getText().toString());
				fee.setAmount(amount.getText().toString());
				dbs.insertFee(fee);
			}
		});	
		
		btnMod.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fee fee = new Fee();
				fee.setLabel(label.getText().toString());
				fee.setDate(date.getText().toString());
				fee.setAmount(tmpAmount);
				dbs.updateFee(id, fee);
			}
		});	
		
		btnDel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dbs.deleteFee(id);			
			}
		});	
	}
	
	private void handleCreateMode(){
		btnMod.setVisibility(View.GONE);
		btnDel.setVisibility(View.GONE);
		amount.setText(tmpAmount.replace(".",",") + " €");
	}
	
	private void handleModifyMode(){
		btnAdd.setVisibility(View.GONE);
		
		/*Fee fee = dbs.getFee(id);
		label.setText(fee.getLabel());
		date.setText(fee.getDate());
		amount.setText(fee.getAmount());*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.fee, menu);
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