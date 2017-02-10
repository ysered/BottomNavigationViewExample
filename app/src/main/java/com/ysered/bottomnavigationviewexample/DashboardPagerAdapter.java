package com.ysered.bottomnavigationviewexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class DashboardPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = DashboardPagerAdapter.class.getSimpleName();

    private static final int COUNT = 2;

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
            case 1:
                fragment = ImageSlideFragment.getInstance(position);
                break;
            default:
                fragment = null;
                Log.e(TAG, "No fragment for position: " + position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
