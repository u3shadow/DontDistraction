package com.u3.dontdistraction.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.adapter.RecordAdapter;
import com.u3.dontdistraction.databasedal.RecordDal;
import com.u3.dontdistraction.model.Record;

import java.util.List;

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
        adapter = new RecordAdapter(recordDal.getList(),getActivity());
        setListAdapter(adapter);
    }
}
