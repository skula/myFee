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
		
		TextView totalLab = (TextView) rowView.findViewById(R.id.budget_item_total);
		TextView goalLab = (TextView) rowView.findViewById(R.id.budget_item_goal);
		ProgressBar pb = (ProgressBar) rowView.findViewById(R.id.budget_item_percent);
		
		double total = Double.valueOf(item.getTotal());
		double goal = Double.valueOf(item.getGoal());
		if(goal>total){
			totalLab.setText(item.getTotal().replace(".",",") + " €");
			goalLab.setText(item.getGoal().replace(".",",") + " €");
			double ratio = (total/goal)*100;
			pb.setProgress((int)ratio);
		}else{
			String tmp = item.getTotal() + " € (" + item.getGoal() + " €)";
			totalLab.setText(tmp.replace(".",","));
			tmp = (goal-total) + "";
			goalLab.setText(tmp.replace(".",",") + " €");
			goalLab.setTextColor(Color.RED);
			pb.setProgress(100);
		}
		
		return rowView;
	}
}