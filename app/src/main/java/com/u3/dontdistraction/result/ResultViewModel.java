package com.u3.dontdistraction.result;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.u3.dontdistraction.BR;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.main.MainActivity;
import com.u3.dontdistraction.record.databasedal.RecordDal;
import com.u3.dontdistraction.screenlock.model.Record;
import com.u3.dontdistraction.screenlock.page.ScreenLockActivity;
import com.u3.dontdistraction.util.TimeRecoder;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by u3-linux on 11/9/17.
 */

public class ResultViewModel {
    private Activity activity;
    private ViewDataBinding binding;
    public ResultEntity resultEntity;
    private boolean isTimeEnd;
    public ResultViewModel(Activity activity,ViewDataBinding binding){
        resultEntity = new ResultEntity();
        resultEntity.setLearnResultString(activity.getResources().getString(R.string.good_result_msg));
        resultEntity.setOkButtonString(activity.getResources().getString(R.string.useagain));
        resultEntity.setNoButtonString(activity.getResources().getString(R.string.not_send));
        resultEntity.setSuccessLearn(true);
        this.activity = activity;
        this.binding = binding;
        this.binding.setVariable(BR.vMode,this);
        initData();
        setView();
    }

    private void setView() {

        resultEntity.setSuccessLearn(isTimeEnd);
        if (isTimeEnd) {
            setSuccessEntity();
        } else {
            setFailEntity();
        }
    }
    public void okClickEvent(View view){
        Intent mIntent = new Intent(activity, MainActivity.class);
        if (TimeRecoder.canRecord()&&TimeRecoder.hadNotRecord()) {
            mIntent.putExtra("isFootPrint",true);
        }
        activity.startActivity(mIntent);
        activity.finish();
    }

    private void setSuccessEntity() {
        resultEntity.setLearnResultString(activity.getResources().getString(R.string.good_result_msg));
        if (TimeRecoder.canRecord()&&TimeRecoder.hadNotRecord()) {
            resultEntity.setOkButtonString(activity.getResources().getString(R.string.addfootprint));
        }
    }

    private void setFailEntity() {
        resultEntity.setLearnResultString(activity.getResources().getString(R.string.bad_result_msg));
    }
  public void initData() {
        ScreenLockActivity.isTimed = false;
        RecordDal recordDal = new RecordDal(activity);
        isTimeEnd = activity.getIntent().getBooleanExtra("isTimeEnd",false);
        Date startTime =new Date();
        startTime.setTime(activity.getIntent().getLongExtra("startTime", -1));
        Date now = new Date();
        Long duration = now.getTime() - startTime.getTime();
        int durationMinu = (int)(duration/(1000*60));
        int durationSec = (int)(duration - durationMinu*60000)/1000;
        Log.i("date123", durationMinu+" : "+durationSec);
        Record record = new Record(isTimeEnd,startTime,now,durationMinu,durationSec);
        TimeRecoder.addTime(durationMinu);
        try {
            recordDal.addRecord(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
