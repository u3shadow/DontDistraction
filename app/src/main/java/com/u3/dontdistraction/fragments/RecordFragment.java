package com.u3.dontdistraction.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.u3.dontdistraction.adapter.RecordAdapter;
import com.u3.dontdistraction.databasedal.RecordDal;

/**
 * Created by U3 on 2015/7/5.
 */
public class RecordFragment extends ListFragment {
    private RecordAdapter adapter;
    private RecordDal recordDal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordDal = new RecordDal(getActivity());
        adapter = new RecordAdapter(recordDal.getList(),getActivity());
        setListAdapter(adapter);
    }
}
