package com.u3.dontdistraction.achievement;

import android.content.SharedPreferences;

import com.u3.dontdistraction.MyApplication;
import com.u3.dontdistraction.util.TimeRecoder;

/**
 * Created by ${U3} on 2016/10/14.
 */

public class AchivementGenerator {
   final private static String ACP = "achivement shared preference";

    public static String getAchivement(){
    SharedPreferences sharedPreferences = MyApplication.getApplication().getSharedPreferences(ACP,0);
        int days = TimeRecoder.getDays();
        String imgId = null;
        boolean hasGet;
        switch (days){
            case 1:
                hasGet = sharedPreferences.getBoolean("1",false);
                if (!hasGet) {
                    imgId = "R.drawable.1";
                    sharedPreferences.edit().putBoolean("1",true).apply();
                }
                break;
            case 5:
                hasGet = sharedPreferences.getBoolean("5",false);
                if (!hasGet) {
                    imgId = "R.drawable.5";
                    sharedPreferences.edit().putBoolean("5",true).apply();
                }
                break;
            case 10:
                hasGet = sharedPreferences.getBoolean("10",false);
                if (!hasGet) {
                    imgId = "R.drawable.10";
                    sharedPreferences.edit().putBoolean("10",true).apply();
                }
                break;
            case 50:
                hasGet = sharedPreferences.getBoolean("50",false);
                if (!hasGet) {
                    imgId = "R.drawable.50";
                    sharedPreferences.edit().putBoolean("50",true).apply();
                }
                break;
            case 100:
                hasGet = sharedPreferences.getBoolean("100",false);
                if (!hasGet) {
                    imgId = "R.drawable.100";
                    sharedPreferences.edit().putBoolean("100",true).apply();
                }
                break;
            case 200:
                hasGet = sharedPreferences.getBoolean("200",false);
                if (!hasGet) {
                    imgId = "R.drawable.200";
                    sharedPreferences.edit().putBoolean("200",true).apply();
                }
                break;
            case 500:
                hasGet = sharedPreferences.getBoolean("500",false);
                if (!hasGet) {
                    imgId = "R.drawable.500";
                    sharedPreferences.edit().putBoolean("500",true).apply();
                }
                break;
            case 1000:
                hasGet = sharedPreferences.getBoolean("1000",false);
                if (!hasGet) {
                    imgId = "R.drawable.1000";
                    sharedPreferences.edit().putBoolean("1000",true).apply();
                }
                break;
        }
        return imgId;
    }
}
