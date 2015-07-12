package com.u3.dontdistraction.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.u3.dontdistraction.model.Record;
import com.u3.dontdistraction.util.Recoder;

import java.util.List;

/**
 * Created by U3 on 2015/7/12.
 */
public class ExpandRecordAdapter extends BaseExpandableListAdapter {
    Context context;
    List<Record> mList;
    List<String> group;
    List<List<Record>> child;
    public ExpandRecordAdapter(Context context,List<Record> list){
        this.context = context;
        mList = list;
        genGroup();
        genChild();
    }
    public void genGroup()
    {}
    public void genChild()
    {}
    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
