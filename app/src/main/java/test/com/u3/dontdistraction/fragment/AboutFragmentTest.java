package test.com.u3.dontdistraction.fragment;

import android.view.View;
import android.widget.TextView;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.fragments.AboutFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.*;

/**
 * Created by ${U3} on 2016/3/14.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class AboutFragmentTest {
    AboutFragment  fragment;
    @Before
    public void init(){
        fragment = new AboutFragment();
        FragmentTestUtil.startFragment(fragment);
    }
    @Test
    public void test_have_aboutmsg(){
        TextView aboutmsg = (TextView)fragment.getView().findViewById(R.id.tv_about);
        assertNotNull(aboutmsg);
    }
    @Test
    public void test_is_aboutmsg_show(){
        TextView aboutmsg = (TextView)fragment.getView().findViewById(R.id.tv_about);
        int actual = aboutmsg.getVisibility();
        int expect = View.VISIBLE;
        assertEquals(expect, actual);
    }
}
