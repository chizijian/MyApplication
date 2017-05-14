package com.tt.czj.ui.fragment.nearby_fragment;

import android.os.Bundle;

import com.tt.czj.ui.fragment.base.BaseNearByFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public class NearByQiuGouFragment extends BaseNearByFragment {

    private static final String BASE_FRAGMENT_TYPE="BASE_FRAGMENT_TYPE";

    /**
     * Instantiates a new Near by qiu gou fragment.
     */
    public NearByQiuGouFragment() {
        super();
    }

    @Override
    public boolean shouldLoadDataAtFirst() {
        return false;
    }

    /**
     * New instance near by qiu gou fragment.
     *
     * @param type the type
     * @return the near by qiu gou fragment
     */
    public static NearByQiuGouFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BASE_FRAGMENT_TYPE, type);
        NearByQiuGouFragment fragment = new NearByQiuGouFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean IsQiuGou() {
        return true;
    }

    @Override
    public String getXiqoqu() {
        return getArguments().getString(BASE_FRAGMENT_TYPE);
    }
}
