package com.tt.tradein.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tt.tradein.utils.PagerFactory;
import com.yhy.tpg.adapter.TpgAdapter;
import com.yhy.tpg.config.PagerConfig;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public class MyFragmentPagerAdapter extends TpgAdapter {
    @Override
    public void reloadDataForCurrentPager(Object... args) {
        super.reloadDataForCurrentPager(args);
    }

    private static final String[] Titles={"二手","求购"};
    private Fragment fragment;

    /**
     * Instantiates a new My fragment pager adapter.
     *
     * @param fm       the fm
     * @param fragment the fragment
     */
    public MyFragmentPagerAdapter(FragmentManager fm, Fragment fragment) {
        super(fm);
        this.fragment = fragment;
    }

    /**
     * Instantiates a new My fragment pager adapter.
     *
     * @param fm the fm
     */
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Instantiates a new My fragment pager adapter.
     *
     * @param fm     the fm
     * @param config the config
     */
    public MyFragmentPagerAdapter(FragmentManager fm, PagerConfig config) {
        super(fm, config);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return Titles.length;
    }

    @Override
    public TpgFragment getPager(int position) {
        return PagerFactory.create(position,fragment);
    }
}
