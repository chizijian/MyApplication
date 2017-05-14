package com.tt.czj.ui.fragment.home_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerMainActivityComponent;
import com.tt.czj.di.modules.MainActivityModule;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.presenter.HomeViewPresenter;
import com.tt.czj.mvp.views.HomeView;
import com.tt.czj.ui.activity.KindActivity;
import com.tt.czj.ui.activity.SearchActivity;
import com.tt.czj.ui.adapter.FragmentPagerAdapter.MyHomeFragmentPageAdapter;
import com.tt.czj.ui.adapter.base.BaseFragmentmentPagerAdapter;
import com.tt.czj.utils.UIUtils;
import com.tt.czj.widget.ImageFlipper;
import com.yhy.tpg.widget.TpgView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czj
 */
public class HomeFragment extends Fragment implements HomeView {
    /**
     * The constant TAG.
     */
    public static final String TAG = "HomeFragment";
    /**
     * The M image fliper.
     */
    @BindView(R.id.imageFliper)
    ImageFlipper mImageFliper;

    /**
     * The M icon home item.
     */
    @BindView(R.id.icon_home_item)
    ImageView mIconHomeItem;
    /**
     * The M icon search imageview.
     */
    @BindView(R.id.icon_search_imageview)
    ImageView mIconSearchImageview;

    /**
     * The M home scroll view.
     */
    @BindView(R.id.home_scroll_view)
    ScrollView mHomeScrollView;

    /**
     * The Dot list.
     */
    @BindViews({R.id.dot_one, R.id.dot_two, R.id.dot_three, R.id.dot_four})
    List<View> DotList;

    @BindView(R.id.Home_TpgView)
    TpgView mTpgView;


    private Context mContext;
    private View mRootView;

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The M get weather text view.
     */
    @BindView(R.id.text3)
    TextView mGetWeatherTextView;
    /**
     * The Presenter.
     */
    @Inject
    HomeViewPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.home_fragment, container, false);
        }
        unbinder = ButterKnife.bind(this, mRootView);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        initData();
        return mRootView;
    }

    /**
     * Init data.
     */
    public void initData() {

        List<ImageView> imageViews = new ArrayList<>();
        presenter.loadBannerData(mContext, imageViews);

        final BaseFragmentmentPagerAdapter baseFragmentmentPagerAdapter = new MyHomeFragmentPageAdapter(getChildFragmentManager());
        mTpgView.setAdapter(baseFragmentmentPagerAdapter);
        mTpgView.setTabGravity(TabLayout.GRAVITY_FILL);
        mTpgView.setTabMode(TabLayout.MODE_FIXED);
        mTpgView.setTabIndicatorHeight(1);
        mTpgView.setTabHeight(40);
        mTpgView.setOnPageChangedListener(new TpgView.OnPageChangedListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                baseFragmentmentPagerAdapter.reloadDataForCurrentPager();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * Init dots.
     *
     * @param i the
     */
    public void initDots(final int i) {
        ButterKnife.apply(DotList, new ButterKnife.Action<View>() {
            @Override
            public void apply(@NonNull View view, int index) {
                view.setBackgroundResource(R.drawable.indictor_shape_normal);
                if (index == i)
                    view.setBackgroundResource(R.drawable.indicator_shape_selected);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.text3, R.id.icon_home_item, R.id.icon_search_imageview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text3:
                break;
            case R.id.icon_home_item:
                UIUtils.nextPage(mContext, KindActivity.class);
                break;
            case R.id.icon_search_imageview:
                UIUtils.nextPage(mContext, SearchActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showBannerData(List<ImageView> images) {
        for (int i = 0; i < images.size(); i++) {
            mImageFliper.addView(images.get(i));
        }
        mImageFliper.setOnPageChangeListener(new ImageFlipper.OnPageChangeListener() {
            @Override
            public void onPageSelected(int index) {
                initDots(index);
            }
        });
    }

    @Override
    public void errorLoad(Throwable t) {

    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses) {

    }

    @Override
    public void onLoadGoodsError(String str) {

    }
}
