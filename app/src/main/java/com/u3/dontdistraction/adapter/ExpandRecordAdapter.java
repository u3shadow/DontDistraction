package com.u3.dontdistraction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.model.Record;
import com.u3.dontdistraction.util.DataTools;
import com.u3.dontdistraction.util.Recoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U3 on 2015/7/12.
 */
public class ExpandRecordAdapter extends BaseExpandableListAdapter {
    Context context;
    List<Record> mList;
    List<String> group;
    List<List<Record>> child;
    public ExpandRecordAdapter(List<Record> list,Context context,List<String> group,List<List<Record>> child){
        this.context = context;
        mList = list;
        this.group = group;
        this.child = child;
    }
    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.record_list_item_group,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.tv_group);
        textView.setText(group.get(groupPosition).toString()+" ("+child.get(groupPosition).size()+")");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        Holder holder;
        Record currentRecord;
        currentRecord = child.get(groupPosition).get(childPosition);
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.record_list_item, null);
            holder.itemLayout = (RelativeLayout) view.findViewById(R.id.layout_recorditem);
            holder.unlockData = (RelativeLayout) view.findViewById(R.id.layout_unlockdata);
            holder.unlockDataText = (TextView) view.findViewById(R.id.tv_unlockday);
            holder.isSuccessText = (TextView) view.findViewById(R.id.tv_record);
            holder.unlockTimeText = (TextView) view.findViewById(R.id.tv_unlocktime);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
            holder.unlockData.setVisibility(View.GONE);
        if (currentRecord.isSuccess()) {
            holder.itemLayout.setBackgroundResource(R.drawable.success_record_pic);
            holder.isSuccessText.setText(R.string.unlock_success);
            holder.isSuccessText.setTextColor(context.getResources().getColor(R.color.success_record));
        } else {
            holder.itemLayout.setBackgroundResource(R.drawable.fail_record_pic);
            holder.isSuccessText.setText(R.string.unlock_fail);
            holder.isSuccessText.setTextColor(context.getResources().getColor(R.color.fail_record));
        }
        holder.unlockDataText.setText(DataTools.getDay(currentRecord.getDate()));
        holder.unlockTimeText.setText(DataTools.getTime(currentRecord.getDate()));
        return view;
    }
    class Holder {
        public RelativeLayout itemLayout;
        public RelativeLayout unlockData;
        public TextView unlockDataText;
        public TextView isSuccessText;
        public TextView unlockTimeText;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
