package test.com.u3.dontdistraction.fragment;

import android.content.Intent;
import android.widget.EditText;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.activity.MainActivity;
import com.u3.dontdistraction.activity.ResultActivity;
import com.u3.dontdistraction.activity.ScreenLockActivity;
import com.u3.dontdistraction.fragments.SetTimeFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.util.FragmentTestUtil;
import static org.junit.Assert.*;
/**
 * Created by ${U3} on 2016/3/14.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class SetTimeFragmentTest {
    SetTimeFragment fragment;
    @Before
    public void init(){
        fragment = new SetTimeFragment();
        FragmentTestUtil.startFragment(fragment);
        }
    @Test
    public void start_result_activity_after_click(){
        ((EditText)fragment.getView().findViewById(R.id.et_inputtime)).setText("5");
        fragment.getView().findViewById(R.id.bt_time_set).performClick();
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        Intent expect = new Intent(fragment.getActivity(), ScreenLockActivity.class);
        expect.putExtra("lockTime",5);
        assertEquals(expect,actual);
    }
}
