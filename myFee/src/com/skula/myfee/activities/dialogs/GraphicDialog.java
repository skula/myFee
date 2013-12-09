package com.skula.myfee.activities.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.GraphicActivity;


public class GraphicDialog extends Dialog {
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
		btnContinue = (Button) findViewById(R.id.graphic_dial_btn_continue);
	}
}