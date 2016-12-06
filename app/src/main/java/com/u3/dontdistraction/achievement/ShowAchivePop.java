package com.u3.dontdistraction.achievement;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.u3.dontdistraction.R;

/**
 * Created by ${U3} on 2016/11/23.
 */

public class ShowAchivePop extends PopupWindow{
    ImageButton btClose;
    SimpleDraweeView tvImage;
    TextView tvName,tvDes;

    private View mainView;

    public ShowAchivePop(Context paramActivity, Achivement achivement,View parent) {
        super(paramActivity);
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.layout_showachivement, null);
        btClose = (ImageButton) mainView.findViewById(R.id.close_button);
        tvImage = (SimpleDraweeView) mainView.findViewById(R.id.AC_Img);
        tvName = (TextView)mainView.findViewById(R.id.AC_Text);
        tvDes = (TextView)mainView.findViewById(R.id.AC_des);
        Uri uri = Uri.parse(achivement.imgUrl);
        tvImage.setImageURI(uri);
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAchivePop.this.dismiss();
            }
        });
        tvDes.setText(achivement.id);
        tvName.setText(achivement.title);
        setContentView(mainView);
        setWidth(parent.getWidth());
        setHeight(parent.getHeight());
    }


}
