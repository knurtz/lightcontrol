package com.knurtz.lightcontrol;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private String name_;
    private List<LightState> state_list_;

    public Scene(String new_name) {
        state_list_ = new ArrayList<>();
        name_ = new_name;
    }

    public void addState(LightState new_state) {
        state_list_.add(new_state);
    }

    public List<LightState> getStateList() {
        return state_list_;
    }

    public boolean activate() {
        // go through list of states and activate each state

        return true;
    }

    public String getName() {
        return name_;
    }

    public String getDescription() {
        // go through list of states and get description for each one
        String desc = new String();
        for (LightState state : state_list_) {
            desc += state.getDescription() + "\n";
        }

        return desc;
    }
}


