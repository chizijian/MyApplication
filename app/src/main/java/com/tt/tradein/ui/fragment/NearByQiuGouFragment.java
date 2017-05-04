package com.tt.tradein.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tradein.R;
import com.tt.tradein.widget.CustomExpandableListView;
import com.yhy.tpg.handler.ResultHandler;
import com.yhy.tpg.pager.TpgFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/03/0003.
 */

public class NearByQiuGouFragment extends TpgFragment {

    @BindView(R.id.near_by_goods_list)
    CustomExpandableListView mNearByGoodsList;

    private static final String NEARBY_QIUGOU_TYPE = "NEARBY_QIUGOU_TYPE";

    private View view;
    private Unbinder unbinder;

    public NearByQiuGouFragment() {
        super();
    }


    private String mCurrentXiqoqu;

    public static NearByQiuGouFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(NEARBY_QIUGOU_TYPE, type);
        NearByQiuGouFragment fragment = new NearByQiuGouFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.nearby_secondhand_frangment, null);
        mCurrentXiqoqu=getArguments().getString(NEARBY_QIUGOU_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected View getSuccessView() {
        return null;
    }

    @Override
    protected void initData(ResultHandler handler) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
