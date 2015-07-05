package com.u3.dontdistraction.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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


public class MainActivity extends SlidingFragmentActivity {
    SlidingMenu menu;
    Fragment aboutFragment;
    Fragment setTimeFragment;
    Fragment recordFragment;
    private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
    private Oauth2AccessToken token;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        isLogin();
        setEndReciver();
        initFragment();
        setmenu();
        setListener();
    }
    private void isLogin() {
        token = AccessTokenKeeper.readAccessToken(this);
        if (token == null || !token.isSessionValid()) {
            jumpToLogin();
        }
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
        menu.setShadowWidth(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow_slidingmenu);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        setBehindContentView(R.layout.slidinglayout);
    }

    private void setListener() {
        Button setButton = (Button) findViewById(R.id.bt_time_set);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main, setTimeFragment);
                fragmentTransaction.commit();
                menu.toggle();
            }
        });
        Button aboutButton = (Button) findViewById(R.id.bt_about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main, aboutFragment);
                fragmentTransaction.commit();
                menu.toggle();
            }
        });
        Button recordButton = (Button) findViewById(R.id.bt_record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_main, recordFragment);
                fragmentTransaction.commit();
                menu.toggle();
            }
        });
        Button logoffButton = (Button) findViewById(R.id.bt_logoff);
        logoffButton.setOnClickListener(new View.OnClickListener() {
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
        });

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
}
