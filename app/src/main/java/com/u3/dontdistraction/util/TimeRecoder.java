package com.u3.dontdistraction.util;

import android.content.Context;
import android.content.SharedPreferences;

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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!DateTools.getDay(date).equals(lastDay)){
            editor.putString("day",DateTools.getDay(date));
            editor.putInt("time",0);
            editor.putBoolean("had",true);
            editor.apply();
        }
    }
    public static int addTime(int time){
        int lastTime = sharedPreferences.getInt("time",0);
       sharedPreferences.edit().putInt("time",lastTime+time).apply();
        return lastTime+time;
    }
    public static boolean canRecord(){
       int time  = sharedPreferences.getInt("time",0);
        if (time >= 10){
            return true;
        }else
        {
            return false;
        }
    }
    public static boolean record(){
        int days =  sharedPreferences.getInt("days",0);
        days += 1;
        if (hadNotRecord()) {
            sharedPreferences.edit().putInt("days", days).apply();
            sharedPreferences.edit().putBoolean("had",false).apply();
            return true;
        }
        return false;
    }
    public static int getTime(){
        return sharedPreferences.getInt("time",0);
    }
    public static int getDays(){
         return sharedPreferences.getInt("days",0);
    }
    public static boolean hadNotRecord(){
        return sharedPreferences.getBoolean("had",false);
    }
}
