package com.tt.tradein.ui.fragment;

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
import com.tt.tradein.mvp.presenter.NearByPresenterImpl;
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

public class NearBySecondHandFrangment extends TpgFragment implements NearByView {
    @BindView(R.id.near_by_goods_list)
    CustomExpandableListView mNearByGoodsList;

    private final String TAG = "NearBySecondFrangment";

    private static final String NEARBY_SECOND_TYPE = "NEARBY_SECOND_TYPE";

    private String mCurrentXiaoqu;

    private View view;
    private Unbinder unbinder;

    private Context mContext;

    private final ThreadLocal<List<Goods>> second_goodses = new ThreadLocal<>();
    private final ThreadLocal<List<User>> second_userses = new ThreadLocal<>();

    private final ThreadLocal<ExpandableListViewAdapter> adapter = new ThreadLocal<>();

    private boolean isFirstCreated = true;
    @Inject
    NearByPresenter presenter;

    public static NearBySecondHandFrangment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(NEARBY_SECOND_TYPE, type);
        NearBySecondHandFrangment fragment = new NearBySecondHandFrangment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view == null) {
            isFirstCreated = false;
            view = View.inflate(getActivity(), R.layout.nearby_secondhand_frangment, null);
        }
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        mCurrentXiaoqu = getArguments().getString(NEARBY_SECOND_TYPE);
        second_goodses.set(new ArrayList<Goods>());
        second_userses.set(new ArrayList<User>());
        adapter.set(new ExpandableListViewAdapter(mContext, second_goodses.get(), second_userses.get()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO:OnCreateView Method has been created, run ButterKnife again to generate code
        unbinder = ButterKnife.bind(this, view);
        presenter.loadGoodsInfor(mContext, mCurrentXiaoqu, false);
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


    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {

    }

    @Override
    public void onLoadGoodsError(String str) {
        Log.e(TAG, "onLoadGoodsError: " + str);
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {
        if (second_goodses.get() != null && second_userses.get() != null) {
            second_goodses.get().clear();
            second_userses.get().clear();
        } else {
            second_userses.set(new ArrayList<User>());
            second_goodses.set(new ArrayList<Goods>());
        }
        second_userses.get().addAll(users);
        second_goodses.get().addAll(goodses);
        if (adapter.get() == null)
            adapter.set(new ExpandableListViewAdapter(mContext, second_goodses.get(), second_userses.get()));
        adapter.get().notifyDataSetChanged();
        initUI();
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
            presenter.loadGoodsInfor(mContext, mCurrentXiaoqu, false);
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

    public void Refresh(String type, Context mContext) {
        if (isFirstCreated) {
            presenter = new NearByPresenterImpl(this);
            mCurrentXiaoqu = type;
            mContext = mContext;
        }
        presenter.loadGoodsInfor(mContext, mCurrentXiaoqu, false);
    }
}
