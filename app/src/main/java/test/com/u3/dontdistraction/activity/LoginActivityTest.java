package test.com.u3.dontdistraction.activity;

import android.content.Intent;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.activity.LoginActivity;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.SplashActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by ${U3} on 2016/2/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class LoginActivityTest {
    @Test
    public void is_jump_to_weibo_login_activity()
    {
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        Assert.assertNotNull(activity);
    }

}
