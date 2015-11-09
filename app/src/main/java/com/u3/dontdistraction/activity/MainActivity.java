package com.u3.dontdistraction.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.fragments.AboutFragment;
import com.u3.dontdistraction.fragments.RecordFragment;
import com.u3.dontdistraction.fragments.SetTimeFragment;
import com.u3.dontdistraction.util.RefreshProblem;

import java.util.ArrayList;
import java.util.List;

/*import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.j256.ormlite.stmt.query.NeedsFutureClause;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;*/import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    Fragment aboutFragment;
    Fragment setTimeFragment;
    Fragment recordFragment;
    // private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
    // private Oauth2AccessToken token;
    List<LinearLayout> llList;
    DrawerLayout drawerLayout;
    @Bind(R.id.Fl_content)
    FrameLayout FlContent;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.iv_headpic)
    SimpleDraweeView ivHeadpic;
    @Bind(R.id.iv_settime)
    ImageView ivSettime;
    @Bind(R.id.Tv_settime)
    TextView TvSettime;
    @Bind(R.id.ll_time_set)
    LinearLayout llTimeSet;
    @Bind(R.id.iv_record)
    ImageView ivRecord;
    @Bind(R.id.Tv_record)
    TextView TvRecord;
    @Bind(R.id.ll_record)
    LinearLayout llRecord;
    @Bind(R.id.iv_refresh)
    ImageView ivRefresh;
    @Bind(R.id.Tv_refresh)
    TextView TvRefresh;
    @Bind(R.id.ll_refresh)
    LinearLayout llRefresh;
    @Bind(R.id.iv_about)
    ImageView ivAbout;
    @Bind(R.id.tv_about)
    TextView tvAbout;
    @Bind(R.id.ll_about)
    LinearLayout llAbout;
    @Bind(R.id.bt_logoff)
    Button btLogoff;
    @Bind(R.id.layout_main)
    DrawerLayout layoutMain;
    private PackageManager mPackageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        enableLauncher();
        showLint();
        isLogin();
        setEndReciver();
        initFragment();
        setListener();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_main);
    }

    private void enableLauncher() {
        mPackageManager = getApplicationContext().getPackageManager();
        mPackageManager.setComponentEnabledSetting(new
                        ComponentName("com.u3.dontdistraction",
                        "com.u3.dontdistraction.activity.HomeActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void showLint() {
        SharedPreferences preferences = getSharedPreferences("App", 0);
        Boolean isFirstTime = preferences.getBoolean("isFirstOpen", true);
    }

    private void isLogin() {
      /*  token = AccessTokenKeeper.readAccessToken(this);
        if (token == null || !token.isSessionValid()) {
            jumpToLogin();
        }*/
    }

    private void jumpToLogin() {
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

        llTimeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, setTimeFragment);
                fragmentTransaction.commit();
                resetLL(llTimeSet.getId());
                toggle();
            }
        });

        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, aboutFragment);
                fragmentTransaction.commit();
                resetLL(llAbout.getId());
                toggle();
            }
        });

        llRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, recordFragment);
                fragmentTransaction.commit();
                resetLL(llRecord.getId());
                toggle();
            }
        });
        llRefresh.setOnClickListener(new View.OnClickListener() {
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
        llList = new ArrayList<LinearLayout>() {
            {
                add(llAbout);
                add(llTimeSet);
                add(llRecord);
            }
        };
        resetLL(llTimeSet.getId());
    }

    private void resetLL(int id) {
     ivAbout.setImageResource(R.drawable.about0);
        tvAbout.setTextColor(getResources().getColor(R.color.black));
        ivRecord.setImageResource(R.drawable.record0);
        TvRecord.setTextColor(getResources().getColor(R.color.black));
        ivSettime.setImageResource(R.drawable.time0);
        TvSettime.setTextColor(getResources().getColor(R.color.black));
        switch (id)
        {
            case R.id.ll_time_set:{
                ivSettime.setImageResource(R.drawable.time);
                TvSettime.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
            }
            case R.id.ll_record:{
                ivRecord.setImageResource(R.drawable.record1);
                TvRecord.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
            }
            case R.id.ll_about:{
                ivAbout.setImageResource(R.drawable.about1);
                tvAbout.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
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
            if ((System.currentTimeMillis() - exitTime) > 2000) {
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

    private void setEndReciver() {
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

    private void toggle() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
