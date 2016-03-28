package androidTest.dbtest;

import android.test.AndroidTestCase;

import com.u3.dontdistraction.databasedal.RecordDal;
import com.u3.dontdistraction.model.Record;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by u3 on 16-3-11.
 */
public class RecordDalTest extends AndroidTestCase{
    public void test_add_records() throws SQLException {
        RecordDal dal = new RecordDal(getContext());
        Record record = new Record(false,new Date(),new Date(),1,2);
        int sizeBefor = dal.getList().size();
        dal.addRecord(record);
        int sizeAfter = dal.getList().size();
        int actual  = sizeAfter - sizeBefor;
        int expect  = 1;
        assertEquals(expect,actual);
        dal.delete(record);
    }

     public void test_delete_records() throws SQLException {
        RecordDal dal = new RecordDal(getContext());
        Record record = new Record(false,new Date(),new Date(),1,2);
         dal.addRecord(record);
         int sizeBefor = dal.getList().size();
         dal.delete(record);
        int sizeAfter = dal.getList().size();
        int actual  = sizeAfter - sizeBefor;
        int expect  = -1;
        assertEquals(expect,actual);
    }
}
