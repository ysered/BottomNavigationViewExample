package com.ysered.bottomnavigationviewexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.SparseArrayCompat;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 2;

    private SparseArrayCompat<Fragment> fragments = new SparseArrayCompat<>(COUNT);

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment == null) {
            fragment = ImageSlideFragment.getInstance(position);
            fragments.append(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
