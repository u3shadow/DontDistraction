package com.u3.dontdistraction.databasedal;

import android.content.Context;

import com.u3.dontdistraction.database.RecordOpenHelper;
import com.u3.dontdistraction.model.Record;

import java.sql.SQLException;

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
}
