package test.com.u3.dontdistraction.activity;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.main.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by ${U3} on 2016/3/10.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class MainActivityTest {
    @Test
    public void create_the_activity()
    {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Assert.assertNotNull(activity);
    }
}
