package com.tt.czj.ui.adapter.FragmentPagerAdapter;

import android.support.v4.app.FragmentManager;

import com.tt.czj.factory.FavorPagerFactory;
import com.tt.czj.ui.adapter.base.BaseFragmentmentPagerAdapter;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by czj on 2017/5/11/0011.
 */

public class MyFavorFragmentPagerAdapter extends BaseFragmentmentPagerAdapter {
    public MyFavorFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public TpgFragment getPager(int position) {
        return FavorPagerFactory.create(position);
    }
}
