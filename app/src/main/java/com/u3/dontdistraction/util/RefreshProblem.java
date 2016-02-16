package com.u3.dontdistraction.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.u3.dontdistraction.other.Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by u3 on 2015/11/7.
 */
public class RefreshProblem {
    private final Context mContext;
    public RefreshProblem(Context context)
    {
        mContext = context;
        }
    public void refresh()
    {
        RefreshTask task = new RefreshTask();
        task.execute();
        }
    private class RefreshTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(mContext,"开始更新",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           String urlStr = "http://7xo3yv.com1.z0.glb.clouddn.com/problem.txt";
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
                    Log.i("problem", line);
                }
                SharedPreferences preferences = mContext.getSharedPreferences(Problems.PROBLEM, 0);
                preferences.edit().putString(Problems.PROBLEM,stringBuffer.toString()).apply();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(mContext,"题目更新完成",Toast.LENGTH_LONG).show();
        }
    }
}
