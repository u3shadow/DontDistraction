package com.u3.dontdistraction.result.page;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.achievement.AchivementGenerator;
import com.u3.dontdistraction.main.MainActivity;
import com.u3.dontdistraction.record.databasedal.RecordDal;
import com.u3.dontdistraction.screenlock.page.ScreenLockActivity;
import com.u3.dontdistraction.screenlock.model.Record;
import com.u3.dontdistraction.util.MsgSender;
import com.u3.dontdistraction.util.TimeRecoder;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by U3 on 2015/5/29.
 */
public class ResultActivity extends AppCompatActivity {
    private Button okButton;
    private Button noSendButton;
    private MsgSender sender;
    private TextView text;
    private ImageView image;
    private RecordDal recordDal;
    private Record record;
    private Boolean isTimeEnd;
    private RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initData();
        addRecord();
        initView();
        initListener();
        setView();
        setEndReciver();
        showAchivement();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    private void initData() {
        sender = new MsgSender(this);
        ScreenLockActivity.isTimed = false;
        recordDal = new RecordDal(this);
        isTimeEnd = getIntent().getBooleanExtra("isTimeEnd",false);
        Date startTime =new Date();
        startTime.setTime(getIntent().getLongExtra("startTime", -1));
        Date now = new Date();
        Long duration = now.getTime() - startTime.getTime();
        int durationMinu = (int)(duration/(1000*60));
        int durationSec = (int)(duration - durationMinu*60000)/1000;
        Log.i("date123", durationMinu+" : "+durationSec);
        record = new Record(isTimeEnd,startTime,now,durationMinu,durationSec);
        TimeRecoder.addTime(durationMinu);
    }

    private void addRecord() {
        try {
            recordDal.addRecord(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        okButton = (Button) findViewById(R.id.bt_result);
        noSendButton = (Button) findViewById(R.id.bt_notsend);
        text = (TextView) findViewById(R.id.tv_result);
        image = (ImageView) findViewById(R.id.iv_resultimg);
        parentLayout = (RelativeLayout)findViewById(R.id.ll);

    }

    private void initListener() {
        noSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });
    }

    private void setView() {

        if (isTimeEnd) {
            setSuccessView();
        } else {
            setFailView();
        }
    }

    private void setFailView() {
        image.setImageResource(R.drawable.sad);
        text.setText(getResources().getString(R.string.bad_result_msg));
        okButton.setText(getResources().getString(R.string.badresult));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sender.sendMsg();
                Intent mIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });
        noSendButton.setText(getResources().getString(R.string.songle));
    }

    private void setSuccessView() {
        image.setImageResource(R.drawable.smile);
        text.setText(getResources().getString(R.string.good_result_msg));
        if (TimeRecoder.canRecord()&&TimeRecoder.hadNotRecord()){
            noSendButton.setText(getResources().getString(R.string.addfootprint));
            noSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntentfp = new Intent(ResultActivity.this,MainActivity.class);
                    mIntentfp.putExtra("isFootPrint",true);
                    startActivity(mIntentfp);
                    finish();
                }
            });
        }else{
            noSendButton.setVisibility(View.GONE);
        }
        okButton.setText(getResources().getString(R.string.useagain));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });
    }

    private void setEndReciver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction("com.u3.end");
        registerReceiver(endReciver, filter);
    }
    private final BroadcastReceiver endReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(endReciver);
    }
    private void showAchivement(){
        AchivementGenerator generator = new AchivementGenerator(this,parentLayout);
        generator.showAchivement();
    }
}
