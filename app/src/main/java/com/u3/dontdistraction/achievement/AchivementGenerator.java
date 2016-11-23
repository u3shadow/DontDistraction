package com.u3.dontdistraction.achievement;

import android.content.Context;
import android.content.SharedPreferences;

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
    public  AchivementGenerator(Context context){
        mContext = context;
    }
    public void showAchivement(){
        SharedPreferences sharedPreferences = MyApplication.getApplication().getSharedPreferences(ACP,0);
        int days = TimeRecoder.getDays();
        int time = TimeRecoder.getTime();
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
            for (Achivement achivement:canGet){
                showPop(achivement);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private List<Achivement> getList() throws JSONException {
        SharedPreferences preferences  = mContext.getSharedPreferences("Achivement", Context.MODE_PRIVATE);
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

    }
    private void saveId(String id){

    }
}
