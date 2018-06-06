package com.knurtz.lightcontrol;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity
        extends AppCompatActivity
        implements LightsFragment.OnFragmentInteractionListener, ScenesFragment.OnFragmentInteractionListener {

    // UI elements
    private Menu action_menu_;
    private ViewPager mViewPager;
    private BottomNavigationView bottom_nav;

    // access application data through ViewModel
    LightControlModel mViewModel;


    // private methods

    private BottomNavigationView.OnNavigationItemSelectedListener bottom_nav_listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_lights:
                    mViewPager.setCurrentItem(0, true);
                    break;

                case R.id.navigation_scenes:
                    mViewPager.setCurrentItem(1, true);
            }
            return true;
        }
    };


    // public methods (in the order they are called during execution of the application)

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // create main layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // attach listener for bottom navigation
        bottom_nav = findViewById(R.id.navigation);
        bottom_nav.setOnNavigationItemSelectedListener(bottom_nav_listener);

        // load application data
        mViewModel = ViewModelProviders.of(this).get(LightControlModel.class);

        // set up main view pager
        LightControlPagerAdapter mPagerAdapter = new LightControlPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottom_nav.setSelectedItemId(R.id.navigation_lights);
                        break;
                    case 1:
                        bottom_nav.setSelectedItemId(R.id.navigation_scenes);
                        break;
                }
            }
        });

        bottom_nav.setSelectedItemId(R.id.navigation_scenes);           // default to scenes screen, @TODO can be changed in preferences, saved as savedInstanceState
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);       // Inflate the menu; this adds items to the action bar
        action_menu_ = menu;                                        // save action menu for later modification
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                // @TODO jump to settings fragment
                Toast.makeText(this, getString(R.string.no_settings), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateFromLightsFragment() {

        // foo
    }

    public void updateFromScenesFragment() {

        // foo
    }
}
