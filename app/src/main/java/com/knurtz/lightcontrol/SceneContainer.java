package com.knurtz.lightcontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Extends the HashMap class in order to be able to access elements by index also
public class SceneContainer extends HashMap<String, Scene> {

    private List<String> key_list_;

    public SceneContainer() {
        super();
        key_list_ = new ArrayList<>();
    }

    @Override
    public Scene put(String key, Scene value) {
        key_list_.add(key);
        return super.put(key, value);
    }

    public Scene getByIndex(int i) {
        return super.get(key_list_.get(i));
    }

    public Scene removeByIndex(int i) {
        String key = key_list_.remove(i);
        return super.remove(key);
    }

}
