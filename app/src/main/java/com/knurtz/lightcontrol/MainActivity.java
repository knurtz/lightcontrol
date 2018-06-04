package com.knurtz.lightcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // UI elements
    private Menu action_menu_;
    private RecyclerView rec_view_;
    private BottomNavigationView bottom_nav;
    private SwipeRefreshLayout swipe_refresh;

    // two different adapters for different contents of the recycler view
    private RecyclerView.Adapter lights_view_adapter_;
    private RecyclerView.Adapter scenes_view_adapter_;

    // datasets for lights and scenes
    private LightContainer light_dataset_;
    private SceneContainer scene_dataset_;


    // private methods

    private SwipeRefreshLayout.OnRefreshListener swipe_refresh_listener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // Refresh items
            refreshLights();
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener bottom_nav_listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                // user switched to lights view
                case R.id.navigation_lights:
                    swipe_refresh.setEnabled(true);
                    rec_view_.setAdapter(lights_view_adapter_);
                    return true;

                // user switched to scenes view
                case R.id.navigation_scenes:
                    swipe_refresh.setEnabled(false);
                    rec_view_.setAdapter(scenes_view_adapter_);
            }

            return true;
        }
    };

    private void refreshLights() {

        // go through list of lights an request status

        // dummy delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(false);
                Toast.makeText(getApplication(), getString(R.string.dummy_load_finished), Toast.LENGTH_SHORT).show();
            }
        }, 2000);

    }

    private void initializeData() {

        // load lights from JSON, put into light_dataset_ (Indexed Hashmap)

        // load scenes from JSON, put into scenes_dataset_ (Indexed Hashmap). Get references to lights by id from light_dataset_

        // dummy data
        light_dataset_ = new LightContainer();
        light_dataset_.put("light1", new Light("Ecke Wohnzimmer Rechts", "light1", "192.168.0.1", true, light_type.single_color_light));
        light_dataset_.put("light2", new Light("Ecke Wohnzimmer Links", "light2", "192.168.0.2", true, light_type.single_color_light));
        light_dataset_.put("light3", new Light("SmartDFI Hintergrundlicht", "light3", "192.168.0.3", true, light_type.single_color_light));
        light_dataset_.put("light4", new Light("Ecke Wohnzimmer Hinten", "light4", "192.168.0.4", true, light_type.single_color_light));
        light_dataset_.put("light5", new Light("Küche", "light5", "192.168.0.5", true, light_type.single_color_light));
        light_dataset_.put("light6", new Light("Last Light", "light6", "192.168.0.6", true, light_type.single_color_light));

        scene_dataset_ = new SceneContainer();
        Scene test_scene = new Scene("Test Szene");
        test_scene.addState(new LightState(light_dataset_.get("light1"), true, 100));
        test_scene.addState(new LightState(light_dataset_.get("light2"), false, 50));
        scene_dataset_.put("scene1", test_scene);
    }


    // public methods (in the order they are called during execution of the application)

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // create main layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // attach listener for bottom navigation
        bottom_nav = findViewById(R.id.navigation);
        bottom_nav.setOnNavigationItemSelectedListener(bottom_nav_listener);

        // attach listener for pull-down refresh
        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setOnRefreshListener(swipe_refresh_listener);
        swipe_refresh.setDistanceToTriggerSync(500);

        // load dummy data to fill lights and scenes lists
        initializeData();

        // create two adapters for the main recycler view (one for lights, one for scenes)
        lights_view_adapter_ = new LightsAdapter(light_dataset_);
        scenes_view_adapter_ = new ScenesAdapter(scene_dataset_);

        // set up the main recycler view for lights and scenes
        rec_view_ = findViewById(R.id.rec_view);
        rec_view_.setHasFixedSize(true);            // improves performance
        rec_view_.setLayoutManager(new LinearLayoutManager(this));
        // rec_view_.setAdapter(lights_view_adapter_);                  // will be set by defaulting to one of the two possible main screens in the end of this function

        bottom_nav.setSelectedItemId(R.id.navigation_scenes);           // default to scenes screen, can later be changed in options
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);       // Inflate the menu; this adds items to the action bar
        action_menu_ = menu;                                        // save action menu for later modification
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_settings:
                // activate to settings activity
                Toast.makeText(this, getString(R.string.no_settings), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
