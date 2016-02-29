/*
package test.com.u3.dontdistraction.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.SplashActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

*/
/**
 * Created by ${U3} on 2016/2/17.
 *//*

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class SplashActivityTest {

    @Test
    public void create_the_activity()
    {
        SplashActivity activity = Robolectric.setupActivity(SplashActivity.class);
        Assert.assertNotNull(activity);
    }
    @Test
    public void is_the_icon_show_at_screen()
    {
        SplashActivity activity = Robolectric.setupActivity(SplashActivity.class);
        ImageView icon = (ImageView)activity.findViewById(R.id.iv_icon);
        Assert.assertEquals(View.VISIBLE, icon.getVisibility());
    }
    @Test
    public void is_start_correct_activity()
    {
        SplashActivity activity = Robolectric.setupActivity(SplashActivity.class);
        Intent expected = new Intent(activity, MainActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(expected,actual);
    }
}
*/
