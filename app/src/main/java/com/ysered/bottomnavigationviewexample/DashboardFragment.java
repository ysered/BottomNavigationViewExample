package com.ysered.bottomnavigationviewexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ysered.bottomnavigationviewexample.view.ViewPagerIndicator;

public class DashboardFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPagerIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        indicator = (ViewPagerIndicator) view.findViewById(R.id.indicator);

        final PagerAdapter adapter = new DashboardPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        indicator.setCount(adapter.getCount());

        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indicator.setCurrent(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
