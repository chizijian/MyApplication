package com.tt.czj.ui.adapter.base;

import android.support.v4.app.FragmentManager;

import com.yhy.tpg.adapter.TpgAdapter;

/**
 * Created by czj on 2017/5/09/0009.
 */

public abstract class BaseFragmentmentPagerAdapter extends TpgAdapter {

    private static final String[] Titles={"二手","求购"};

    public BaseFragmentmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return Titles.length;
    }
}
