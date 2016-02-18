package test.com.u3.dontdistraction.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.ResultActivity;
import com.u3.dontdistraction.activity.SplashActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by ${U3} on 2016/2/18.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
public class ResultActivityTest {
    ResultActivity activity;

    @Before
    public void create_activity_instance() {
        activity = Robolectric.setupActivity(ResultActivity.class);
    }

    @Test
    public void create_the_activity() {
        Assert.assertNotNull(activity);
    }

    @Test
    public void is_the_imageview_show_at_screen() {
        ImageView icon = (ImageView) activity.findViewById(R.id.iv_resultimg);
        Assert.assertEquals(View.VISIBLE, icon.getVisibility());
    }

    @Test
    public void is_start_correct_activity_after_click_notsend() {
        Intent expected = new Intent(activity, MainActivity.class);
        activity.findViewById(R.id.bt_notsend).performClick();
        Intent actual =  ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(expected,actual);
    }
}
