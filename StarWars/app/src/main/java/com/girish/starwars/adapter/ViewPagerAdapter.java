package com.girish.starwars.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.girish.starwars.fragments.FilmFragment;
import com.girish.starwars.fragments.PeopleFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mNoOfTabs;
    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNoOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                PeopleFragment peopleFragmentObj = new PeopleFragment();
                return  peopleFragmentObj;
            case 1:
                FilmFragment filmFragmentObj = new FilmFragment();
                return filmFragmentObj;
             default:
                 return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNoOfTabs;
    }
}
