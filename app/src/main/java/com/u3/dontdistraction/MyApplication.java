package com.u3.dontdistraction;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Fresco.initialize(this);
    }
}
