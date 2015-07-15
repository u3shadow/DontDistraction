package com.u3.dontdistraction.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.adapter.RecordAdapter;
import com.u3.dontdistraction.databasedal.RecordDal;
import com.u3.dontdistraction.model.Record;
import com.u3.dontdistraction.util.DateTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U3 on 2015/7/5.
 */
public class RecordFragment extends Fragment {
    private RecordAdapter adapter;
    private RecordDal recordDal;
   private List<String> group;
   private List<List<Record>> child;
    private List<Record> mList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordDal = new RecordDal(getActivity());
        List list = recordDal.getList();
        if(list.size() == 0)
        {
            AlertDialog.Builder dialogBuilder =  new AlertDialog.Builder(getActivity());
            dialogBuilder.setTitle(getActivity().getResources().getString(R.string.norecord_title))
                    .setMessage(getActivity().getResources().getString(R.string.norecord))
                    .setPositiveButton(getActivity().getResources().getString(R.string.okbutton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                         Fragment setTimeFragment = new SetTimeFragment();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.layout_main, setTimeFragment);
                            fragmentTransaction.commit();
                        }
                    })
                    .create().show();
        }
        mList = recordDal.getList();
        List<Record> list1 = new ArrayList<>();
        for(int i = mList.size() - 1;i >=  0;i--)
        {
            list1.add(mList.get(i));
        }
        mList = list1;
        genGroup();
        genChild();
        adapter = new RecordAdapter(mList,getActivity(),group,child);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_expand,null);
        ExpandableListView listView = (ExpandableListView)view.findViewById(R.id.elv_record);
         listView.setAdapter(adapter);
        return view;
    }
    public void genGroup()
    {
        group = new ArrayList<String>();
        for(int i = 0;i < mList.size();i++) {
            String currentDay = DateTools.getDay(mList.get(i).getDate());
            if( group.indexOf(currentDay) < 0)
            {
                group.add(currentDay);
            }
        }
    }
    public void genChild()
    {
        child = new ArrayList<List<Record>>();
        for(int i = 0;i < group.size();i++)
        {
            List<Record> item = new ArrayList<Record>();
            child.add(item);
        }
        for(int i = 0;i < group.size();i++)
        {
            String groupDay = group.get(i);
            for(int j = 0;j < mList.size();j++)
            {
                String childDay = DateTools.getDay(mList.get(j).getDate());
                if(childDay.equals(groupDay))
                {
                    child.get(i).add(mList.get(j));
                }
            }
        }
    }
}
