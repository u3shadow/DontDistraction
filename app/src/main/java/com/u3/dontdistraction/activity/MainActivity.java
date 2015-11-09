package com.u3.dontdistraction.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/*import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.j256.ormlite.stmt.query.NeedsFutureClause;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;*/
import com.u3.dontdistraction.fragments.AboutFragment;
import com.u3.dontdistraction.fragments.RecordFragment;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.fragments.SetTimeFragment;
import com.u3.dontdistraction.util.RefreshProblem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{
    Fragment aboutFragment;
    Fragment setTimeFragment;
    Fragment recordFragment;
   // private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
   // private Oauth2AccessToken token;
    List<Button> buttonList;
    DrawerLayout drawerLayout;
    private Button recordButton;
    private Button setButton;
    private Button aboutButton;
    private Button refreshButton;
    private PackageManager mPackageManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        enableLauncher();
        showLint();
        isLogin();
        setEndReciver();
        initFragment();
        setListener();
    }
    private void initView()
    {
        drawerLayout = (DrawerLayout)findViewById(R.id.layout_main);
        setButton = (Button) findViewById(R.id.bt_time_set);
        aboutButton = (Button) findViewById(R.id.bt_about);
        recordButton = (Button) findViewById(R.id.bt_record);
        refreshButton = (Button)findViewById(R.id.bt_refresh);
    }
    private void enableLauncher()
    {
        mPackageManager = getApplicationContext().getPackageManager();
        mPackageManager.setComponentEnabledSetting(new
                        ComponentName("com.u3.dontdistraction",
                        "com.u3.dontdistraction.activity.HomeActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
    private void showLint() {
        SharedPreferences preferences  = getSharedPreferences("App",0);
        Boolean isFirstTime = preferences.getBoolean("isFirstOpen", true);
    }

    private void isLogin() {
      /*  token = AccessTokenKeeper.readAccessToken(this);
        if (token == null || !token.isSessionValid()) {
            jumpToLogin();
        }*/
    }
    private void jumpToLogin(){
        Intent mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
    }
    private void initFragment() {
        aboutFragment = new AboutFragment();
        setTimeFragment = new SetTimeFragment();
        recordFragment = new RecordFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.Fl_content, setTimeFragment);
        fragmentTransaction.commit();
    }
    private void setListener() {

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, setTimeFragment);
                fragmentTransaction.commit();
                reSetButton(setButton.getId());
               toggle();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, aboutFragment);
                fragmentTransaction.commit();
                reSetButton(aboutButton.getId());
                toggle();
            }
        });

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, recordFragment);
                fragmentTransaction.commit();
                reSetButton(recordButton.getId());
               toggle();
            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshProblem refreshProblem = new RefreshProblem(MainActivity.this);
                refreshProblem.refresh();
            }
        });
        Button logoffButton = (Button) findViewById(R.id.bt_logoff);
      /*  logoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AccessTokenKeeper.readAccessToken(MainActivity.this).isSessionValid()) {
                    new LogoutAPI(MainActivity.this, Constants.APP_KEY,
                            AccessTokenKeeper.readAccessToken(MainActivity.this)).logout(mLogoutListener);
                }else
                {
                    Intent mIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                }
            }
        });*/
       buttonList = new ArrayList<Button>(){
            {
                add(setButton);
                add(aboutButton);
                add(recordButton);
            }
        };
        reSetButton(setButton.getId());
    }
    private void reSetButton(int id)
    {
        for(int i = 0;i < buttonList.size();i++)
        {
            if(buttonList.get(i).getId() != id){
            buttonList.get(i).setBackgroundColor(getResources().getColor(R.color.sliding_button));
            }
            else
            {
                buttonList.get(i).setBackgroundColor(getResources().getColor(R.color.sliding_button_press));
            }
        }
    }



   /* private class LogOutRequestListener implements RequestListener {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String value = obj.getString("result");
                    if ("true".equalsIgnoreCase(value)) {
                        AccessTokenKeeper.clear(MainActivity.this);
                        Toast.makeText(MainActivity.this,getResources().getString(R.string.logoff_success),Toast.LENGTH_LONG).show();
                        Intent mIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(mIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    }*/
    private long exitTime;
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(getApplicationContext(), "再按一次,退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent mIntent = new Intent();
                mIntent.setAction("com.u3.end");
                sendBroadcast(mIntent);
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
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
    private void toggle()
    {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
