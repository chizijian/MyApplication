package com.tt.tradein.ui.fragment.home_fragment;

import com.tt.tradein.ui.fragment.base.BaseHomeFragment;

/**
 * Created by czj on 2017/5/09/0009.
 */

public class QiuGouHomeFragment extends BaseHomeFragment {
    private static final String BASE_FRAGMENT_TYPE ="QiuGouHomeFragment" ;

    @Override
    public boolean IsQiuGou() {
        return true;
    }

    @Override
    public boolean shouldLoadDataAtFirst() {
        return false;
    }
}
