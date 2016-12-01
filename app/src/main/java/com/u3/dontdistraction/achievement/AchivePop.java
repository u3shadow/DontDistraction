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

public class AchivePop extends PopupWindow{
    ImageButton btClose;
    SimpleDraweeView tvImage;
    TextView tvName;

    private View mainView;

    public AchivePop(Context paramActivity, Achivement achivement) {
        super(paramActivity);
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.layout_achivement, null);
        btClose = (ImageButton) mainView.findViewById(R.id.close_button);
        tvImage = (SimpleDraweeView) mainView.findViewById(R.id.AC_Img);
        tvName = (TextView)mainView.findViewById(R.id.AC_Text);
        Uri uri = Uri.parse(achivement.imgUrl);
        tvImage.setImageURI(uri);
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AchivePop.this.dismiss();
            }
        });

        tvName.setText(achivement.title);
        setContentView(mainView);
        //设置宽度高度

        setWidth(mainView.getWidth());

        setHeight(mainView.getHeight());
        //设置显示隐藏动画
        //        setAnimationStyle();
    }

}
