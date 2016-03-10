package com.u3.dontdistraction.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.other.Problems;

/**
 * Created by U3 on 2015/5/29.
 */
public class ScreenLockActivity extends Activity {
    public static boolean isTimed = false;
    private TextView text;
    private int lockTime;
    private Button putAnswer;
    private Button endLock;
    private TextView problem;
    private Problems problems;
    private EditText answer;
    private CountDownTimer mTimer;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String reason = intent.getStringExtra("reason");
            if (reason != null) {
                if (reason.equals("homekey")) {
                    startResultActivity(false);
                }
            }
        }
    };

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
        addHomeReceiver();
    }
    private void addHomeReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(receiver,filter);
    }
    private void timeCountDown() {

        lockTime = getIntent().getIntExtra("lockTime", -1);
        lockTime = lockTime * 60 * 1000;
        mTimer = new CountDownTimer(lockTime, 1000) {
            public void onTick(long millisUntilFinished) {
                text.setText(getResources().getString(R.string.time_remain)
                        + millisUntilFinished / (60 * 1000)
                        + getResources().getString(R.string.minute)
                        + (millisUntilFinished / 1000 - (millisUntilFinished / (60 * 1000) * 60))
                        + getResources().getString(R.string.second));
            }

            public void onFinish() {
                text.setText(getResources().getString(R.string.time_end));
                putAnswer.setVisibility(View.GONE);
                endLock.setText(getResources().getString(R.string.complete));
                endLock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mIntent = new Intent(ScreenLockActivity.this, ResultActivity.class);
                        mIntent.putExtra("isTimeEnd", true);
                        startActivity(mIntent);
                        ScreenLockActivity.this.finish();
                    }
                });
            }
        };
        mTimer.start();
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
                    startResultActivity(true);
                } else {
                    animation();
                    Toast.makeText(ScreenLockActivity.this, getResources().getString(R.string.wrong_answer), Toast.LENGTH_LONG).show();
                }
            }
        });
        endLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startResultActivity(false);
            }
        });
    }

    private void startResultActivity(Boolean isTimeEnd) {
        Intent mIntent = new Intent(ScreenLockActivity.this, ResultActivity.class);
        mIntent.putExtra("isTimeEnd", isTimeEnd);
        startActivity(mIntent);
        ScreenLockActivity.this.finish();
    }

    private void animation() {
        TranslateAnimation animation1 = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.edittextshake);
        answer.startAnimation(animation1);
    }

    private void initProblem() {
        problems = new Problems(ScreenLockActivity.this);
        problem.setText(problems.getProblem());
    }


    private void setEndReciver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction("com.u3.end");
        registerReceiver(endReciver, filter);
    }

    private final BroadcastReceiver endReciver = new BroadcastReceiver() {
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
        Log.i("sl", "slp");
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("sl", "sls");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("sl", "sld");
        mTimer.cancel();
        unregisterReceiver(endReciver);
        unregisterReceiver(receiver);
    }
}
