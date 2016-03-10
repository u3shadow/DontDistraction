package test.com.u3.dontdistraction.activity;

import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.ResultActivity;
import com.u3.dontdistraction.activity.ScreenLockActivity;
import com.u3.dontdistraction.fragments.SetTimeFragment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

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
