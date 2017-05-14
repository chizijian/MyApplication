package com.tt.czj.ui.fragment.base;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerMainActivityComponent;
import com.tt.czj.di.modules.MainActivityModule;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.presenter.HomeViewPresenter;
import com.tt.czj.mvp.views.HomeView;
import com.yhy.tpg.handler.ResultHandler;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by czj on 2017/5/09/0009.
 */

public abstract class BaseHomeFragment extends BaseTpgFrament implements HomeView {

    protected Context mContext;//上下文

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Inject
    HomeViewPresenter presenter;

    private final String TAG = "BaseHomeFragment";

    @Override
    public void errorLoad(Throwable t) {

    }

    @Override
    protected View getSuccessView() {
        return mScrollView;
    }

    @Override
    protected void initData(ResultHandler handler) {
        if (presenter != null)
            presenter.loadGoodsInfor(mContext, IsQiuGou());
    }

    @Override
    public void showBannerData(List<ImageView> images) {

    }

    @Override
    public void reloadDate(Object... args) {
        super.reloadDate(args);
        if (presenter != null)
            presenter.loadGoodsInfor(mContext, IsQiuGou());
    }

    @Override
    public void initView() {
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        presenter.loadGoodsInfor(mContext, IsQiuGou());
    }

    @Override
    public void HiddenChange() {
        presenter.loadGoodsInfor(mContext, IsQiuGou());
    }

    protected void setupComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
        super.parseUser(users,goodses);
    }

    @Override
    public void onLoadGoodsError(String str) {
        Log.e(TAG, String.format("onLoadGoodsError: %s", str));
    }
}
