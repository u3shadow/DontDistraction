package com.u3.dontdistraction.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.u3.dontdistraction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U3 on 2015/8/2.
 */
public class WelComeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        Button startBt = (Button)findViewById(R.id.bt_start);
        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(WelComeActivity.this,MainActivity.class);
                startActivity(mIntent);
            }
        });
        setEndReciver();
    }
    private void setEndReciver()
    {
        final IntentFilter filter = new IntentFilter();
        filter.addAction("com.u3.end");
        registerReceiver(endReciver, filter);
    }
    final BroadcastReceiver endReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(endReciver);
    }
}
