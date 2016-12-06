package com.u3.dontdistraction.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.u3.dontdistraction.achievement.Achivement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by u3 on 2015/11/7.
 */
public class RefreshAchivement {
    private final Context mContext;
    List<Achivement> list;
    public RefreshAchivement(Context context)
    {
        mContext = context;
        }
    public void getAc()
    {
        RefreshTask task = new RefreshTask();
        task.execute();
        }
    private class RefreshTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {

           String urlStr = "http://7xo3yv.com1.z0.glb.clouddn.com/achivement.txt"+"?"+Math.random();
            URL url= null;
            try {
                url = new URL(urlStr);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setRequestProperty("contentType", "GBK");
                InputStream input=conn.getInputStream();
                BufferedReader in=new BufferedReader(new InputStreamReader(input,"GBK"));
                String line;
                StringBuffer stringBuffer=new StringBuffer();
                while((line=in.readLine())!=null){
                    stringBuffer.append(line);
                    Log.i("ac", line);
                }
                SharedPreferences preferences = mContext.getSharedPreferences("Achivement", 0);
                preferences.edit().putString("Achivement",stringBuffer.toString()).apply();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }
}
