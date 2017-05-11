package com.tt.tradein.ui.adapter.FragmentPagerAdapter;

import android.support.v4.app.FragmentManager;

import com.tt.tradein.factory.NearByPagerFactory;
import com.tt.tradein.ui.adapter.base.BaseFragmentmentPagerAdapter;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public class MyNearByFragmentPagerAdapter extends BaseFragmentmentPagerAdapter {
    public MyNearByFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public TpgFragment getPager(int position) {
        return NearByPagerFactory.create(position);
    }
}
