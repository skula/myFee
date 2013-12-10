package com.skula.myfee.activities.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.GraphicActivity;


public class GraphicDialog extends Dialog implements OnClickListener {
	public GraphicActivity parentActivity;
	public Button btnCancel;
	public Button btnContinue;
	private Spinner typeSpin;
	private Spinner unitSpin;
	private Spinner monthSpin;
	private EditText count;
	
	public GraphicDialog(GraphicActivity parentActivity) {
		super(parentActivity);
		this.parentActivity = parentActivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.graphic_dial_layout);
		
		typeSpin = (Spinner) findViewById(R.id.graphic_dial_type);
		unitSpin = (Spinner) findViewById(R.id.graphic_dial_unit);
		monthSpin = (Spinner) findViewById(R.id.graphic_dial_month);
		count = (EditText) findViewById(R.id.graphic_dial_count);
		
		btnCancel = (Button) findViewById(R.id.graphic_dial_btn_cancel);
		btnCancel.setOnClickListener(this);
		btnContinue = (Button) findViewById(R.id.graphic_dial_btn_continue);		
		btnContinue.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.amount_btn_cancel:
			dismiss();			
			break;
		case R.id.amount_btn_continue:
			String type = typeSpin.getItemAtPosition(typeSpin.getSelectedItemPosition()).toString();
			String unit = unitSpin.getItemAtPosition(unitSpin.getSelectedItemPosition()).toString();
			String month = monthSpin.getItemAtPosition(monthSpin.getSelectedItemPosition()).toString();
			parentActivity.init(type, unit, count.getText().toString());
			dismiss();
			break;
		default:
			dismiss();
			break;
		}
	}
}