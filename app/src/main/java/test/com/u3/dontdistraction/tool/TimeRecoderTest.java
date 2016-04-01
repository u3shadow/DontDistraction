package test.com.u3.dontdistraction.tool;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.util.TimeRecoder;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by ${U3} on 2016/4/1.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class TimeRecoderTest {
    MainActivity  activity;
    @Before
    public void init(){
        activity  = Robolectric.setupActivity(MainActivity.class);
         TimeRecoder.initRecord(activity);
    }
    @Test
    public void addTime(){
        int expect = 5;
        int actual = TimeRecoder.addTime(5);
        assertEquals(expect,actual);
    }
}

