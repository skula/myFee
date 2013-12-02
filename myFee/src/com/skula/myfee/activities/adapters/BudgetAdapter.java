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

public class BudgetAdapter extends ArrayAdapter<Budget> {
	Context context;
	int layoutResourceId;
	Budget data[] = null;

	public BudgetAdapter(Context context, int layoutResourceId, Budget[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Budget item = data[position];
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.budget_item_layout, parent, false);

		TextView cat = (TextView) rowView.findViewById(R.id.budget_item_catlab);
		cat.setText(item.getCategory());
		int col = Color.parseColor(item.getColor());
		cat.setTextColor(col);
		
		TextView total = (TextView) rowView.findViewById(R.id.budget_item_total_lab);
		total.setText(item.getTotal().replace(".",",") + " €");
		
		TextView goal = (TextView) rowView.findViewById(R.id.budget_item_goal);
		goal.setText(item.getGoal().replace(".",",") + " €");
		
		ProgressBar pb = (ProgressBar) rowView.findViewById(R.id.budget_item_percent);
        double a = Double.valueOf(item.getPercent());
		if(a>=100.0){
			pb.setProgress(100);
			//set color blue
		}else{
			pb.setProgress((int)a);
			//set color red
		}
		
		return rowView;
	}
}