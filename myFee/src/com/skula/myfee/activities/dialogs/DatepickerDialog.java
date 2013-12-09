package com.skula.myfee.activities.dialogs;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.skula.activities.myfee.R;


public class DatepickerDialog extends Activity{
	EditText etDate;
	Button change_date;
	final int Date_Dialog_ID = 0;
	int cDay,cMonth,cYear; // this is the instances of the current date
	Calendar cDate;
	int sDay,sMonth,sYear; // this is the instances of the entered date
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datpicker_layout);
		
		etDate=(EditText)findViewById(R.id.EditText01);
		change_date=(Button)findViewById(R.id.Button01);
		change_date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//triggers the DatePickerDialog
				showDialog(Date_Dialog_ID);
			}
		});
		
		//getting current date
		cDate=Calendar.getInstance();
		cDay=cDate.get(Calendar.DAY_OF_MONTH);
		cMonth=cDate.get(Calendar.MONTH);
		cYear=cDate.get(Calendar.YEAR);
		//assigning the edittext with the current date in the beginning
		sDay=cDay;
		sMonth=cMonth;
		sYear=cYear;
		updateDateDisplay(sYear,sMonth,sDay);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case Date_Dialog_ID:
			return new DatePickerDialog(this, onDateSet, cYear, cMonth,cDay);
		}
		return null;
	}

	private void updateDateDisplay(int year,int month,int date) {
		// TODO Auto-generated method stub
		etDate.setText(date+"-"+(month+1)+"-"+year);
	}
	
	private OnDateSetListener onDateSet=new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			System.out.println("2");
			sYear=year;
			sMonth=monthOfYear;
			sDay=dayOfMonth;
			updateDateDisplay(sYear,sMonth,sDay);
		}
	};
}