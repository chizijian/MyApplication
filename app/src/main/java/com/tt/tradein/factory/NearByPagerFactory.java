package com.tt.tradein.factory;

import com.tt.tradein.ui.fragment.nearby_fragment.NearByFragment;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public class NearByPagerFactory {
    /**
     * Create tpg fragment.
     *
     * @param position the position
     * @return the tpg fragment
     */
    public static TpgFragment create(int position) {
        return NearByFragment.getInstance(position);
    }
}
