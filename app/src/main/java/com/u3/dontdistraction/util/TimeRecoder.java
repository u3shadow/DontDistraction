package com.u3.dontdistraction.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by ${U3} on 2016/4/1.
 */
public class TimeRecoder {
    private static  String timeRecord = "time record";
    private static SharedPreferences sharedPreferences;
    public static void initRecord(Context context){
      sharedPreferences = context.getSharedPreferences(timeRecord,0);
        Date date = new Date();
       String lastDay = sharedPreferences.getString("day","");
        if (!DateTools.getDay(date).equals(lastDay)){
            sharedPreferences.edit().putString("day",DateTools.getDay(date));
            sharedPreferences.edit().putInt("time",0);
            sharedPreferences.edit().apply();
        }
    }
    public static int addTime(int time){
        int lastTime = sharedPreferences.getInt("time",0);
       sharedPreferences.edit().putInt("time",lastTime+time);
       sharedPreferences.edit().apply();
        return lastTime+time;
    }
}
