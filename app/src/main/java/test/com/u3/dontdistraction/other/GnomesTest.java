package test.com.u3.dontdistraction.other;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.screenlock.page.ScreenLockActivity;
import com.u3.dontdistraction.screenlock.gnome.Gnomes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by ${U3} on 2016/3/14.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class GnomesTest {
    ScreenLockActivity activity;
    @Before
    public void init(){
        activity  = Robolectric.setupActivity(ScreenLockActivity.class);
    }
    @Test
    public void getGnome(){
        Gnomes gnomes = new Gnomes(activity.getApplicationContext());
        String gnome = gnomes.getGnome();
        System.out.print(gnome);
        assertNotNull(gnome);
    }
}
