package com.u3.dontdistraction.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.util.TimeRecoder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${U3} on 2016/4/8.
 */
public class FootPrintFragment extends Fragment {
    @Bind(R.id.days)
    TextView days;
    @Bind(R.id.times)
    TextView times;
    @Bind(R.id.hint)
    TextView hint;
    @Bind(R.id.confirm)
    Button confirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_footprint, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        TimeRecoder.initRecord(getActivity());
        initView();
        initListener();
    }

    private void initView() {
        days.setText(getResources().getString(R.string.youhave)+TimeRecoder.getDays()+getResources().getString(R.string.footprint));
        times.setText(getResources().getString(R.string.havelearn)+TimeRecoder.getTime()+getResources().getString(R.string.minute));
        if (TimeRecoder.canRecord()){
            if(TimeRecoder.hadNotRecord())
            hint.setText(getResources().getString(R.string.leavehint));
            else
                hint.setText(getResources().getString(R.string.hadfoot));
        }
        else
            hint.setText(getString(R.string.learnmore)+(10 - TimeRecoder.getTime())+getString(R.string.tofootprint));
    }
private void initListener(){
    confirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TimeRecoder.canRecord()){
                if (TimeRecoder.hadNotRecord())
                {
                    if (TimeRecoder.record()){
                        initView();
                    }
                }else
                {
                    Toast.makeText(getActivity(),getString(R.string.hadfooted),Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getActivity(),getString(R.string.cantfoot),Toast.LENGTH_LONG).show();
            }
        }
    });
}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
