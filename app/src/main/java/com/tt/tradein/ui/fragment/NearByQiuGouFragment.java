package com.tt.tradein.ui.fragment;

import android.os.Bundle;

import com.tt.tradein.ui.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/03/0003.
 */

public class NearByQiuGouFragment extends BaseFragment {

    private static final String BASE_FRAGMENT_TYPE="BASE_FRAGMENT_TYPE";
    public NearByQiuGouFragment() {
        super();
    }

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
