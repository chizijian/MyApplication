package com.tt.czj.ui.fragment.home_fragment;

import com.tt.czj.ui.fragment.base.BaseHomeFragment;

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
