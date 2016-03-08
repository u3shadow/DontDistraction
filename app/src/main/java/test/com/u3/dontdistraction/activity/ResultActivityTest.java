package test.com.u3.dontdistraction.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.ResultActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by u3 on 16-3-7.
 */
// need note adddb method to run this test
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class ResultActivityTest {


    @Test
    public void create_the_activity() {
        ResultActivity activity = Robolectric.setupActivity(ResultActivity.class);
        Assert.assertNotNull(activity);
    }
    @Test
    public void is_the_imageview_show_at_screen() {

        ResultActivity activity = Robolectric.setupActivity(ResultActivity.class);
        ImageView icon = (ImageView) activity.findViewById(R.id.iv_resultimg);
        Assert.assertEquals(View.VISIBLE, icon.getVisibility());
    }

    @Test
    public void is_start_correct_activity_after_click_notsend() {
        ResultActivity activity = Robolectric.setupActivity(ResultActivity.class);
        Intent expected = new Intent(activity, MainActivity.class);
        activity.findViewById(R.id.bt_notsend).performClick();
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        Assert.assertEquals(expected, actual);
    }
}
