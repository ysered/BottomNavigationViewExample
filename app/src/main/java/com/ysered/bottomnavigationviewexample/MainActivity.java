package com.ysered.bottomnavigationviewexample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    //private static final int TAB_COUNT = 3;

    //final SparseArrayCompat<Fragment> fragments = new SparseArrayCompat<>(TAB_COUNT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fragment fragment = new ProfileFragment();
        loadFragment(fragment);

        /*fragments.put(R.id.itemProfile, fragment);
        fragments.put(R.id.itemMap, new MapFragment());
        // FIXME: image views shows when fragment created once
        fragments.put(R.id.itemDashboard, new DashboardFragment());*/

        final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.itemProfile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.itemMap:
                        fragment = new MapFragment();
                        break;
                    case R.id.itemDashboard:
                        fragment = new DashboardFragment();
                        break;
                }
                if (fragment != null) {
                    loadFragment(fragment);
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        loadFragment(fragment, false);
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom,
                R.anim.slide_in_top, R.anim.slide_out_bottom)
                .replace(R.id.containerFrame, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    private void removeTextLabel(@NonNull BottomNavigationView bottomNavigationView, @IdRes int menuItemId) {
        final View view = bottomNavigationView.findViewById(menuItemId);
        if (view == null) {
            return;
        }
        if (view instanceof MenuView.ItemView) {
            FrameLayout layout = (FrameLayout) view;
            for (int i = 0; i < layout.getChildCount(); i++) {
                View v = layout.getChildAt(i);
                if (v instanceof ViewGroup) {
                    layout.removeViewAt(i);
                }
            }
        }
    }
}
