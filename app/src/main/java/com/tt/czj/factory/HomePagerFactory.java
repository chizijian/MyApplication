package com.tt.czj.factory;

import com.tt.czj.ui.fragment.home_fragment.QiuGouHomeFragment;
import com.tt.czj.ui.fragment.home_fragment.SecondHandHomeFragment;
import com.yhy.tpg.pager.TpgFragment;

/**
 * Created by czj on 2017/5/09/0009.
 */

public class HomePagerFactory {
    public static TpgFragment create(int position) {
        switch (position){
            case 0:
                return new SecondHandHomeFragment();
            case 1:
                return new QiuGouHomeFragment();
        }
        return new SecondHandHomeFragment();
    }
}
