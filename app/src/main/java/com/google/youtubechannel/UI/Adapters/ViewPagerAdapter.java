package com.google.youtubechannel.UI.Adapters;



import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public  class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
   return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void  addFragments(Fragment fragment) {
        fragments.add(fragment);

    }

}
