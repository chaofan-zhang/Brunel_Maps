package com.example.brunel_maps;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.brunel_maps.ui.events.EventsFragment;
import com.example.brunel_maps.ui.home.HomeFragment;
import com.example.brunel_maps.ui.maps.MapFragment;
import com.example.brunel_maps.ui.timetable.TimetableFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_timetable, R.id.nav_map, R.id.nav_events)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);






        findViewById(R.id.nav_home).setOnClickListener(this);
        findViewById(R.id.nav_timetable).setOnClickListener(this);
        findViewById(R.id.nav_map).setOnClickListener(this);
        findViewById(R.id.nav_events).setOnClickListener(this);
        initDate();
    }

    private void initDate() {
        HomeFragment homeFragment = new HomeFragment();
        MapFragment mapFragment = new MapFragment();
        TimetableFragment timetableFragment = new TimetableFragment();
        EventsFragment eventsFragment = new EventsFragment();


        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(timetableFragment);
        fragmentList.add(mapFragment);
        fragmentList.add(eventsFragment);
       // showFragmentByIndex(0);

    }

    public void showFragmentByIndex(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment f = fragmentList.get(i);
            if (!f.isAdded()) {
                ft.add(R.id.nav_host_fragment, f);
            }
            if (i == position) {
                ft.show(f);
            } else {
                ft.hide(f);
            }
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.nav_home:
             showFragmentByIndex(0);
             break;
         case R.id.nav_timetable:
             showFragmentByIndex(1);
             break;
         case R.id.nav_map:
             showFragmentByIndex(2);
             break;
         case R.id.nav_events:
             showFragmentByIndex(3);
             break;

     }
    }
}
