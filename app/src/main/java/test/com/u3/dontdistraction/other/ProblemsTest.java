package test.com.u3.dontdistraction.other;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.activity.ScreenLockActivity;
import com.u3.dontdistraction.model.Problem;
import com.u3.dontdistraction.other.Problems;

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
public class ProblemsTest {
    ScreenLockActivity activity;
    @Before
    public void init(){
        activity  = Robolectric.setupActivity(ScreenLockActivity.class);
    }
    @Test
    public void getProblem(){
        Problems problems = new Problems(activity.getApplicationContext());
        String problem = problems.getProblem();
        System.out.print(problem);
        assertNotNull(problem);
    }
}
