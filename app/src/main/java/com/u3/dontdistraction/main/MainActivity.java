package com.u3.dontdistraction.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.achievement.AchivementGenerator;
import com.u3.dontdistraction.main.fragments.AboutFragment;
import com.u3.dontdistraction.main.fragments.AcWallFragment;
import com.u3.dontdistraction.main.fragments.FootPrintFragment;
import com.u3.dontdistraction.main.fragments.SetTimeFragment;
import com.u3.dontdistraction.record.page.RecordFragment;
import com.u3.dontdistraction.util.RefreshAchivement;
import com.u3.dontdistraction.util.RefreshGnome;
import com.u3.dontdistraction.util.RefreshProblem;
import com.u3.dontdistraction.util.TimeRecoder;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.iv_acwall)
    ImageView ivAcwall;
    @Bind(R.id.Tv_acwall)
    TextView TvAcwall;
    @Bind(R.id.ll_acwall)
    LinearLayout llAcwall;
    @Bind(R.id.iv_out)
    ImageView ivOut;
    @Bind(R.id.tv_out)
    TextView tvOut;
    private Fragment aboutFragment;
    private Fragment setTimeFragment;
    private Fragment recordFragment;
    private Fragment footFragment;
    private Fragment wallFragment;
    private List<LinearLayout> llList;
    private DrawerLayout drawerLayout;
    @Bind(R.id.Fl_content)
    FrameLayout FlContent;
    @Bind(R.id.iv_headpic)
    SimpleDraweeView ivHeadpic;
    @Bind(R.id.iv_settime)
    ImageView ivSettime;
    @Bind(R.id.Tv_settime)
    TextView TvSettime;
    @Bind(R.id.tv_juzi)
    TextView tvJuzi;
    @Bind(R.id.ll_time_set)
    LinearLayout llTimeSet;
    @Bind(R.id.iv_record)
    ImageView ivRecord;
    @Bind(R.id.Tv_record)
    TextView TvRecord;
    @Bind(R.id.ll_record)
    LinearLayout llRecord;
    @Bind(R.id.iv_refresh)
    ImageView ivRefresh;
    @Bind(R.id.Tv_refresh)
    TextView TvRefresh;
    @Bind(R.id.ll_refresh)
    LinearLayout llRefresh;
    @Bind(R.id.ll_footprint)
    LinearLayout llfootprint;
    @Bind(R.id.iv_about)
    ImageView ivAbout;
    @Bind(R.id.tv_about)
    TextView tvAbout;
    @Bind(R.id.ll_about)
    LinearLayout llAbout;
    @Bind(R.id.iv_footprint)
    ImageView ivfoot;
    @Bind(R.id.Tv_footprint)
    TextView tvfoot;
    @Bind(R.id.layout_main)
    DrawerLayout layoutMain;
    @Bind(R.id.fba)
    FrameLayout fba;
    @Bind(R.id.ll_out)
    LinearLayout logoutLayout;
    PackageManager mPackageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TimeRecoder.initRecord(this);
        initView();
        setEndReciver();
        initFragment();
        setListener();
        RefreshAchivement refreshAchivement = new RefreshAchivement(this);
        refreshAchivement.getAc();
        RefreshGnome refreshGnome = new RefreshGnome(MainActivity.this);
        refreshGnome.refresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Boolean isfootprint = getIntent().getBooleanExtra("isFootPrint", false);
        if (!isfootprint) {
            fragmentTransaction.replace(R.id.Fl_content, setTimeFragment);
            resetLL(llTimeSet.getId());
        } else {
            fragmentTransaction.replace(R.id.Fl_content, footFragment);
            resetLL(llfootprint.getId());
        }
        fragmentTransaction.commit();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        showAchivement();
    }

    private void showAchivement() {
        AchivementGenerator generator = new AchivementGenerator(this.getApplicationContext(), layoutMain);
        generator.showAchivement();
    }

    private void initFragment() {
        aboutFragment = new AboutFragment();
        setTimeFragment = new SetTimeFragment();
        recordFragment = new RecordFragment();
        footFragment = new FootPrintFragment();
        wallFragment = new AcWallFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Fl_content, setTimeFragment);
        fragmentTransaction.commit();
    }

    private void setListener() {
         llAcwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, wallFragment);
                fragmentTransaction.commit();
                resetLL(llAcwall.getId());
                toggle();
            }
        });
        llTimeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, setTimeFragment);
                fragmentTransaction.commit();
                resetLL(llTimeSet.getId());
                toggle();
            }
        });

        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, aboutFragment);
                fragmentTransaction.commit();
                resetLL(llAbout.getId());
                toggle();
            }
        });

        llRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, recordFragment);
                fragmentTransaction.commit();
                resetLL(llRecord.getId());
                toggle();
            }
        });
        llRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefreshProblem refreshProblem = new RefreshProblem(MainActivity.this);

                refreshProblem.refresh();
            }
        });
        llfootprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fl_content, footFragment);
                fragmentTransaction.commit();
                resetLL(llfootprint.getId());
                toggle();
            }
        });
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle(true);
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llList = new ArrayList<LinearLayout>() {
            {
                add(llAbout);
                add(llTimeSet);
                add(llRecord);
                add(llfootprint);
            }
        };
        resetLL(llTimeSet.getId());
    }

    private void resetLL(int id) {
        ivAcwall.setImageResource(R.drawable.acwall0);
        TvAcwall.setTextColor(getResources().getColor(R.color.black));
        ivAbout.setImageResource(R.drawable.about0);
        tvAbout.setTextColor(getResources().getColor(R.color.black));
        ivRecord.setImageResource(R.drawable.record0);
        TvRecord.setTextColor(getResources().getColor(R.color.black));
        ivSettime.setImageResource(R.drawable.time0);
        TvSettime.setTextColor(getResources().getColor(R.color.black));
        TvSettime.setTextColor(getResources().getColor(R.color.black));
        ivfoot.setImageResource(R.drawable.foot1);
        tvfoot.setTextColor(getResources().getColor(R.color.black));
        switch (id) {
            case R.id.ll_time_set: {
                ivSettime.setImageResource(R.drawable.time);
                TvSettime.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
            }
            case R.id.ll_record: {
                ivRecord.setImageResource(R.drawable.record1);
                TvRecord.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
            }
            case R.id.ll_about: {
                ivAbout.setImageResource(R.drawable.about1);
                tvAbout.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
            }
            case R.id.ll_footprint: {
                ivfoot.setImageResource(R.drawable.foot2);
                tvfoot.setTextColor(getResources().getColor(R.color.sliding_button_press));
                break;
            }
               case R.id.ll_acwall: {
                ivAcwall.setImageResource(R.drawable.acwall1);
                TvAcwall.setTextColor(getResources().getColor(R.color.sliding_button_press));
                   break;
            }
               default:break;
        }
    }

    private long exitTime;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.onemoreexit),
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent mIntent = new Intent();
                mIntent.setAction("com.u3.end");
                sendBroadcast(mIntent);
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void setEndReciver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction("com.u3.end");
        registerReceiver(endReciver, filter);
    }

    private final BroadcastReceiver endReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(endReciver);

    }

    private void toggle() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void toggle(boolean open) {
        if (open)
            drawerLayout.openDrawer(Gravity.LEFT);
        else
            toggle();
    }

}
