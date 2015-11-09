package com.u3.dontdistraction.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.u3.dontdistraction.util.Recoder;

public class HomeActivity extends Activity {
    private boolean isLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onStart() {
        super.onStart();
        isLock = Recoder.isFront;
        setEndReciver();
        //判断锁屏Activity是否在前台
        if (isLock) {
            Intent mIntent = new Intent(this, ScreenLockActivity.class);
            startActivity(mIntent);
            finish();
        }
        else
        {
            startSystemLauncher();
        }
    }
    private void startSystemLauncher()
    {
        PackageManager  mPackageManager;
        mPackageManager = getApplicationContext().getPackageManager();
        mPackageManager.setComponentEnabledSetting(new ComponentName("com.u3.dontdistraction",
                        "com.u3.dontdistraction.activity.HomeActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
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
            HomeActivity.this.finish();
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(endReciver);
    }


}
