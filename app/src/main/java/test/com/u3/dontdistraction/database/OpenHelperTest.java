package test.com.u3.dontdistraction.database;

import android.test.AndroidTestCase;
import android.util.Log;

import com.u3.dontdistraction.database.RecordOpenHelper;
import com.u3.dontdistraction.model.Record;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by U3 on 2015/7/4.
 */
public class OpenHelperTest extends AndroidTestCase{
        public void testAdd()
        {
            Record p1 = new Record(true,new Date());
            RecordOpenHelper helper = new RecordOpenHelper(getContext());
            try {
                helper.getRecordDao().create(p1);
                 p1 = new Record(true,new Date());
                helper.getRecordDao().create(p1);
                 p1 = new Record(true,new Date());
                helper.getRecordDao().create(p1);
                 p1 = new Record(true,new Date());
                testList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void testList() {
            RecordOpenHelper helper = new RecordOpenHelper(getContext());
            try {
                List<Record> records = helper.getRecordDao().queryForAll();
                Log.v("ormliteTest", records.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
}
