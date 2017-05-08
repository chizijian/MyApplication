package com.tt.tradein.utils;

import android.support.v4.app.Fragment;

import com.tt.tradein.ui.fragment.NearByFragment;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public class PagerFactory {
    /**
     * Create tpg fragment.
     *
     * @param position the position
     * @param fragment the fragment
     * @return the tpg fragment
     */
    public static TpgFragment create(int position, Fragment fragment) {
        TpgFragment pager = null;
        switch (position){
            case 0:
                pager=((NearByFragment)fragment).getSecondFrangment();
                break;
            case 1:
                pager=((NearByFragment)fragment).getQiugouFragment();
                break;
        }
        return pager;
    }
}
