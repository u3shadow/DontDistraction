package test.com.u3.dontdistraction.database;

import android.test.AndroidTestCase;
import android.util.Log;

import com.u3.dontdistraction.database.RecordOpenHelper;
import com.u3.dontdistraction.model.Record;

import junit.framework.Assert;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by U3 on 2015/7/4.
 */
public class RecordOpenHelperTest extends AndroidTestCase{
        public void testAdd()
        {
            RecordOpenHelper helper = new RecordOpenHelper(getContext());
            try {
                List<Record> recordsOld = helper.getRecordDao().queryForAll();
                int size = recordsOld.size();
                for(int i = 0;i < 2000 ;i++)
                {
                    Record  p1 = new Record(true,new Date());
                    helper.getRecordDao().create(p1);
                }
                List<Record> records = helper.getRecordDao().queryForAll();
                Assert.assertEquals("Not right number record",2000,records.size() - size);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
