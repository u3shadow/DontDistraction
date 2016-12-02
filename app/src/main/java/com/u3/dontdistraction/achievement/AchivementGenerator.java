package com.u3.dontdistraction.achievement;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.google.gson.Gson;
import com.u3.dontdistraction.MyApplication;
import com.u3.dontdistraction.util.TimeRecoder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${U3} on 2016/10/14.
 */

public class AchivementGenerator {
    final private static String ACP = "achivement shared preference";
    Context mContext;
    SharedPreferences preferences;
    int days,time;
    List<Achivement> canGet;
    View parentView;
    public  AchivementGenerator(Context context, View parentView){
        mContext = context;
        preferences = MyApplication.getApplication().getSharedPreferences(ACP,0);
        this.parentView = parentView;
        days = TimeRecoder.getDays();
        time = TimeRecoder.getTime();
    }
    public void showAchivement(){
        canGet = getCanShowAchivementList();
        for (Achivement achivement:canGet){
            if (!idIsEx(achivement.id)) {
                showPop(achivement);
                saveId(achivement.id);
            }
        }
    }
    public List<Achivement> getCanShowAchivementList() {
        List<Achivement> canGet = new ArrayList<>();
        try {
            List<Achivement> achivements = getList();
            for (Achivement achivement:achivements){
                switch (achivement.acType){
                    case 1:
                        if (days >= achivement.acCond){
                            canGet.add(achivement);
                        }
                        break;
                    case 2:
                        if (time >= achivement.acCond){
                            canGet.add(achivement);
                        }
                        break;
                    default:break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return canGet;
    }

    private boolean idIsEx(String id){
        String savedId = preferences.getString(id,"");
        if (savedId.equals("")){
            return false;
        }else{
            return true;
        }
    }
    public List<Achivement> getList() throws JSONException {
        preferences  = mContext.getSharedPreferences("Achivement", Context.MODE_PRIVATE);
        String json = preferences.getString("Achivement","");
        List<Achivement> list = null;
        if(!json.equals(""))
        {
            JSONArray arr = new JSONArray(json);
            Gson gson = new Gson();
            list = new ArrayList<Achivement>();
            for(int i = 0;i < arr.length();i++)
            {
                list.add(gson.fromJson(arr.get(i).toString(),Achivement.class));
            }
        }
        return list;
    }
    private void showPop(Achivement achivement){
        AchivePop pop = new AchivePop(mContext,achivement,parentView);
        Log.i("pop","show");
        pop.showAtLocation(parentView, Gravity.CENTER,0,0);
    }
    private void saveId(String id){
        preferences.edit().putString(id,id).apply();
    }
}
