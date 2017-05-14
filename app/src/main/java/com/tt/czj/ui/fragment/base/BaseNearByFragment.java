package com.tt.czj.ui.fragment.base;

import android.content.Context;
import android.util.Log;

import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerMainActivityComponent;
import com.tt.czj.di.modules.MainActivityModule;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.presenter.NearByPresenter;
import com.tt.czj.mvp.views.NearByView;
import com.yhy.tpg.handler.ResultHandler;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/5/03/0003.
 */
public abstract class BaseNearByFragment extends BaseTpgFrament implements NearByView {

    private final String TAG = "BaseNearByFragment";

    @Inject
    NearByPresenter presenter;

    private Context mContext;//上下文
    private ResultHandler mHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    /**
     * Gets xiqoqu.
     *
     * @return the xiqoqu
     */
    public abstract String getXiqoqu();

    @Override
    public void initView() {
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
    }

    @Override
    public void HiddenChange() {
    }

    @Override
    public void reloadDate(Object... args) {
        super.reloadDate(args);
        if (presenter != null) {
            presenter.loadGoodsInfor(mContext, String.valueOf(args[0]), IsQiuGou());
        }
    }

    @Override
    protected void initData(ResultHandler handler) {
        mHandler = handler;
        if (presenter != null)
            presenter.loadGoodsInfor(mContext, getXiqoqu(), IsQiuGou());
    }


    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {
        mHandler.sendSuccessHandler();
    }

    @Override
    public void onLoadGoodsError(String str) {
        if (mHandler != null)
            mHandler.sendErrorHandler();
        String TAG = "BaseFrament";
        Log.e(TAG, "onLoadGoodsError: " + str);
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
       super.parseUser(users,goodses);
    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }
}
