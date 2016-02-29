package com.u3.dontdistraction.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.u3.dontdistraction.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${U3} on 2015/11/10.
 */
public class SplashActivity extends Activity {
    @Bind(R.id.iv_icon)
    ImageView ivIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        setEndReciver();
        ButterKnife.bind(this);
        AlphaAnimation animation = new AlphaAnimation(1, (float) 0.5);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(2);
        animation.setDuration(900);
        ivIcon.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent mIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(mIntent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void setEndReciver(){
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
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(endReciver);

    }
}
