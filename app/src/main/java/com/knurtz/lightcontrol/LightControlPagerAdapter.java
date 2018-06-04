package com.knurtz.lightcontrol;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class LightControlPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments;

    public LightControlPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(LightsFragment.newInstance());
        mFragments.add(ScenesFragment.newInstance());
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

}
