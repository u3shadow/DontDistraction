package test.com.u3.dontdistraction.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.ResultActivity;
import com.u3.dontdistraction.activity.ScreenLockActivity;
import com.u3.dontdistraction.activity.SplashActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by ${U3} on 2016/3/10.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class ScreenLockActivityTest {
    @Test
    public void create_the_activity()
    {
        ScreenLockActivity activity = Robolectric.setupActivity(ScreenLockActivity.class);
        Assert.assertNotNull(activity);
    }
    @Test
    public void is_the_msg_show_at_screen()
    {
        ScreenLockActivity activity = Robolectric.setupActivity(ScreenLockActivity.class);
        TextView msg = (TextView)activity.findViewById(R.id.tv_msg);
        Assert.assertEquals(View.VISIBLE, msg.getVisibility());
    }
    @Test
    public void is_start_correct_activity()
    {
        ScreenLockActivity activity = Robolectric.setupActivity(ScreenLockActivity.class);
        Intent expected = new Intent(activity, ResultActivity.class);
        expected.putExtra("isTimeEnd",false);
        activity.findViewById(R.id.bt_endlock).performClick();
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(expected,actual);
    }
}
