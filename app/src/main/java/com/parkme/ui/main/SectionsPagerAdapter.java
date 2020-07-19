package com.parkme.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.parkme.R;
import com.parkme.adminlogintab;
import com.parkme.userogintab;
import com.parkme.usersignuptab;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
       // return PlaceholderFragment.newInstance(position + 1);
        Fragment f = null;
        switch (position){
            case 0 :
                f = new userogintab();

                break;
            case 1 :
                f = new usersignuptab();

                break;

            case 2 :
                f = new adminlogintab();

            break;
        }
        return f;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String s="";
        switch(position)
        {
            case 0 : s="User Login";
            break;
            case 1 : s="User SignUp";
                break;
            case 2: s="Admin Login";
                    break;
        }
        return s;
        //return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}