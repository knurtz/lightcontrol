package com.knurtz.lightcontrol;

enum light_type {
    single_color_light,
    rgb_light
}

public class Light {

    private String name_;               // name of the light displayed to the user
    private String id_;                 // id that will be used for referencing this light
    private String address_;            // where to reach light in local network
    private boolean address_is_ip_;     // saves if address is an ip or (if false) a hostname
    private light_type type_;           // RGB or single color light

    private boolean power_;             // if light currently switched on or not
    private int level_;                 // brightness level, goes from 0 to 100 for single color or represents hexadecimal color values (like 0xrrggbb) for RGB lights
    private boolean available_;         // if communication with this light has been successful on the last try


    private boolean transmitValues() {
        String url = "http://" + address_ + "/set?power=" + (power_ ? "0" : "1") + "&level=" + String.valueOf(level_);
        // call url and process response using Volley
        return true;
    }

    // constructor
    public Light(String new_name, String new_id, String new_ip_or_hostname, boolean new_is_ip, light_type new_type) {

        name_ = new_name;
        id_ = new_id;
        address_ = new_ip_or_hostname;
        address_is_ip_ = new_is_ip;
        type_ = new_type;

        power_ = false;     // turn off by default
        level_ = 0;         // value of zero means darkest for single color lights and black for RGB lights
        available_ = false;

    }

    public boolean turnOn() {
        power_ = true;
        this.transmitValues();

        return true;
    }

    public boolean turnOff() {
        power_ = false;
        this.transmitValues();

        return true;
    }

    public boolean setLevel(int new_level) {
        level_ = new_level;
        this.transmitValues();

        return true;
    }

    public boolean setState(boolean new_power, int new_level) {
        power_ = new_power;
        level_ = new_level;
        this.transmitValues();

        return true;
    }

    public boolean setState(LightState new_state) {
        return this.setState(new_state.getStatePower(), new_state.getStateLevel());
    }

    public String getName() {

        return this.name_;
    }

    public String getAddress() {

        return this.address_;
    }

    public LightState getState() {

        return new LightState(this, power_, level_);
    }




}
