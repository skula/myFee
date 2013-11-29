package com.skula.myfee.activities.adapters;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.skula.activities.myfee.R;
import com.skula.myfee.models.Category;
import com.skula.myfee.models.Fee;
 
public class MonthListAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<Category> headers; 
    private Map<String, List<Fee>> childs;
 
    public MonthListAdapter(Context context, List<Category> headers,
            Map<String, List<Fee>> childs) {
        this._context = context;
        this.headers = headers;
        this.childs = childs;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childs.get(this.headers.get(groupPosition).getLabel())
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final Fee fee = (Fee) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.month_list_item_layout, null);
        }
 
        TextView date = (TextView) convertView.findViewById(R.id.month_list_item_date);
        date.setText(fee.getDate());
		TextView amount = (TextView) convertView.findViewById(R.id.month_list_item_amount);
        amount.setText(fee.getAmount());
		TextView label = (TextView) convertView.findViewById(R.id.month_list_item_label);
        label.setText(fee.getLabel());
		
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childs.get(this.headers.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this.headers.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this.headers.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        Category cat = (Category) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.month_list_header_layout, null);
        }
 
        TextView label = (TextView) convertView.findViewById(R.id.month_list_header_label);
        label.setText(cat.getLabel());
		TextView total = (TextView) convertView.findViewById(R.id.month_list_header_total);
        total.setText(cat.getTotal());
		TextView perc = (TextView) convertView.findViewById(R.id.month_list_header_percent);
        perc.setText(cat.getPercent());
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}