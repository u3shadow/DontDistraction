package com.u3.dontdistraction.main.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.u3.dontdistraction.R;
import com.u3.dontdistraction.achievement.AchivementGenerator;
import com.u3.dontdistraction.main.adapter.AcWallAdapter;

import org.json.JSONException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${U3} on 2016/12/5.
 */

public class AcWallFragment extends Fragment {
    @Bind(R.id.wall)
    GridView wall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achivementwall, null);
        ButterKnife.bind(this, view);
        AchivementGenerator generator = new AchivementGenerator(getActivity(),wall);
        try {
           AcWallAdapter adapter =  new AcWallAdapter(getActivity(),generator.getList());
           wall.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
