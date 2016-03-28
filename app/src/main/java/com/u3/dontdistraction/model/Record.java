package com.u3.dontdistraction.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by U3 on 2015/7/4.
 */
@DatabaseTable(tableName = "record")
public class Record {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "issuccess")
    private boolean isSuccess;
    @DatabaseField(columnName = "recorddate")
    private Date endTime;

    public int getLearntime() {
        return learntime;
    }

    public void setLearntime(int learntime) {
        this.learntime = learntime;
    }

    public Date getStarTime() {
        return starTime;
    }

    public void setStarTime(Date starTime) {
        this.starTime = starTime;
    }

    @DatabaseField(columnName = "learntime")
    private int learntime;
    @DatabaseField(columnName = "startime")
    private Date starTime;
    @DatabaseField(columnName = "minu")
    private int minu;
    @DatabaseField(columnName = "sec")
    private int sec;
    public Record()
    {}

    public int getMinu() {
        return minu;
    }

    public void setMinu(int minu) {
        this.minu = minu;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Record(boolean isSuccess, Date starTime, Date endTime, int min, int sec)
    {
        this.isSuccess = isSuccess;
        this.endTime = endTime;
        this.starTime = starTime;

        minu = min;
        this.sec = sec;
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
