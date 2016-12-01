package test.com.u3.dontdistraction.other;

import android.content.SharedPreferences;
import android.widget.RelativeLayout;

import com.u3.dontdistraction.BuildConfig;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.achievement.Achivement;
import com.u3.dontdistraction.achievement.AchivementGenerator;
import com.u3.dontdistraction.main.MainActivity;
import com.u3.dontdistraction.util.RefreshAchivement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by ${U3} on 2016/3/14.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class AchivementTest {
    MainActivity activity;
    RelativeLayout layout;
    @Before
    public void init(){
        activity  = Robolectric.setupActivity(MainActivity.class);
        layout = (RelativeLayout)activity.findViewById(R.id.parent);
    }
    @Test
    public void get_ac_json_test(){
        RefreshAchivement refreshAchivement = new RefreshAchivement(activity);
        refreshAchivement.getAc();
        SharedPreferences preferences  = activity.getSharedPreferences("Achivement",0);
        String json = preferences.getString("Achivement","");
        assertTrue(!json.equals(""));
    }
    @Test
    public void get_ac_list_test() throws InvocationTargetException, IllegalAccessException {
          Method[] ma = AchivementGenerator.class.getDeclaredMethods();
          for(Method m :ma)
          {
              if(m.getName().equals("getList"))
              {
                  m.setAccessible(true);
                  AchivementGenerator t = new AchivementGenerator(activity,layout);
                  List<Achivement> s =(List)m.invoke(t,null);
                  int size = s.size();
                  System.out.print(size);
                  assertTrue(size > 0);
              }
          }
    }
    @Test
    public void get_can_get_list_test() throws InvocationTargetException, IllegalAccessException {
          Method[] ma = AchivementGenerator.class.getDeclaredMethods();
          for(Method m :ma)
          {
              if(m.getName().equals("getCanShowAchivementList"))
              {
                  m.setAccessible(true);
                  AchivementGenerator t = new AchivementGenerator(activity,layout);
                  List<Achivement> s =(List)m.invoke(t,null);
                  int size = s.size();
                  System.out.print(size);
                  assertTrue(size > 0);
              }
          }
    }
     @Test
    public void id_ext_test() throws InvocationTargetException, IllegalAccessException {
          Method[] ma = AchivementGenerator.class.getDeclaredMethods();
          for(Method m :ma)
          {
              if(m.getName().equals("idIsEx"))
              {
                  m.setAccessible(true);
                  AchivementGenerator t = new AchivementGenerator(activity,layout);
                  boolean s =(boolean) m.invoke(t,"testid");
                  System.out.print(s);
                  assertTrue(!s);
              }
          }
    }
}
