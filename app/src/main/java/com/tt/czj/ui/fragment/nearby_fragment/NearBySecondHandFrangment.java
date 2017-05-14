package com.tt.czj.ui.fragment.nearby_fragment;

import android.os.Bundle;

import com.tt.czj.ui.fragment.base.BaseNearByFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public class NearBySecondHandFrangment extends BaseNearByFragment {

    private static final String BASE_FRAGMENT_TYPE = "BASE_FRAGMENT_TYPE";

    @Override
    public boolean shouldLoadDataAtFirst() {
        return true;
    }

    /**
     * New instance near by second hand frangment.
     *
     * @param type the type
     * @return the near by second hand frangment
     */
    public static NearBySecondHandFrangment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BASE_FRAGMENT_TYPE, type);
        NearBySecondHandFrangment fragment = new NearBySecondHandFrangment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean IsQiuGou() {
        return false;
    }

    @Override
    public String getXiqoqu() {
        return getArguments().getString(BASE_FRAGMENT_TYPE);
    }

}
