package com.tt.czj.factory;

import com.tt.czj.ui.fragment.my_favor_fragment.QiuGouFavorFragment;
import com.tt.czj.ui.fragment.my_favor_fragment.SecondHandFavorFragment;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by czj on 2017/5/11/0011.
 */

public class FavorPagerFactory {
    public static TpgFragment create(int position) {
        switch (position){
            case 0:
                return new SecondHandFavorFragment();
            case 1:
                return new QiuGouFavorFragment();
        }
        return new SecondHandFavorFragment();
    }
}
