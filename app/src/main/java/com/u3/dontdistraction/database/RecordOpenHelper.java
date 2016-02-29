package com.u3.dontdistraction.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.u3.dontdistraction.model.Record;

import java.sql.SQLException;

/**
 * Created by U3 on 2015/7/4.
 */
public class RecordOpenHelper extends OrmLiteSqliteOpenHelper {
    private static Dao<Record,Integer> recordDao;
   public RecordOpenHelper(Context context)
   {
       super(context,"ddphone",null,1);
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Record.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
    public Dao<Record,Integer> getRecordDao() throws SQLException {
        if(recordDao == null)
        {
            recordDao = getDao(Record.class);
        }
        return recordDao;
    }

}
