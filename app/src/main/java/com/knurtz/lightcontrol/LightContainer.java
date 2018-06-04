package com.knurtz.lightcontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Extends the HashMap class in order to be able to access elements by index also
public class LightContainer extends HashMap<String, Light> {

    private List<String> key_list_;

    public LightContainer() {
        super();
        key_list_ = new ArrayList<>();
    }

    @Override
    public Light put(String key, Light value) {
        key_list_.add(key);
        return super.put(key, value);
    }

    public Light getByIndex(int i) {
        return super.get(key_list_.get(i));
    }

    public Light removeByIndex(int i) {
        String key = key_list_.remove(i);
        return super.remove(key);
    }

}
