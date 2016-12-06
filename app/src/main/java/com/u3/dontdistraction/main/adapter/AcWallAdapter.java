package com.u3.dontdistraction.main.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.achievement.Achivement;
import com.u3.dontdistraction.achievement.ShowAchivePop;

import java.util.List;

/**
 * Created by ${U3} on 2016/12/5.
 */

public class AcWallAdapter extends BaseAdapter {
    List<Achivement> list;
    Context context;
    SharedPreferences preferences;
    public AcWallAdapter(Context context,List<Achivement> list){
        preferences  = context.getSharedPreferences("Achivement", Context.MODE_PRIVATE);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convert1View, final ViewGroup parent) {
       View convertView = LayoutInflater.from(context).inflate(R.layout.layout_acwallitem,null);
        SimpleDraweeView pic = (SimpleDraweeView)convertView.findViewById(R.id.AC_Img);
        TextView textView = (TextView) convertView.findViewById(R.id.AC_Text);
        ImageView getView = (ImageView) convertView.findViewById(R.id.get);
        ImageView noGetView = (ImageView) convertView.findViewById(R.id.noget);
        final Achivement achivement = list.get(position);
        Uri uri = Uri.parse(achivement.imgUrl);
        pic.setImageURI(uri);
        textView.setText(achivement.title);
        if (preferences.getString(achivement.id,"").equals("")){
            noGetView.setVisibility(View.VISIBLE);
        }else{
            getView.setVisibility(View.VISIBLE);
        }
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAchivePop pop = new ShowAchivePop(context,achivement,parent);
                pop.showAtLocation(parent,Gravity.CENTER,0,0);
            }
        });
        return convertView;
    }
}
