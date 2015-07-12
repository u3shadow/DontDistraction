package com.u3.dontdistraction.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.adapter.RecordAdapter;
import com.u3.dontdistraction.databasedal.RecordDal;
import com.u3.dontdistraction.model.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U3 on 2015/7/5.
 */
public class RecordFragment extends Fragment {
    private RecordAdapter adapter;
    private RecordDal recordDal;
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
        List<Record> mList = recordDal.getList();
        List<Record> list1 = new ArrayList<>();
        for(int i = mList.size() - 1;i >=  0;i--)
        {
            list1.add(mList.get(i));
        }
        mList = list1;
        adapter = new RecordAdapter(mList,getActivity());

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record,null);
        ListView listView = (ListView)view.findViewById(R.id.lv_record);
         listView.setAdapter(adapter);
        return view;
    }
}
