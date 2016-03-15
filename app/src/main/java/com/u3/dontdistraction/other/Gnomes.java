package com.u3.dontdistraction.other;



import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.u3.dontdistraction.model.GnomeEntity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by U3 on 2015/5/29.
 */
public class Gnomes {
    public static final String Gnome = "GnomeEntity";
    private int gnomeNumMax;
    private int gnomeNumMin = 0;
    private int gnomeNumber = 0;
    private final Context mContext;
    private  List<GnomeEntity> gnomeList = new ArrayList<GnomeEntity>() {
        {
            add(new GnomeEntity("Behind every successful man there's a lot u unsuccessful years.- Bob Brown"));
            add(new GnomeEntity("I think success has no rules, but you can learn a lot from failure.- Jean Kerr" ));
            add(new GnomeEntity("There are no secrets to success. It is the result of preparation, hard work, and learning from failure. - Colin L. Powell " ));
            add(new GnomeEntity("Failure is the mother of success. -- Thomas Paine" ));
            add(new GnomeEntity("There is no such thing as a great talent without great will - power. -- Balzac"));
            add(new GnomeEntity("You have to believe in yourself. That's the secret of success. -- Charles Chaplin"));
            add(new GnomeEntity("Cease to struggle and you cease to live. -- Thomas Carlyle" ));
            add(new GnomeEntity("The unexamined life is not worth living. -- Socrates" ));
        }
    };
    private void setGnomeist(List<GnomeEntity> gnomeList)
    {
        this.gnomeList = gnomeList;
    }
public Gnomes(Context context)
{
    mContext = context;
    try {
        getList();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    Random random = new Random();
    gnomeNumber = gnomeList.size();
    int gnoNum = random.nextInt()%(gnomeNumber);
    if(gnoNum < 0)
    {
        gnoNum *= -1;
    }
    gnomeNumber = gnoNum;
}
    private void getList() throws JSONException {
        SharedPreferences preferences  = mContext.getSharedPreferences(Gnomes.Gnome, Context.MODE_PRIVATE);
        String json = preferences.getString(Gnomes.Gnome,"");
        if(!json.equals(""))
        {
            JSONArray arr = new JSONArray(json);
            Gson gson = new Gson();
            List<GnomeEntity> list = new ArrayList<GnomeEntity>();
            for(int i = 0;i < arr.length();i++)
            {
                list.add(gson.fromJson(arr.get(i).toString(),GnomeEntity.class));
            }
            setGnomeist(list);
        }
    }
    public String getGnome() {
        return gnomeList.get(gnomeNumber).getGnomeString();
    }

}
