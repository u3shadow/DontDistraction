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
        initView();
        initListener();
    }

    private void initView() {
        days.setText("你已经踩下了"+TimeRecoder.getDays()+"个足迹");
        times.setText("今天已经学习了"+TimeRecoder.getTime()+"分钟");
        if (TimeRecoder.canRecord())
            hint.setText("留下你的足迹吧");
        else
            hint.setText("再学习"+(10 - TimeRecoder.getTime())+"分钟就可以踩下足迹");
    }
private void initListener(){
    confirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TimeRecoder.canRecord()){
                if (TimeRecoder.record()){
                    initView();
                }
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
