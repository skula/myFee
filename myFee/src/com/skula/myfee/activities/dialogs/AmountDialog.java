package com.skula.myfee.activities.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.activities.FeeActivity;


public class AmountDialog extends Dialog implements OnClickListener {

	public Activity parentActivity;
	public Button btn0;
	public Button btn1;
	public Button btn2;
	public Button btn3;
	public Button btn4;
	public Button btn5;
	public Button btn6;
	public Button btn7;
	public Button btn8;
	public Button btn9;
	public Button btnComa;
	public Button btnDel;
	public Button btnCancel;
	public Button btnContinue;
	
	public TextView label;
	public String amount;
	
	public AmountDialog(Activity parentActivity) {
		super(parentActivity);
		this.parentActivity = parentActivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.amount_dial_layout);
		
		this.amount = "";
		label = (TextView) findViewById(R.id.amount_label);
		label.setText(formatAmount());
		
		btn0 = (Button) findViewById(R.id.amount_btn_0);
		btn1 = (Button) findViewById(R.id.amount_btn_1);
		btn2 = (Button) findViewById(R.id.amount_btn_2);
		btn3 = (Button) findViewById(R.id.amount_btn_3);
		btn4 = (Button) findViewById(R.id.amount_btn_4);
		btn5 = (Button) findViewById(R.id.amount_btn_5);
		btn6 = (Button) findViewById(R.id.amount_btn_6);
		btn7 = (Button) findViewById(R.id.amount_btn_7);
		btn8 = (Button) findViewById(R.id.amount_btn_8);
		btn9 = (Button) findViewById(R.id.amount_btn_9);
		btnComa = (Button) findViewById(R.id.amount_btn_coma);
		btnDel = (Button) findViewById(R.id.amount_btn_del);	
		btnCancel = (Button) findViewById(R.id.amount_btn_cancel);
		btnContinue = (Button) findViewById(R.id.amount_btn_continue);
		
		btn0.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btnComa.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnContinue.setOnClickListener(this);
	}
	
	//dismiss(); parentActivity.finish()
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.amount_btn_0:
			label.setText(handleAmount("0"));
			break;
			case R.id.amount_btn_1:
			label.setText(handleAmount("1"));
			break;
			case R.id.amount_btn_2:
			label.setText(handleAmount("2"));
			break;
			case R.id.amount_btn_3:
			label.setText(handleAmount("3"));
			break;
			case R.id.amount_btn_4:
			label.setText(handleAmount("4"));
			break;
			case R.id.amount_btn_5:
			label.setText(handleAmount("5"));
			break;
			case R.id.amount_btn_6:
			label.setText(handleAmount("6"));
			break;
			case R.id.amount_btn_7:
			label.setText(handleAmount("7"));
			break;
			case R.id.amount_btn_8:
			label.setText(handleAmount("8"));
			break;
			case R.id.amount_btn_9:
			label.setText(handleAmount("9"));
			break;
			case R.id.amount_btn_coma:
			label.setText(handleAmount(","));
			break;
			case R.id.amount_btn_del:
			amount = "";
			label.setText(formatAmount());
			break;
			case R.id.amount_btn_cancel:
			dismiss();
			break;
			case R.id.amount_btn_continue:
			dismiss();
			Intent intent = new Intent(parentActivity, FeeActivity.class);
			intent.putExtra("feeAmount", formatDouble());
	        v.getContext().startActivity(intent);
			break;
			default:
			dismiss();
			break;
		}
	}
	
	private String handleAmount(String c) {
		if (c.equals("0") && amount.isEmpty()) {
			return formatAmount();
		}
		
		if (c.equals(",") && amount.isEmpty()) {
			amount = "0,";
			return formatAmount();
		}
		
		if (amount.contains(",") && c.equals(",")) {
			return formatAmount();
		}
		if (amount.contains(",")) {
			int a = amount.indexOf(',');
			int b = amount.length() - a;
			if (b < 3) {
				amount += c;
			}
		} else {
			amount += c;
		}
		return spaces(formatAmount());
	}

	private String formatAmount() {
		if (amount.isEmpty()) {
			return "0,00 €";
		}

		if (!amount.contains(",")) {
			return amount + ",00 €";
		} else {
			int a = amount.indexOf(',');
			String tmp = amount;
			for (int i = 2; i > amount.length() - a - 1; i--) {
				tmp += "0";
			}
			tmp += " €";
			return tmp;
		}
	}
	
	private String formatDouble() {
		if (amount.isEmpty()) {
			return "0.0";
		}

		if (!amount.contains(",")) {
			return amount + ".00";
		} else {
			int a = amount.indexOf(',');
			String tmp = amount;
			for (int i = 2; i > amount.length() - a - 1; i--) {
				tmp += "0";
			}
			return tmp.replace(",",".");
		}
	}

	private String spaces(String in2) {
		String in = "";
		String r2 = "";	
		in = in2.substring(0, in2.indexOf(","));
		r2 = in2.substring(in2.indexOf(",")+1);
		if (in.length() < 4) {
			return in+","+r2;
		} else {
			int a = in.length() % 3;
			String res = in.substring(0,a);
			if(!res.isEmpty()){
				res+=" ";
			}
			int b = in.length() / 3;
			for(int i=0 ; i<b; i++){
				int s1 = a+i*3;
				int s2 =  a+i*3+3;
				String tmp = in.substring(s1,s2);
				res = res + tmp + " ";
			}
			return res.trim()+","+r2;
		}
	}
}