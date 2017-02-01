package com.ysered.bottomnavigationviewexample;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_user:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_map:
                        Toast.makeText(MainActivity.this, "Map", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
