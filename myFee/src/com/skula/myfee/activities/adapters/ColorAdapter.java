package com.skula.myfee.activities.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.models.Budget;

public class ColorAdapter extends ArrayAdapter<String>{
	private String[] colors;
	private Context context;
	
	public ColorAdapter(Context context, int textViewResourceId,
		String[] objects) {
		super(context, textViewResourceId, objects);
		this.colors = objects;
		this.context = context;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		String col = colors[position];
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.color_list_item_layout, parent, false);
		TextView ref = (TextView)row.findViewById(R.id.color_list_item_ref);
		ref.setText(col);
		int coli = Color.parseColor(col);
		ref.setBackgroundColor(coli);
		ref.setTextColor(coli);

		return row;
	}
}