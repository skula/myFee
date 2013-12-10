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

public class CategoryAdapter extends ArrayAdapter<Category> {
	Context context;
	List<Category> itemList;
	ListView list;
	
	public CategoryAdapter(Context context, List<Category> itemList, ListView list) {
		super(context.getApplicationContext(), R.layout.category_item_layout, itemList);
		this.itemList = itemList;
		this.context = context;
		this.list = list;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.category_item_layout, parent, false);
		}
		
		TextView color = (TextView) row.findViewById(R.id.category_item_color);
		color.setText(itemList.get(position).getLabel().substring(0,1));
		
		color.setBackgroundColor(Color.parseColor(itemList.get(position).getColor()));
		
		TextView label = (TextView) row.findViewById(R.id.category_item_label);
		label.setText(itemList.get(position).getLabel());
		
		//CheckedTextView checkBox = (CheckedTextView) row.findViewById(R.id.category_item_state);
		//checkBox.setChecked(true);//list.getCheckedItemPosition() == position);
		return (row);
	}
}