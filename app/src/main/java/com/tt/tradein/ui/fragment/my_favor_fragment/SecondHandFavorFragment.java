package com.tt.tradein.ui.fragment.my_favor_fragment;

import com.tt.tradein.ui.fragment.base.BaseMyFavorFragment;

/**
 * Created by czj on 2017/5/11/0011.
 */

public class SecondHandFavorFragment extends BaseMyFavorFragment {
    @Override
    public boolean IsQiuGou() {
        return false;
    }

    @Override
    public boolean shouldLoadDataAtFirst() {
        return true;
    }
}