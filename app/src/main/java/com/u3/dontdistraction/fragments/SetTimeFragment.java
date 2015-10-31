package com.u3.dontdistraction.fragments;


import android.app.Fragment;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.u3.dontdistraction.activity.LoginActivity;
import com.u3.dontdistraction.activity.ScreenLockActivity;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.util.Recoder;

public class SetTimeFragment extends Fragment {

    Button setTime;
    EditText time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.settime_title));
        View view = inflater.inflate(R.layout.fragment_time_set, null);
        setTime = (Button) view.findViewById(R.id.bt_time_set);
        time = (EditText) view.findViewById(R.id.et_inputtime);
        initListener();
        return view;
    }

    private void initListener() {
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if (!AccessTokenKeeper.readAccessToken(getActivity()).isSessionValid()) {
                    checkIsLogin();
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.notlog), Toast.LENGTH_LONG).show();
                    return;
                }*/
                if (!time.getText().toString().equals("") && !time.getText().toString().equals("0")) {
                    if (!Recoder.isTimed) {
                        reopenScreenLock();
                    } else {
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.istimed), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.wrong_time), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void reopenScreenLock() {
        Intent mIntent = new Intent(getActivity(), ScreenLockActivity.class);
        Recoder.lockTime = new Integer(time.getText().toString());
        Recoder.isTimed = true;
        setRecevier();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        getActivity().startActivity(mIntent);
    }

    private void checkIsLogin() {
        Intent mIntent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(mIntent);
        getActivity().finish();
    }

    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Recoder.isTimed) {
                KeyguardManager km = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                if (km.inKeyguardRestrictedInputMode()) {
                    Intent alarmIntent = new Intent(context, ScreenLockActivity.class);
                    alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(alarmIntent);
                }
            }
        }
    };

    private void setRecevier() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        getActivity().registerReceiver(broadcastReceiver, filter);
    }

}

