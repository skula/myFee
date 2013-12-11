package com.skula.myfee.activities;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.adapters.CategoryAdapter;
import com.skula.myfee.activities.dialogs.AmountDialog;
import com.skula.myfee.models.Category;
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
	private Button btnDate;

	private String tmpAmount;
	private Fee fee;

	final int Date_Dialog_ID = 0;
	private int cDay, cMonth, cYear; // this is the instances of the current
										// date
	private Calendar cDate;
	private int sDay, sMonth, sYear; // this is the instances of the entered
										// date

	private boolean modeCrea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fee_layout);

		this.fee = new Fee();
		dbs = new DatabaseService(this);

		listView = (ListView) findViewById(R.id.fee_categories);
		catAdapter = new CategoryAdapter(this, dbs.getCategories(), listView);

		listView.setAdapter(catAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// int i = listView.getCheckedItemPosition();

		btnCancel = (Button) findViewById(R.id.fee_btnCancel);
		btnAdd = (Button) findViewById(R.id.fee_btnAdd);
		btnMod = (Button) findViewById(R.id.fee_btnMod);
		btnDel = (Button) findViewById(R.id.fee_btnDel);

		label = (EditText) findViewById(R.id.fee_label);
		date = (EditText) findViewById(R.id.fee_date);
		amount = (TextView) findViewById(R.id.fee_amount);
		btnDate = (Button) findViewById(R.id.fee_btnDate);

		// getting current date
		cDate = Calendar.getInstance();
		cDay = cDate.get(Calendar.DAY_OF_MONTH);
		cMonth = cDate.get(Calendar.MONTH);
		cYear = cDate.get(Calendar.YEAR);
		// assigning the edittext with the current date in the beginning
		sDay = cDay;
		sMonth = cMonth;
		sYear = cYear;
		updateDateDisplay(sYear, sMonth, sDay);

		Bundle bundle = getIntent().getExtras();
		String id = bundle == null ? null : bundle.getString("feeId");
		if (id == null) {
			handleCreateMode();
		} else {
			handleModifyMode(id);
		}

		final FeeActivity act = this;
		amount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AmountDialog ad = new AmountDialog(act);
				ad.show();
			}
		});

		btnDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(Date_Dialog_ID);
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		btnAdd.setOnClickListener(new OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {

				Category c = (Category)listView.getItemAtPosition(listIndex);
				Toast.makeText(v.getContext(), "pos: ", Toast.LENGTH_SHORT);
				/*Fee tmp = new Fee();
				tmp.setLabel(label.getText().toString());
				tmp.setDate(date.getText().toString());
				tmp.setAmount(fee.getAmount());
				dbs.insertFee(tmp);
				Toast.makeText(v.getContext(), "Dépense ajoutée.",
						Toast.LENGTH_SHORT).show();*/
			}
		});

		btnMod.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleModify(act);
			}
		});

		btnDel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//handleDelete(act);
				
				Toast.makeText(v.getContext(), "pos: " + listView.getSelectedItemPosition(), Toast.LENGTH_SHORT);
			}
		});

		AmountDialog ad = new AmountDialog(this);
		ad.show();
	}

	private RadioButton listRadioButton = null;
	   int listIndex = -1;

	   public void onClickRadioButton(View v) {
	        View vMain = ((View) v.getParent());
	        // getParent() must be added 'n' times, 
	        // where 'n' is the number of RadioButtons' nested parents
	        // in your case is one.

	        // uncheck previous checked button. 
	        if (listRadioButton != null) listRadioButton.setChecked(false);
	        // assign to the variable the new one
	        listRadioButton = (RadioButton) v;
	        // find if the new one is checked or not, and set "listIndex"
	        if (listRadioButton.isChecked()) {
	            listIndex = ((ViewGroup) vMain.getParent()).indexOfChild(vMain); 
	        } else {
	            listRadioButton = null;
	            listIndex = -1;
	        }
	    }
	   
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case Date_Dialog_ID:
			return new DatePickerDialog(this, onDateSet, cYear, cMonth, cDay);
		}
		return null;
	}

	private void updateDateDisplay(int year, int month, int day) {
		date.setText(day + "/" + (month + 1) + "/" + year);
		fee.setDate(year + "-" + (month + 1) + "-" + day);
	}

	private OnDateSetListener onDateSet = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			sYear = year;
			sMonth = monthOfYear;
			sDay = dayOfMonth;
			updateDateDisplay(sYear, sMonth, sDay);
		}
	};

	public void handleModify(final FeeActivity mainActivity) {
		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(mainActivity);
		helpBuilder.setTitle("Modification");
		helpBuilder
				.setMessage("Etes-vous sûr de vouloir modifier cette dépense ?");
		helpBuilder.setPositiveButton("Oui",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						modify();
					}
				});
		helpBuilder.setNegativeButton("Non",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}

	public void handleDelete(final FeeActivity mainActivity) {
		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(mainActivity);
		helpBuilder.setTitle("Suppression");
		helpBuilder
				.setMessage("Etes-vous sûr de vouloir supprimer cette dépense ?");
		helpBuilder.setPositiveButton("Oui",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						delete();
					}
				});
		helpBuilder.setNegativeButton("Non",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}

	public void modify() {
		Fee tmp = new Fee();
		tmp.setLabel(label.getText().toString());
		tmp.setDate(date.getText().toString());
		// TODO : get color
		tmp.setAmount(fee.getAmount());
		dbs.updateFee(fee.getId(), tmp);
	}

	public void delete() {
		dbs.deleteFee(fee.getId());
	}

	private void handleCreateMode() {
		btnMod.setVisibility(View.GONE);
		btnDel.setVisibility(View.GONE);
	}

	private void handleModifyMode(String id) {
		btnAdd.setVisibility(View.GONE);
		fee = dbs.getFee(id);
		label.setText(fee.getLabel());
		date.setText(fee.getDate());
		amount.setText(fee.getAmount().replace(".", ",") + " €");
		// TODO : set color
	}

	public void setAmount(String amnt) {
		fee.setAmount(amnt);
		amount.setText(fee.getAmount().replace(".", ",") + " €");
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