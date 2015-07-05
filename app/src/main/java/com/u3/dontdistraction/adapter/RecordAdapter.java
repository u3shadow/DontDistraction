package com.u3.dontdistraction.adapter;

import android.content.Context;
import android.database.DatabaseUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.model.Record;
import com.u3.dontdistraction.util.DataTools;

import java.util.Date;
import java.util.List;

/**
 * Created by U3 on 2015/7/5.
 */
public class RecordAdapter extends BaseAdapter {
    private List<Record> mList;
    private Context mContext;

    public RecordAdapter(List<Record> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        Record currentRecord;
        currentRecord = mList.get(position);
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.record_list_item, null);
            holder.itemLayout = (RelativeLayout) convertView.findViewById(R.id.layout_recorditem);
            holder.unlockData = (RelativeLayout) convertView.findViewById(R.id.layout_unlockdata);
            holder.unlockDataText = (TextView) convertView.findViewById(R.id.tv_unlockday);
            holder.isSuccessText = (TextView) convertView.findViewById(R.id.tv_record);
            holder.unlockTimeText = (TextView) convertView.findViewById(R.id.tv_unlocktime);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        boolean isSameDay = false;
        if (position > 0) {
            Record lastRecord = mList.get(position - 1);
            String currentDay = DataTools.getDay(currentRecord.getDate());
            String lastDay = DataTools.getDay(lastRecord.getDate());
             isSameDay = currentDay.equals(lastDay);
        }
        if (isSameDay) {
            holder.unlockData.setVisibility(View.GONE);
        }
        if (currentRecord.isSuccess()) {
            //  holder.itemLayout.setBackground(R.drawable.success_record_pic);
             holder.isSuccessText.setText(R.string.unlock_success);
        } else {
            // holder.itemLayout.setBackground(R.drawable.fail_record_pic);
            holder.isSuccessText.setText(R.string.unlock_fail);
        }
        holder.unlockDataText.setText(DataTools.getDay(currentRecord.getDate()));
        holder.unlockTimeText.setText(DataTools.getTime(currentRecord.getDate()));
        return convertView;
    }

    class Holder {
        public RelativeLayout itemLayout;
        public RelativeLayout unlockData;
        public TextView unlockDataText;
        public TextView isSuccessText;
        public TextView unlockTimeText;
    }
}
