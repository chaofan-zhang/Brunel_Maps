package com.example.brunel_maps;

  import android.os.Bundle;
        import android.view.Menu;
  import android.view.View;

  import com.example.brunel_maps.ui.events.EventsFragment;
  import com.example.brunel_maps.ui.home.HomeFragment;
  import com.example.brunel_maps.ui.maps.MapFragment;
  import com.example.brunel_maps.ui.timetable.TimetableFragment;
  import com.google.android.material.bottomnavigation.BottomNavigationView;

  import java.util.ArrayList;
  import java.util.List;

  import androidx.appcompat.app.AppCompatActivity;
  import androidx.drawerlayout.widget.DrawerLayout;
  import androidx.fragment.app.Fragment;
  import androidx.fragment.app.FragmentTransaction;
  import androidx.navigation.NavController;
        import androidx.navigation.Navigation;
        import androidx.navigation.ui.AppBarConfiguration;
        import androidx.navigation.ui.NavigationUI;

public class BottomNav_Activity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> fragmentList;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_timetable, R.id.nav_map,R.id.nav_map)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

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
        showFragmentByIndex(0);

    }

    /**
     * 通过下标显示和隐藏fragment
     *
     * @param position
     */
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
