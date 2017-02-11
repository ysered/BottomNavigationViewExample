package com.ysered.bottomnavigationviewexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class DashboardPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = DashboardPagerAdapter.class.getSimpleName();

    private static final int COUNT = 10;

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position >= 0 && position < COUNT) {
            fragment = ImageSlideFragment.getInstance(position);
        } else {
            fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
