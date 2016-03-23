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
    private Date date;

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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @DatabaseField(columnName = "learntime")
    private int learntime;
    @DatabaseField(columnName = "startime")
    private Date starTime;
    @DatabaseField(columnName = "endtime")
    private Date endTime;
    public Record()
    {}
    public Record(boolean isSuccess,Date date)
    {
        this.isSuccess = isSuccess;
        this.date = date;
    }
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
