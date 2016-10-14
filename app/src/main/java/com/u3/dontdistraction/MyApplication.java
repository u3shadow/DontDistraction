package com.u3.dontdistraction;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MyApplication extends Application {
    private static Context instance;
    @Override
    public void onCreate() {
        super.onCreate();
 //       LeakCanary.install(this);
        instance = this;
        Fresco.initialize(this);
    }
    public static Context getApplication(){
        return instance;
    }
}
