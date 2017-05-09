package com.tt.tradein.ui.adapter;

import android.support.v4.app.FragmentManager;

import com.tt.tradein.factory.HomePagerFactory;
import com.tt.tradein.ui.adapter.base.BaseFragmentmentPagerAdapter;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by czj on 2017/5/09/0009.
 */

public class MyHomeFragmentPageAdapter extends BaseFragmentmentPagerAdapter {
    public MyHomeFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public TpgFragment getPager(int position) {
        return HomePagerFactory.create(position);
    }
}
