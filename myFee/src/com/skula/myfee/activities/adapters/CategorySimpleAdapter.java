package com.skula.myfee.activities.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.models.Category;

public class CategorySimpleAdapter extends ArrayAdapter<Category> {
	Context context;
	int layoutResourceId;
	Category data[] = null;

	public CategorySimpleAdapter(Context context, int layoutResourceId, Category[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Category item = data[position];
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.category_item_simple_layout, parent, false);

		TextView id = (TextView) rowView.findViewById(R.id.category_item_simple_id);
		id.setText(item.getId());
		
		TextView color = (TextView) rowView.findViewById(R.id.category_item_simple_color);
		int col = Color.parseColor(item.getColor());
		color.setBackgroundColor(col);
		
		TextView label = (TextView) rowView.findViewById(R.id.category_item_simple_label);
		label.setText(item.getLabel());
		return rowView;
	}
}