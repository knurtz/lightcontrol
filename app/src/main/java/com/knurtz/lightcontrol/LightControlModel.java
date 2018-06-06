package com.knurtz.lightcontrol;

import android.arch.lifecycle.ViewModel;

public class LightControlModel extends ViewModel {

    // datasets for lights and scenes
    public LightContainer light_dataset_;
    public SceneContainer scene_dataset_;

    public LightControlModel() {

        // load lights from JSON, put into light_dataset_ (Indexed Hashmap)

        // load scenes from JSON, put into scenes_dataset_ (Indexed Hashmap). Get references to lights by id from light_dataset_

        // for now use dummy data
        light_dataset_ = new LightContainer();
        light_dataset_.put("light1", new Light("Ecke Wohnzimmer Rechts", "light1", "192.168.0.1", true, light_type.single_color_light));
        light_dataset_.put("light2", new Light("Ecke Wohnzimmer Links", "light2", "192.168.0.2", true, light_type.single_color_light));
        light_dataset_.put("light3", new Light("SmartDFI Hintergrundlicht", "light3", "192.168.0.3", true, light_type.single_color_light));
        light_dataset_.put("light4", new Light("Ecke Wohnzimmer Hinten", "light4", "192.168.0.4", true, light_type.single_color_light));
        light_dataset_.put("light5", new Light("Küche", "light5", "192.168.0.5", true, light_type.single_color_light));
        light_dataset_.put("light6", new Light("Last Light", "light6", "192.168.0.6", true, light_type.single_color_light));

        light_dataset_.get("light1").setState(true, 64);
        light_dataset_.get("light3").setState(true, 22);
        light_dataset_.get("light4").setState(true, 90);

        scene_dataset_ = new SceneContainer();
        Scene test_scene = new Scene("Test Szene");
        test_scene.addState(new LightState(light_dataset_.get("light1"), true, 100));
        test_scene.addState(new LightState(light_dataset_.get("light2"), false, 50));
        scene_dataset_.put("scene1", test_scene);
    }


}
