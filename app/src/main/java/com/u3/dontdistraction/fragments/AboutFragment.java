package com.u3.dontdistraction.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.u3.dontdistraction.R;

/**
 * Created by U3 on 2015/5/29.
 */
public class AboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.about_title));
        return inflater.inflate(R.layout.fragment_about,null);
    }
}
