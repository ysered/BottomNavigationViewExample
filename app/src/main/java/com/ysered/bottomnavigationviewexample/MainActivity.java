package com.ysered.bottomnavigationviewexample;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom,
                                    R.anim.slide_in_top, R.anim.slide_out_bottom)
                            .replace(R.id.containerFrame, fragment)
                            .commit();
                }
                return true;
            }
        });
    }
}
