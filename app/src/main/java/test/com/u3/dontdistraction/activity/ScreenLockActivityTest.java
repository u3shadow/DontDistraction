package test.com.u3.dontdistraction.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.result.ResultActivity;
import com.u3.dontdistraction.screenlock.page.ScreenLockActivity;

import static org.junit.Assert.*;
import org.junit.Before;
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
    ScreenLockActivity activity;
    @Before
    public void inittest(){
        activity = Robolectric.setupActivity(ScreenLockActivity.class);
    }
    @Test
    public void create_the_activity()
    {
       assertNotNull(activity);
    }
    @Test
    public void is_the_msg_show_at_screen()
    {
        TextView msg = (TextView)activity.findViewById(R.id.tv_msg);
       assertEquals(View.VISIBLE, msg.getVisibility());
    }
    @Test
    public void is_start_correct_activity()
    {
        Intent expected = new Intent(activity, ResultActivity.class);
        expected.putExtra("isTimeEnd",false);
        activity.findViewById(R.id.bt_endlock).performClick();
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expected,actual);
    }
    @Test
    public void has_gnome(){
      TextView gnome = (TextView)activity.findViewById(R.id.gnome);
       assertNotNull(gnome);
    }
    @Test
    public void is_gnome_show(){
        TextView gnome = (TextView)activity.findViewById(R.id.gnome);
        int expect = View.VISIBLE;
        int actual  = gnome.getVisibility();
        assertEquals(expect, actual);
    }
    @Test
    public void open_problem_after_click_open_button(){
        activity.findViewById(R.id.start).performClick();
       int actual =  activity.findViewById(R.id.tv_problem).getVisibility();
        int expect = View.VISIBLE;
        assertEquals(expect, actual);
    }
}
