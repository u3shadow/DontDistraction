package com.u3.dontdistraction.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.u3.dontdistraction.util.Recoder;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {
    HomeChoice homeChoice; //HomeChoice为设置和启动主屏幕类
    private SharedPreferences sharedPreferences;
    private Editor editor;
    private boolean isLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeChoice = new HomeChoice(this);
        sharedPreferences = getSharedPreferences("homeChoice", MODE_PRIVATE);
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
            PackageManager  mPackageManager;
            mPackageManager = getApplicationContext().getPackageManager();
            mPackageManager.setComponentEnabledSetting(new

                            ComponentName("com.u3.dontdistraction",

                            "com.u3.dontdistraction.activity.HomeActivity"),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        }
    }
    public class HomeChoice {
        Context context;
        Intent intent;
        SharedPreferences sharedPreferences;
        Editor editor;
        String packageName = "packageName";
        String activityName = "activityName";
        List<String> pkgNames, actNames;

        public HomeChoice(Context context) {
            this.context = context;
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            sharedPreferences = context.getSharedPreferences("homeChoice", MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        public void chooseBackHome() {

            List<String> pkgNames = new ArrayList<String>();
            List<String> actNames = new ArrayList<String>();
            //获取所有能作为主屏的应用信息
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            //将这些应用的包名和Activity名称存入list
            for (int i = 0; i < resolveInfos.size(); i++) {
                String string = resolveInfos.get(i).activityInfo.packageName;
                if (!string.equals(context.getPackageName())) {
                    pkgNames.add(string);
                    string = resolveInfos.get(i).activityInfo.name;
                    actNames.add(string);
                }
            }
            //转化报名为数组
            String[] names = new String[pkgNames.size()];
            for (int i = 0; i < names.length; i++) {
                names[i] = pkgNames.get(i);
            }
            this.pkgNames = pkgNames;
            this.actNames = actNames;
            //启动dialog让用户点击设置预设主屏
            new AlertDialog.Builder(context).setTitle("设置主屏幕")
                    .setCancelable(false)
                    .setSingleChoiceItems(names, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editor.putString(packageName, HomeChoice.this.pkgNames.get(which));
                    editor.putString(activityName, HomeChoice.this.actNames.get(which));
                    editor.commit();
                    originalHome();
                    dialog.dismiss();
                }
            }).show();
        }
        //启动预设主屏
        public void originalHome() {
            String pkgName = sharedPreferences.getString(packageName, null);
            String actName = sharedPreferences.getString(activityName, null);
            ComponentName componentName = new ComponentName(pkgName, actName);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            context.startActivity(intent);
           // ((Activity) context).finish();
        }
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
