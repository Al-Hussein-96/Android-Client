package com.example.al_hussein.client;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Al-Hussein on 7/3/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitles = new ArrayList<>();
    private Map<Integer,String> mFragmentTags;
    private FragmentManager fragmentManager;



    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
        this.fragmentManager = fm;
        this.mFragmentTags = new HashMap<Integer, String>();

    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }
    @Override
    public int getCount() {
        return listTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);
    }

    public void AddFragment(Fragment fragment,String title){
        listFragment.add(fragment);
        listTitles.add(title);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container,position);

        if(obj instanceof Fragment)
        {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position,tag);

        }


        return obj;
    }
    public Fragment getFragment(int position)
    {
        String tag = mFragmentTags.get(position);
        if(tag == null)
            return  null;
        return fragmentManager.findFragmentByTag(tag);
    }

}













