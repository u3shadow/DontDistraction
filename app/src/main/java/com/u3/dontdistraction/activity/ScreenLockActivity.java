package com.u3.dontdistraction.activity;

import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.other.Problems;
import com.u3.dontdistraction.util.Recoder;

import java.security.cert.X509Certificate;

/**
 * Created by U3 on 2015/5/29.
 */
public class ScreenLockActivity extends Activity {
    TextView text;
    int lockTime;
    Button putAnswer;
    Button endLock;
    TextView problem;
    Problems problems;
    EditText answer;
    PackageManager mPackageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_screenlock);

        initView();
        initProblem();
        timeCountDown();
        initListener();
        setEndReciver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPackageManager = getApplicationContext().getPackageManager();
        mPackageManager.setComponentEnabledSetting(new

                        ComponentName("com.u3.dontdistraction",

                        "com.u3.dontdistraction.activity.HomeActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void timeCountDown() {
        lockTime = Recoder.lockTime;
        lockTime = lockTime * 60 * 1000;
        new CountDownTimer(lockTime, 1000) {
            public void onTick(long millisUntilFinished) {
                text.setText(getResources().getString(R.string.time_remain)
                        + millisUntilFinished / (60 * 1000)
                        + getResources().getString(R.string.minute)
                        + (millisUntilFinished / 1000 - (millisUntilFinished / (60 * 1000) * 60))
                        + getResources().getString(R.string.second));
            }

            public void onFinish() {
                text.setText(getResources().getString(R.string.time_end));
                Recoder.isTimeEnd = true;
                putAnswer.setVisibility(View.GONE);
                endLock.setText(getResources().getString(R.string.complete));
                endLock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        closeLock();
                        Recoder.isTimeEnd = true;
                        Recoder.isFront = false;
                        Intent mIntent = new Intent(ScreenLockActivity.this, ResultActivity.class);
                        startActivity(mIntent);
                        ScreenLockActivity.this.finish();
                    }
                });
            }
        }.start();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.tv_msg);
        putAnswer = (Button) findViewById(R.id.bt_enteranswer);
        problem = (TextView) findViewById(R.id.tv_problem);
        answer = (EditText) findViewById(R.id.et_answer);
        endLock = (Button) findViewById(R.id.bt_endlock);
    }

    private void initListener() {
        putAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.getText().toString() != null && problems.isAnswerRight(answer.getText().toString())) {
                    closeLock();
                    Recoder.isTimeEnd = true;
                    Recoder.isFront = false;
                    Intent mIntent = new Intent(ScreenLockActivity.this, ResultActivity.class);
                    startActivity(mIntent);
                    ScreenLockActivity.this.finish();
                } else {
                    animation();
                    Toast.makeText(ScreenLockActivity.this, getResources().getString(R.string.wrong_answer), Toast.LENGTH_LONG).show();
                }
            }
        });
        endLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLock();
                Recoder.isTimeEnd = false;
                Recoder.isFront = false;
                Intent mIntent = new Intent(ScreenLockActivity.this, ResultActivity.class);
                startActivity(mIntent);
                ScreenLockActivity.this.finish();
            }
        });
    }
    private void animation()
    {
        float x  = answer.getPivotX();
        TranslateAnimation animation1 = new TranslateAnimation(x,x + 10,answer.getPivotY(),answer.getPivotY());
        animation1.setRepeatMode(Animation.RESTART);
        animation1.setDuration(100);
        TranslateAnimation animation2 = new TranslateAnimation(x,x - 10,answer.getPivotY(),answer.getPivotY());
        animation2.setRepeatMode(Animation.RESTART);
        animation2.setDuration(100);
        animation2.setStartOffset(200);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animation1);
        set.addAnimation(animation2);
        set.setRepeatCount(2);
        answer.startAnimation(set);
    }
    private void closeLock()
    {
        mPackageManager = getApplicationContext().getPackageManager();
        mPackageManager.setComponentEnabledSetting(new

                        ComponentName("com.u3.dontdistraction",

                        "com.u3.dontdistraction.activity.HomeActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
    private void initProblem() {
        problems = new Problems();
        problem.setText(problems.getProblem());
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU || keyCode == KeyEvent.KEYCODE_APP_SWITCH) {
            return true;
        }
        return true;
    }
    //屏蔽menu
    @Override
    public void onWindowFocusChanged(boolean pHasWindowFocus) {
        super.onWindowFocusChanged(pHasWindowFocus);
        if (!pHasWindowFocus) {
            sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }
    @Override
    protected void onStart() {
       Recoder.isFront = true;
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(endReciver);
    }
}
