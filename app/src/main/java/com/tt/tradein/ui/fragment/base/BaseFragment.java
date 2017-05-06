package com.tt.tradein.ui.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.tt.tradein.R;
import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerMainActivityComponent;
import com.tt.tradein.di.modules.MainActivityModule;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.NearByPresenter;
import com.tt.tradein.mvp.views.NearByView;
import com.tt.tradein.ui.activity.GoodsDetailActivity;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.widget.CustomExpandableListView;
import com.yhy.tpg.handler.ResultHandler;
import com.yhy.tpg.pager.TpgFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/03/0003.
 */

public abstract class BaseFragment extends TpgFragment implements NearByView {

    @BindView(R.id.near_by_goods_list)
    CustomExpandableListView mNearByGoodsList;
    private View view;
    private Unbinder unbinder;

    @Inject
    NearByPresenter presenter;

    private static final String BASE_FRAGMENT_TYPE = "BASE_FRAGMENT_TYPE";

    private Context mContext;

    public String getmCurrentXiaoqu() {
        return mCurrentXiaoqu;
    }

    public void setmCurrentXiaoqu(String mCurrentXiaoqu) {
        this.mCurrentXiaoqu = mCurrentXiaoqu;
    }

    private final ThreadLocal<List<Goods>> second_goodses = new ThreadLocal<>();
    private final ThreadLocal<List<User>> second_userses = new ThreadLocal<>();

    private final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();

    private boolean isFirstCreated = true;
    private ResultHandler mHandler;

    public abstract boolean IsQiuGou();//传递货物状态

    public abstract String getXiqoqu();

    private String mCurrentXiaoqu;

    private final String TAG = "BaseFrament";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void reloadDate(Object... args) {
        super.reloadDate(args);
        if (presenter != null) {
            presenter.loadGoodsInfor(mContext, String.valueOf(args[0]), IsQiuGou());
        }
    }

    @Override
    protected View getSuccessView() {
        return mNearByGoodsList;
    }

    @Override
    protected void initData(ResultHandler handler) {
        mHandler = handler;
        presenter.loadGoodsInfor(mContext, getXiqoqu(), IsQiuGou());
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
        unbinder = ButterKnife.bind(this, view);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        second_goodses.set(new ArrayList<Goods>());
        second_userses.set(new ArrayList<User>());
        //presenter.loadGoodsInfor(mContext,getXiqoqu(),IsQiuGou());
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {
        mHandler.sendSuccessHandler();
    }

    @Override
    public void onLoadGoodsError(String str) {
        mHandler.sendErrorHandler();
        Log.e(TAG, "onLoadGoodsError: " + str);
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
        second_userses.get().clear();
        second_goodses.get().clear();
        second_userses.get().addAll(users);
        second_goodses.get().addAll(goodses);
        if (adapter.get() == null)
            adapter.set(new ExpandableListViewAdapter(mContext, second_goodses.get(), second_userses.get()));
        adapter.get().notifyDataSetChanged();
        initUI();
    }

    @Override
    public boolean shouldLoadDataAtFirst() {
        return true;
    }

    protected void setupComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isFirstCreated) {
            presenter.loadGoodsInfor(mContext, getXiqoqu(), IsQiuGou());
        }
        super.onHiddenChanged(hidden);
    }

    private void initUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.get().sendEmptyMessage(0);
            }
        }).start();
    }

    private final ThreadLocal<Handler> handler = new ThreadLocal<Handler>() {
        @Override
        protected Handler initialValue() {
            return new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message msg) {
                    adapter.set(null);
                    initHomeList(second_goodses.get(), second_userses.get());
                    if (mHandler != null)
                        mHandler.sendSuccessHandler();
                }
            };
        }
    };

    private class MyGoodsListGroupListener implements ExpandableListView.OnGroupClickListener {
        private List<Goods> goodses = new ArrayList<>();
        private List<User> userses = new ArrayList<>();

        /**
         * Instantiates a new My goods list group listener.
         *
         * @param goodses the goodses
         * @param userses the userses
         */
        MyGoodsListGroupListener(List<Goods> goodses, List<User> userses) {
            this.goodses = goodses;
            this.userses = userses;
        }

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            Toast.makeText(mContext, "" + goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Goods", goodses.get(groupPosition));
            bundle.putSerializable("User", userses.get(groupPosition));
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
    }

    private class MyGoodsListChildListener implements ExpandableListView.OnChildClickListener {
        private List<Goods> goodses = new ArrayList<>();
        private List<User> userses = new ArrayList<>();

        /**
         * Instantiates a new My goods list child listener.
         *
         * @param goodses the goodses
         * @param userses the userses
         */
        MyGoodsListChildListener(List<Goods> goodses, List<User> userses) {
            this.goodses = goodses;
            this.userses = userses;
        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            Toast.makeText(mContext, "" + goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Goods", goodses.get(groupPosition));
            bundle.putSerializable("User", userses.get(groupPosition));
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
    }

    private void initHomeList(List<Goods> goodses, List<User> userses) {
        adapter.set(new ExpandableListViewAdapter(mContext, goodses, userses));
        mNearByGoodsList.setAdapter(adapter.get());
        adapter.get().notifyDataSetChanged();
        for (int i = 0; i < adapter.get().getGroupCount(); i++) {
            mNearByGoodsList.collapseGroup(i);
            mNearByGoodsList.expandGroup(i);
        }
        mNearByGoodsList.setOnGroupClickListener(new MyGoodsListGroupListener(goodses, userses));
        mNearByGoodsList.setOnChildClickListener(new MyGoodsListChildListener(goodses, userses));
    }
}
