package com.tt.tradein.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tt.tradein.R;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.NearByPresenter;
import com.tt.tradein.mvp.views.NearByView;
import com.tt.tradein.widget.CustomExpandableListView;
import com.yhy.tpg.handler.ResultHandler;
import com.yhy.tpg.pager.TpgFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/03/0003.
 */

public class BaseFragment extends TpgFragment implements NearByView {

    @BindView(R.id.near_by_goods_list)
    CustomExpandableListView mNearByGoodsList;
    private View view;
    private Unbinder unbinder;

    @Inject
    NearByPresenter presenter;

    private boolean isFirstCreated = true;

    @Override
    protected View getSuccessView() {
        return null;
    }

    @Override
    protected void initData(ResultHandler handler) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view == null) {
            isFirstCreated = false;
            view = View.inflate(getActivity(), R.layout.nearby_secondhand_frangment, null);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO:OnCreateView Method has been created, run ButterKnife again to generate code
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isFirstCreated) {
            //presenter.loadGoodsInfor(mContext, mCurrentXiaoqu, false);
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {

    }

    @Override
    public void onLoadGoodsError(String str) {

    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {

    }
}
