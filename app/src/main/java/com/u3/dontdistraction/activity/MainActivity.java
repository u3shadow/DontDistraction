package com.u3.dontdistraction.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/*import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;*/
import com.j256.ormlite.stmt.query.NeedsFutureClause;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.u3.dontdistraction.fragments.AboutFragment;
import com.u3.dontdistraction.fragments.RecordFragment;
import com.u3.dontdistraction.util.AccessTokenKeeper;
import com.u3.dontdistraction.util.Constants;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.fragments.SetTimeFragment;
import com.u3.dontdistraction.util.Recoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends SlidingFragmentActivity {
    SlidingMenu menu;
    Fragment aboutFragment;
    Fragment setTimeFragment;
    Fragment recordFragment;
    private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
    private Oauth2AccessToken token;
    List<Button> buttonList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        showLint();
        isLogin();
        setEndReciver();
        initFragment();
        setmenu();
        setListener();
    }

    private void showLint() {
        SharedPreferences preferences  = getSharedPreferences("App",0);
        Boolean isFirstTime = preferences.getBoolean("isFirstOpen", true);
        if(isFirstTime)
        {
            AlertDialog.Builder dialogBuilder =  new AlertDialog.Builder(this);
            dialogBuilder.setTitle(getResources().getString(R.string.firstHintHead))
                    .setMessage(getResources().getString(R.string.firstHintText))
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                    .create().show();
            isFirstTime = !isFirstTime;
            preferences.edit().putBoolean("isFirstOpen",isFirstTime).commit();
        }
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
        fragmentTransaction.add(R.id.layout_main, setTimeFragment);
        fragmentTransaction.commit();
    }

    private void setmenu() {
        menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        setBehindContentView(R.layout.slidinglayout);
    }

    private void setListener() {
        final Button setButton = (Button) findViewById(R.id.bt_time_set);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main, setTimeFragment);
                fragmentTransaction.commit();
                reSetButton(setButton.getId());
                menu.toggle();
            }
        });
        final  Button aboutButton = (Button) findViewById(R.id.bt_about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main, aboutFragment);
                fragmentTransaction.commit();
                reSetButton(aboutButton.getId());
                menu.toggle();
            }
        });
        final   Button recordButton = (Button) findViewById(R.id.bt_record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main, recordFragment);
                fragmentTransaction.commit();
                reSetButton(recordButton.getId());
                menu.toggle();
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
    private class LogOutRequestListener implements RequestListener {
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
    }
    private long exitTime;
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {// back退出应用程序
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 处理连按退出
            // System.currentTimeMillis()无论何时调用，肯定大于2000
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
}
