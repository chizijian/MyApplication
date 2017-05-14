package com.tt.czj.ui.fragment.home_fragment;

import com.tt.czj.ui.fragment.base.BaseHomeFragment;

/**
 * Created by czj on 2017/5/09/0009.
 */

public class SecondHandHomeFragment extends BaseHomeFragment {


    private static final String BASE_FRAGMENT_TYPE ="SecondHandHomeFragment" ;

    @Override
    public boolean shouldLoadDataAtFirst() {
        return true;
    }

    @Override
    public boolean IsQiuGou() {
        return false;
    }
}
