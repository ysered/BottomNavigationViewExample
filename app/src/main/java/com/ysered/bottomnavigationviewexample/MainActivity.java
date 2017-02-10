package com.ysered.bottomnavigationviewexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int TAB_COUNT = 3;

    final SparseArrayCompat<Fragment> fragments = new SparseArrayCompat<>(TAB_COUNT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (fragments.size() == 0) {
            fragments.put(R.id.itemProfile, new ProfileFragment());
            fragments.put(R.id.itemMap, new MapFragment());
            fragments.put(R.id.itemDashboard, new DashboardFragment());
        }
        loadFragment(fragments.get(R.id.itemProfile));


        final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final Fragment fragment = fragments.get(item.getItemId());
                if (fragment != null) {
                    loadFragment(fragment);
                } else {
                    Log.d(TAG, "No fragment for menu item: " + item.getItemId());
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
}
