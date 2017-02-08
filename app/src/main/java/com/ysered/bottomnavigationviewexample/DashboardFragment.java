package com.ysered.bottomnavigationviewexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashboardFragment extends Fragment {

    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        PagerAdapter adapter = new DashboardPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        return view;
    }
}
