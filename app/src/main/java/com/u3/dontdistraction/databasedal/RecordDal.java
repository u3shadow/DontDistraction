package com.u3.dontdistraction.databasedal;

import android.content.Context;
import android.database.Cursor;

import com.u3.dontdistraction.database.RecordOpenHelper;
import com.u3.dontdistraction.model.Record;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by U3 on 2015/7/4.
 */
public class RecordDal {
    private Record record;
    private RecordOpenHelper openHelper;
    public RecordDal(Context context)
    {
        openHelper = new RecordOpenHelper(context);
    }
    public int addRecord(Record record) throws SQLException {
        this.record = record;
       return openHelper.getRecordDao().create(this.record);
    }
    public List<Record> getList()
    {
        List<Record> list = new ArrayList<Record>();
        try {
            list = openHelper.getRecordDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Record> getUpdate(List<Record> list)
    {
      /*  List<Record> list1 = new ArrayList<Record>();
        try {
            list1 = openHelper.getRecordDao().is();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return list;
    }
}
