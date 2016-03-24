package com.u3.dontdistraction.activity;

import android.app.Activity;
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
import android.widget.TextView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.databasedal.RecordDal;
import com.u3.dontdistraction.model.Record;
import com.u3.dontdistraction.util.MsgSender;

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
        Date startTime =(Date)getIntent().getSerializableExtra("startTime");
        Log.i("date123", startTime.toString()+"");
        record = new Record(isTimeEnd,new Date());
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

        if (!isTimeEnd) {
            setSuccessView();
        } else {
            setFailView();
        }
    }

    private void setSuccessView() {
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
        noSendButton.setText("怂了，不发");
    }

    private void setFailView() {
        image.setImageResource(R.drawable.smile);
        text.setText(getResources().getString(R.string.good_result_msg));
        noSendButton.setVisibility(View.GONE);
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
}
