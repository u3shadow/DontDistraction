package com.u3.dontdistraction.record.page;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.record.adapter.RecordAdapter;
import com.u3.dontdistraction.record.databasedal.RecordDal;
import com.u3.dontdistraction.screenlock.model.Record;
import com.u3.dontdistraction.screenlock.gnome.Gnomes;
import com.u3.dontdistraction.util.DateTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U3 on 2015/7/5.
 */
public class RecordFragment extends Fragment {
    private List<String> group;
    private List<List<Record>> child;
    private List<Record> mList;
    private ExpandableListView listView;
    private ProgressBar progressBar;
    private TextView norecord;

    public interface callback {
        public void resetButton();

        public void restLis();
    }

    private void noRecord() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(getActivity().getResources().getString(R.string.norecord_title))
                .setMessage(getActivity().getResources().getString(R.string.norecord))
                .setPositiveButton(getActivity().getResources().getString(R.string.okbutton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        norecord.setVisibility(View.VISIBLE);
                    }
                })
                .create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_expand, null);
        listView = (ExpandableListView) view.findViewById(R.id.elv_record);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        norecord = (TextView) view.findViewById(R.id.Tv_norecord);
        GetListTask task = new GetListTask();
        task.execute();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Record record = child.get(groupPosition).get(childPosition);
                View view = getActivity().getLayoutInflater().inflate(R.layout.layout_record_detail,null);
                Gnomes gnomes = new Gnomes(getActivity());
                TextView gnomeText = (TextView)view.findViewById(R.id.recordgnome);
                gnomeText.setText(gnomes.getGnome());
                TextView time  = (TextView)view.findViewById(R.id.locktime);
                time.setText("Total:"+record.getMinu()+"min"+record.getSec()
                        +"sec"+" "+DateTools.getTime(record.getStarTime())+"-"+DateTools.getTime(record.getEndTime()));
                final AlertDialog dialog = new  AlertDialog.Builder(getActivity()).setView(view).show();
                Button button =  (Button)view.findViewById(R.id.confirm);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Log.i("asdf",record.getEndTime().toString()+record.getStarTime().toString()+"");
                return false;
            }
        });
    }

    private void genGroup() {
        group = new ArrayList<String>();
        for (int i = 0; i < mList.size(); i++) {
            String currentDay = DateTools.getDay(mList.get(i).getEndTime());
            if (group.indexOf(currentDay) < 0) {
                group.add(currentDay);
            }
        }
    }

    private void genChild() {
        child = new ArrayList<List<Record>>();
        for (int i = 0; i < group.size(); i++) {
            List<Record> item = new ArrayList<Record>();
            child.add(item);
        }
        for (int i = 0; i < group.size(); i++) {
            String groupDay = group.get(i);
            for (int j = 0; j < mList.size(); j++) {
                String childDay = DateTools.getDay(mList.get(j).getEndTime());
                if (childDay.equals(groupDay)) {
                    child.get(i).add(mList.get(j));
                }
            }
        }
    }

    private class GetListTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            RecordDal recordDal = new RecordDal(getActivity());
            mList = recordDal.getList();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mList.size() == 0) {
                noRecord();
            } else {
                norecord.setVisibility(View.GONE);
            }
            List<Record> list1 = new ArrayList<>();
            int j = 0;
            for (int i = mList.size() - 1; i >= 0; i--) {
                if (j < 200) {
                    list1.add(mList.get(i));
                    j++;
                } else {
                    break;
                }
            }
            mList = list1;
            genGroup();
            genChild();
            RecordAdapter adapter = new RecordAdapter(mList, getActivity(), group, child);
            listView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
    }

}
