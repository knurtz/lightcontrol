package com.knurtz.lightcontrol;


public class LightState {

    private Light light_;
    private boolean state_power_;
    private int state_level_;

    public LightState(Light new_light, boolean new_power, int new_level) {
        light_ = new_light;
        state_power_ = new_power;
        state_level_ = new_level;
    }

    public String getLightName() {
        return light_.getName();
    }

    public boolean getStatePower() {
        return state_power_;
    }

    public int getStateLevel() {
        return state_level_;
    }

    public String getDescription() {
        return getLightName() + ": " + (state_power_ ? (String.valueOf(getStateLevel()) + " %") : "Off");
    }

    public boolean activate() {
        light_.setState(state_power_, state_level_);
        return true;
    }
}
